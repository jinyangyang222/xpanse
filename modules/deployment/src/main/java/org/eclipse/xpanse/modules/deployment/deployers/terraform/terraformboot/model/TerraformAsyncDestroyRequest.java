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
 * TerraformAsyncDestroyRequest
 */

public class TerraformAsyncDestroyRequest {

  @Valid
  private Map<String, Object> variables = new HashMap<>();

  @Valid
  private Map<String, String> envVariables = new HashMap<>();

  private WebhookConfig webhookConfig;

  /**
   * Default constructor
   * @deprecated Use {@link TerraformAsyncDestroyRequest# TerraformAsyncDestroyRequest(Map<String, Object>, WebhookConfig)}
   */
  @Deprecated
  public TerraformAsyncDestroyRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public TerraformAsyncDestroyRequest(Map<String, Object> variables, WebhookConfig webhookConfig) {
    this.variables = variables;
    this.webhookConfig = webhookConfig;
  }

  public TerraformAsyncDestroyRequest variables(Map<String, Object> variables) {
    this.variables = variables;
    return this;
  }

  public TerraformAsyncDestroyRequest putVariablesItem(String key, Object variablesItem) {
    if (this.variables == null) {
      this.variables = new HashMap<>();
    }
    this.variables.put(key, variablesItem);
    return this;
  }

  /**
   * Key-value pairs of regular variables that must be used to execute the Terraform request.
   * @return variables
  */
  @NotNull 
  @Schema(name = "variables", description = "Key-value pairs of regular variables that must be used to execute the Terraform request.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("variables")
  public Map<String, Object> getVariables() {
    return variables;
  }

  public void setVariables(Map<String, Object> variables) {
    this.variables = variables;
  }

  public TerraformAsyncDestroyRequest envVariables(Map<String, String> envVariables) {
    this.envVariables = envVariables;
    return this;
  }

  public TerraformAsyncDestroyRequest putEnvVariablesItem(String key, String envVariablesItem) {
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

  public TerraformAsyncDestroyRequest webhookConfig(WebhookConfig webhookConfig) {
    this.webhookConfig = webhookConfig;
    return this;
  }

  /**
   * Get webhookConfig
   * @return webhookConfig
  */
  @NotNull @Valid 
  @Schema(name = "webhookConfig", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("webhookConfig")
  public WebhookConfig getWebhookConfig() {
    return webhookConfig;
  }

  public void setWebhookConfig(WebhookConfig webhookConfig) {
    this.webhookConfig = webhookConfig;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TerraformAsyncDestroyRequest terraformAsyncDestroyRequest = (TerraformAsyncDestroyRequest) o;
    return Objects.equals(this.variables, terraformAsyncDestroyRequest.variables) &&
        Objects.equals(this.envVariables, terraformAsyncDestroyRequest.envVariables) &&
        Objects.equals(this.webhookConfig, terraformAsyncDestroyRequest.webhookConfig);
  }

  @Override
  public int hashCode() {
    return Objects.hash(variables, envVariables, webhookConfig);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TerraformAsyncDestroyRequest {\n");
    sb.append("    variables: ").append(toIndentedString(variables)).append("\n");
    sb.append("    envVariables: ").append(toIndentedString(envVariables)).append("\n");
    sb.append("    webhookConfig: ").append(toIndentedString(webhookConfig)).append("\n");
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

