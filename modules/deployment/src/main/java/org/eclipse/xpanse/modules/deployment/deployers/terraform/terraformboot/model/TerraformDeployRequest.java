/*
 * SPDX-License-Identifier: Apache-2.0
 * SPDX-FileCopyrightText: Huawei Inc.
 *
 */

package org.eclipse.xpanse.modules.deployment.deployers.terraform.terraformboot.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * TerraformDeployRequest
 */

public class TerraformDeployRequest {

  private Boolean isPlanOnly;

  @Valid
  private Map<String, Object> variables = new HashMap<>();

  @Valid
  private Map<String, String> envVariables = new HashMap<>();

  /**
   * Default constructor
   * @deprecated Use {@link TerraformDeployRequest#TerraformDeployRequest(Boolean, Map<String, Object>)}
   */
  @Deprecated
  public TerraformDeployRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public TerraformDeployRequest(Boolean isPlanOnly, Map<String, Object> variables) {
    this.isPlanOnly = isPlanOnly;
    this.variables = variables;
  }

  public TerraformDeployRequest isPlanOnly(Boolean isPlanOnly) {
    this.isPlanOnly = isPlanOnly;
    return this;
  }

  /**
   * Flag to control if the deployment must only generate the terraform or it must also apply the changes.
   * @return isPlanOnly
  */
  @NotNull 
  @Schema(name = "isPlanOnly", description = "Flag to control if the deployment must only generate the terraform or it must also apply the changes.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("isPlanOnly")
  public Boolean getIsPlanOnly() {
    return isPlanOnly;
  }

  public void setIsPlanOnly(Boolean isPlanOnly) {
    this.isPlanOnly = isPlanOnly;
  }

  public TerraformDeployRequest variables(Map<String, Object> variables) {
    this.variables = variables;
    return this;
  }

  public TerraformDeployRequest putVariablesItem(String key, Object variablesItem) {
    if (this.variables == null) {
      this.variables = new HashMap<>();
    }
    this.variables.put(key, variablesItem);
    return this;
  }

  /**
   * Key-value pairs of variables that must be used to execute the Terraform request.
   * @return variables
  */
  @NotNull 
  @Schema(name = "variables", description = "Key-value pairs of variables that must be used to execute the Terraform request.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("variables")
  public Map<String, Object> getVariables() {
    return variables;
  }

  public void setVariables(Map<String, Object> variables) {
    this.variables = variables;
  }

  public TerraformDeployRequest envVariables(Map<String, String> envVariables) {
    this.envVariables = envVariables;
    return this;
  }

  public TerraformDeployRequest putEnvVariablesItem(String key, String envVariablesItem) {
    if (this.envVariables == null) {
      this.envVariables = new HashMap<>();
    }
    this.envVariables.put(key, envVariablesItem);
    return this;
  }

  /**
   * Key-value pairs of variables that must be injected as environment variables to terraform process.
   * @return envVariables
  */
  
  @Schema(name = "envVariables", description = "Key-value pairs of variables that must be injected as environment variables to terraform process.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("envVariables")
  public Map<String, String> getEnvVariables() {
    return envVariables;
  }

  public void setEnvVariables(Map<String, String> envVariables) {
    this.envVariables = envVariables;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TerraformDeployRequest terraformDeployRequest = (TerraformDeployRequest) o;
    return Objects.equals(this.isPlanOnly, terraformDeployRequest.isPlanOnly) &&
        Objects.equals(this.variables, terraformDeployRequest.variables) &&
        Objects.equals(this.envVariables, terraformDeployRequest.envVariables);
  }

  @Override
  public int hashCode() {
    return Objects.hash(isPlanOnly, variables, envVariables);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TerraformDeployRequest {\n");
    sb.append("    isPlanOnly: ").append(toIndentedString(isPlanOnly)).append("\n");
    sb.append("    variables: ").append(toIndentedString(variables)).append("\n");
    sb.append("    envVariables: ").append(toIndentedString(envVariables)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

