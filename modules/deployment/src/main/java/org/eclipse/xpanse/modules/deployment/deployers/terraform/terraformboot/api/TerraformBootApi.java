/*
 * SPDX-License-Identifier: Apache-2.0
 * SPDX-FileCopyrightText: Huawei Inc.
 *
 */

package org.eclipse.xpanse.modules.deployment.deployers.terraform.terraformboot.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Optional;
import javax.validation.Valid;
import org.eclipse.xpanse.modules.deployment.deployers.terraform.terraformboot.model.Response;
import org.eclipse.xpanse.modules.deployment.deployers.terraform.terraformboot.model.SystemStatus;
import org.eclipse.xpanse.modules.deployment.deployers.terraform.terraformboot.model.TerraformAsyncDeployRequest;
import org.eclipse.xpanse.modules.deployment.deployers.terraform.terraformboot.model.TerraformAsyncDestroyRequest;
import org.eclipse.xpanse.modules.deployment.deployers.terraform.terraformboot.model.TerraformDeployRequest;
import org.eclipse.xpanse.modules.deployment.deployers.terraform.terraformboot.model.TerraformDestroyRequest;
import org.eclipse.xpanse.modules.deployment.deployers.terraform.terraformboot.model.TerraformResult;
import org.eclipse.xpanse.modules.deployment.deployers.terraform.terraformboot.model.TerraformValidationResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.NativeWebRequest;

@Validated
@Tag(name = "Terraform", description = "APIs for running Terraform commands")
public interface TerraformBootApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /terraform-boot/deploy/async/{module_directory}
     * async deploy resources via Terraform
     *
     * @param moduleDirectory directory name where the Terraform module files exist. (required)
     * @param terraformAsyncDeployRequest  (required)
     * @return Accepted (status code 202)
     *         or Bad Request (status code 400)
     *         or Unprocessable Entity (status code 422)
     *         or Bad Gateway (status code 502)
     */
    @Operation(
        operationId = "asyncDeploy",
        description = "async deploy resources via Terraform",
        tags = { "Terraform" },
        responses = {
            @ApiResponse(responseCode = "202", description = "Accepted"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                @Content(mediaType = "*/*", schema = @Schema(implementation = Response.class))
            }),
            @ApiResponse(responseCode = "422", description = "Unprocessable Entity", content = {
                @Content(mediaType = "*/*", schema = @Schema(implementation = Response.class))
            }),
            @ApiResponse(responseCode = "502", description = "Bad Gateway", content = {
                @Content(mediaType = "*/*", schema = @Schema(implementation = Response.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/terraform-boot/deploy/async/{module_directory}",
        produces = { "*/*" },
        consumes = { "application/json" }
    )
    default ResponseEntity<Void> asyncDeploy(
        @Parameter(name = "module_directory", description = "directory name where the Terraform module files exist.", required = true, in = ParameterIn.PATH) @PathVariable("module_directory") String moduleDirectory,
        @Parameter(name = "TerraformAsyncDeployRequest", description = "", required = true) @Valid @RequestBody
                TerraformAsyncDeployRequest terraformAsyncDeployRequest
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * DELETE /terraform-boot/destroy/async/{module_directory}
     * Async destroy the Terraform modules
     *
     * @param moduleDirectory directory name where the Terraform module files exist. (required)
     * @param terraformAsyncDestroyRequest  (required)
     * @return Accepted (status code 202)
     *         or Bad Request (status code 400)
     *         or Unprocessable Entity (status code 422)
     *         or Bad Gateway (status code 502)
     */
    @Operation(
        operationId = "asyncDestroy",
        description = "Async destroy the Terraform modules",
        tags = { "Terraform" },
        responses = {
            @ApiResponse(responseCode = "202", description = "Accepted"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                @Content(mediaType = "*/*", schema = @Schema(implementation = Response.class))
            }),
            @ApiResponse(responseCode = "422", description = "Unprocessable Entity", content = {
                @Content(mediaType = "*/*", schema = @Schema(implementation = Response.class))
            }),
            @ApiResponse(responseCode = "502", description = "Bad Gateway", content = {
                @Content(mediaType = "*/*", schema = @Schema(implementation = Response.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/terraform-boot/destroy/async/{module_directory}",
        produces = { "*/*" },
        consumes = { "application/json" }
    )
    default ResponseEntity<Void> asyncDestroy(
        @Parameter(name = "module_directory", description = "directory name where the Terraform module files exist.", required = true, in = ParameterIn.PATH) @PathVariable("module_directory") String moduleDirectory,
        @Parameter(name = "TerraformAsyncDestroyRequest", description = "", required = true) @Valid @RequestBody
                TerraformAsyncDestroyRequest terraformAsyncDestroyRequest
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * POST /terraform-boot/deploy/{module_directory}
     * Deploy resources via Terraform
     *
     * @param moduleDirectory directory name where the Terraform module files exist. (required)
     * @param terraformDeployRequest  (required)
     * @return OK (status code 200)
     *         or Bad Request (status code 400)
     *         or Unprocessable Entity (status code 422)
     *         or Bad Gateway (status code 502)
     */
    @Operation(
        operationId = "deploy",
        description = "Deploy resources via Terraform",
        tags = { "Terraform" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = TerraformResult.class)),
                @Content(mediaType = "*/*", schema = @Schema(implementation = TerraformResult.class))
            }),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Response.class)),
                @Content(mediaType = "*/*", schema = @Schema(implementation = Response.class))
            }),
            @ApiResponse(responseCode = "422", description = "Unprocessable Entity", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Response.class)),
                @Content(mediaType = "*/*", schema = @Schema(implementation = Response.class))
            }),
            @ApiResponse(responseCode = "502", description = "Bad Gateway", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Response.class)),
                @Content(mediaType = "*/*", schema = @Schema(implementation = Response.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/terraform-boot/deploy/{module_directory}",
        produces = { "application/json", "*/*" },
        consumes = { "application/json" }
    )
    default ResponseEntity<TerraformResult> deploy(
        @Parameter(name = "module_directory", description = "directory name where the Terraform module files exist.", required = true, in = ParameterIn.PATH) @PathVariable("module_directory") String moduleDirectory,
        @Parameter(name = "TerraformDeployRequest", description = "", required = true) @Valid @RequestBody
                TerraformDeployRequest terraformDeployRequest
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("*/*"))) {
                    String exampleString = "{ \"commandSuccessful\" : true, \"commandStdError\" : \"commandStdError\", \"commandStdOutput\" : \"commandStdOutput\" }";
                    ApiUtil.setExampleResponse(request, "*/*", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"commandSuccessful\" : true, \"commandStdError\" : \"commandStdError\", \"commandStdOutput\" : \"commandStdOutput\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * DELETE /terraform-boot/destroy/{module_directory}
     * Destroy the Terraform modules
     *
     * @param moduleDirectory directory name where the Terraform module files exist. (required)
     * @param terraformDestroyRequest  (required)
     * @return OK (status code 200)
     *         or Bad Request (status code 400)
     *         or Unprocessable Entity (status code 422)
     *         or Bad Gateway (status code 502)
     */
    @Operation(
        operationId = "destroy",
        description = "Destroy the Terraform modules",
        tags = { "Terraform" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = TerraformResult.class)),
                @Content(mediaType = "*/*", schema = @Schema(implementation = TerraformResult.class))
            }),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Response.class)),
                @Content(mediaType = "*/*", schema = @Schema(implementation = Response.class))
            }),
            @ApiResponse(responseCode = "422", description = "Unprocessable Entity", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Response.class)),
                @Content(mediaType = "*/*", schema = @Schema(implementation = Response.class))
            }),
            @ApiResponse(responseCode = "502", description = "Bad Gateway", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Response.class)),
                @Content(mediaType = "*/*", schema = @Schema(implementation = Response.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/terraform-boot/destroy/{module_directory}",
        produces = { "application/json", "*/*" },
        consumes = { "application/json" }
    )
    default ResponseEntity<TerraformResult> destroy(
        @Parameter(name = "module_directory", description = "directory name where the Terraform module files exist.", required = true, in = ParameterIn.PATH) @PathVariable("module_directory") String moduleDirectory,
        @Parameter(name = "TerraformDestroyRequest", description = "", required = true) @Valid @RequestBody
                TerraformDestroyRequest terraformDestroyRequest
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("*/*"))) {
                    String exampleString = "{ \"commandSuccessful\" : true, \"commandStdError\" : \"commandStdError\", \"commandStdOutput\" : \"commandStdOutput\" }";
                    ApiUtil.setExampleResponse(request, "*/*", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"commandSuccessful\" : true, \"commandStdError\" : \"commandStdError\", \"commandStdOutput\" : \"commandStdOutput\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /terraform-boot/health
     * Check health of Terraform API service
     *
     * @return OK (status code 200)
     *         or Bad Request (status code 400)
     *         or Unprocessable Entity (status code 422)
     *         or Bad Gateway (status code 502)
     */
    @Operation(
        operationId = "healthCheck",
        description = "Check health of Terraform API service",
        tags = { "Terraform" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = SystemStatus.class)),
                @Content(mediaType = "*/*", schema = @Schema(implementation = SystemStatus.class))
            }),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Response.class)),
                @Content(mediaType = "*/*", schema = @Schema(implementation = Response.class))
            }),
            @ApiResponse(responseCode = "422", description = "Unprocessable Entity", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Response.class)),
                @Content(mediaType = "*/*", schema = @Schema(implementation = Response.class))
            }),
            @ApiResponse(responseCode = "502", description = "Bad Gateway", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Response.class)),
                @Content(mediaType = "*/*", schema = @Schema(implementation = Response.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/terraform-boot/health",
        produces = { "application/json", "*/*" }
    )
    default ResponseEntity<SystemStatus> healthCheck(
        
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("*/*"))) {
                    String exampleString = "{ \"healthStatus\" : \"OK\" }";
                    ApiUtil.setExampleResponse(request, "*/*", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"healthStatus\" : \"OK\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /terraform-boot/validate/{module_directory}
     * Validate the Terraform modules
     *
     * @param moduleDirectory directory name where the Terraform module files exist. (required)
     * @return OK (status code 200)
     *         or Bad Request (status code 400)
     *         or Unprocessable Entity (status code 422)
     *         or Bad Gateway (status code 502)
     */
    @Operation(
        operationId = "validate",
        description = "Validate the Terraform modules",
        tags = { "Terraform" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = TerraformValidationResult.class)),
                @Content(mediaType = "*/*", schema = @Schema(implementation = TerraformValidationResult.class))
            }),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Response.class)),
                @Content(mediaType = "*/*", schema = @Schema(implementation = Response.class))
            }),
            @ApiResponse(responseCode = "422", description = "Unprocessable Entity", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Response.class)),
                @Content(mediaType = "*/*", schema = @Schema(implementation = Response.class))
            }),
            @ApiResponse(responseCode = "502", description = "Bad Gateway", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Response.class)),
                @Content(mediaType = "*/*", schema = @Schema(implementation = Response.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/terraform-boot/validate/{module_directory}",
        produces = { "application/json", "*/*" }
    )
    default ResponseEntity<TerraformValidationResult> validate(
        @Parameter(name = "module_directory", description = "directory name where the Terraform module files exist.", required = true, in = ParameterIn.PATH) @PathVariable("module_directory") String moduleDirectory
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("*/*"))) {
                    String exampleString = "{ \"valid\" : true, \"diagnostics\" : [ { \"detail\" : \"detail\" }, { \"detail\" : \"detail\" } ] }";
                    ApiUtil.setExampleResponse(request, "*/*", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"valid\" : true, \"diagnostics\" : [ { \"detail\" : \"detail\" }, { \"detail\" : \"detail\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
