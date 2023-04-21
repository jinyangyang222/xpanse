/*
 * SPDX-License-Identifier: Apache-2.0
 * SPDX-FileCopyrightText: Huawei Inc.
 *
 */

package org.eclipse.xpanse.modules.deployment.deployers.terraform;

import jakarta.validation.Valid;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.xpanse.modules.deployment.Deployment;
import org.eclipse.xpanse.modules.deployment.deployers.terraform.exceptions.TerraformExecutorException;
import org.eclipse.xpanse.modules.deployment.utils.DeployEnvironments;
import org.eclipse.xpanse.modules.models.enums.Csp;
import org.eclipse.xpanse.modules.models.enums.DeployerKind;
import org.eclipse.xpanse.modules.models.enums.TerraformExecState;
import org.eclipse.xpanse.modules.models.service.DeployResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Implementation of th deployment with terraform.
 */
@Slf4j
@Component
public class TerraformDeployment implements Deployment {

    public static final String VERSION_FILE_NAME = "version.tf";
    public static final String SCRIPT_FILE_NAME = "resources.tf";
    public static final String TERRAFORM_TAG_NAME = "terraform.tar.gz";


    private final String workspaceDirectory;

    public TerraformDeployment(
            @Value("${terraform.workspace.directory:xpanse_deploy_ws}") String workspaceDirectory) {
        this.workspaceDirectory = workspaceDirectory;
    }

    /**
     * Deploy the DeployTask.
     *
     * @param task the task for the deployment.
     */
    @Override
    public DeployResult deploy(DeployTask task) {
        String workspace = getWorkspacePath(task.getId().toString());
        // Create the workspace.
        buildWorkspace(workspace);

        String tagUrl = task.getOcl().getDeployment().getDeployer();

        try {
            downLoadTag(workspace, tagUrl);
            String fileName = unZip(workspace);
            String tfScript = readFiLeAsString(workspace, fileName);
            createScriptFile(task.getCreateRequest().getCsp(), task.getCreateRequest().getRegion(),
                    workspace, tfScript);
        } catch (Exception e) {
            e.printStackTrace();
        }

        TerraformExecutor executor = getExecutor(task, workspace);
        executor.deploy();
        String tfState = executor.getTerraformState();

        DeployResult deployResult = new DeployResult();
        if (StringUtils.isBlank(tfState)) {
            deployResult.setState(TerraformExecState.DEPLOY_FAILED);
        } else {
            deployResult.setState(TerraformExecState.DEPLOY_SUCCESS);
            deployResult.getProperty().put("stateFile", tfState);
        }

        if (task.getDeployResourceHandler() != null) {
            task.getDeployResourceHandler().handler(deployResult);
        }
        return deployResult;
    }


    /**
     * Destroy the DeployTask.
     *
     * @param task the task for the deployment.
     */
    @Override
    public DeployResult destroy(DeployTask task) {
        String workspace = getWorkspacePath(task.getId().toString());
        TerraformExecutor executor = getExecutor(task, workspace);
        DeployResult result = new DeployResult();
        result.setId(task.getId());
        executor.destroy();
        result.setState(TerraformExecState.DESTROY_SUCCESS);
        return result;
    }

    /**
     * Get a TerraformExecutor.
     *
     * @param task      the task for the deployment.
     * @param workspace the workspace of the deployment.
     */
    private TerraformExecutor getExecutor(DeployTask task, String workspace) {
        Map<String, String> envVariables = DeployEnvironments.getEnv(task);
        Map<String, String> flavorVariables = DeployEnvironments.getFlavorVariables(task);
        Map<String, String> tfFlavorVariables = new HashMap<>();
        for (String key : flavorVariables.keySet()) {
            tfFlavorVariables.put("TF_VAR_" + key, flavorVariables.get(key));
        }
        envVariables.putAll(tfFlavorVariables);
        return new TerraformExecutor(envVariables, DeployEnvironments.getVariables(task),
                workspace);
    }

    /**
     * Create terraform script.
     *
     * @param csp       the cloud service provider.
     * @param workspace the workspace for terraform.
     * @param script    the terraform scripts of the task.
     */
    private void createScriptFile(Csp csp, String region, String workspace, String script) {
        log.info("start create terraform script");
        String verScriptPath = workspace + File.separator + VERSION_FILE_NAME;
        String scriptPath = workspace + File.separator + SCRIPT_FILE_NAME;
        try {
            try (FileWriter verWriter = new FileWriter(verScriptPath);
                    FileWriter scriptWriter = new FileWriter(scriptPath)) {
                verWriter.write(TerraformProviders.getProvider(csp).getProvider(region));
                scriptWriter.write(script);
            }
            log.info("terraform script create success");
        } catch (IOException ex) {
            log.error("create version file failed.", ex);
            throw new TerraformExecutorException("create version file failed.", ex);
        }
    }

    /**
     * Build workspace of the `terraform`.
     *
     * @param workspace The workspace of the task.
     */
    private void buildWorkspace(String workspace) {
        log.info("start create workspace");
        File ws = new File(workspace);
        if (!ws.exists() && !ws.mkdirs()) {
            throw new TerraformExecutorException(
                    "Create workspace failed, File path not created: " + ws.getAbsolutePath());
        }
        log.info("workspace create success,Working directory is " + ws.getAbsolutePath());
    }

    /**
     * Get the workspace path for terraform.
     *
     * @param taskId The id of the task.
     */
    private String getWorkspacePath(String taskId) {
        return System.getProperty("user.dir")
                + File.separator + this.workspaceDirectory + File.separator + taskId;
    }


    /**
     * Get the deployer kind.
     */
    @Override
    public DeployerKind getDeployerKind() {
        return DeployerKind.TERRAFORM;
    }


    public String readFiLeAsString(String workspace, String fileName) {
        String directoryPath = workspace + File.separator + fileName + "deployScript.tf";
        String scriptStr = null;
        try {
            scriptStr = Files.readString(Paths.get(directoryPath));
            log.info("Read terraform script success.");
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Read terraform script fail:{}.", e.getMessage());
        }
        return scriptStr;
    }


    public String unZip(String workspace) {
        String fileName = null;
        boolean isFirst = true;
        Path targetPath = Paths.get(workspace + File.separator + TERRAFORM_TAG_NAME);
        try (TarArchiveInputStream tarInput = new TarArchiveInputStream(
                new GzipCompressorInputStream(new BufferedInputStream(
                        Files.newInputStream(targetPath))))) {
            TarArchiveEntry entry;
            while ((entry = tarInput.getNextTarEntry()) != null) {
                Path filePath = Paths.get(workspace + File.separator + entry.getName());
                if (entry.isDirectory()) {
                    Files.createDirectories(filePath);
                    if (isFirst) {
                        fileName = entry.getName();
                        isFirst = false;
                    }
                } else {
                    Files.createDirectories(filePath.getParent());
                    Files.copy(tarInput, filePath, StandardCopyOption.REPLACE_EXISTING);
                }
            }
            log.info("Unzip the tag package success,name:{}", fileName);
            Files.delete(targetPath);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Unzip the tag package fail:{}", e.getMessage());
        }
        return fileName;
    }

    public void downLoadTag(String workspace, String tagUrl) {
        try {
            URL url = new URL(tagUrl);
            InputStream in = url.openStream();
            FileOutputStream out = new FileOutputStream(
                    workspace + File.separator + TERRAFORM_TAG_NAME);
            byte[] buffer = new byte[4096];
            int bytesRead = 0;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
            out.close();
            in.close();
            log.info("Download the tag package success.");
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Download the tag package fail:{}.", e.getMessage());
        }
    }


}
