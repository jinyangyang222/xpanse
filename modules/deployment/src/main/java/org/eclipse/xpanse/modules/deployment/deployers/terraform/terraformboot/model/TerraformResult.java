/*
 * SPDX-License-Identifier: Apache-2.0
 * SPDX-FileCopyrightText: Huawei Inc.
 *
 */

package org.eclipse.xpanse.modules.deployment.deployers.terraform.terraformboot.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Objects;

/**
 * TerraformResult
 */

public class TerraformResult {

  private String commandStdOutput;

  private String commandStdError;

  private Boolean commandSuccessful;

  public TerraformResult commandStdOutput(String commandStdOutput) {
    this.commandStdOutput = commandStdOutput;
    return this;
  }

  /**
   * Get commandStdOutput
   * @return commandStdOutput
  */
  
  @Schema(name = "commandStdOutput", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("commandStdOutput")
  public String getCommandStdOutput() {
    return commandStdOutput;
  }

  public void setCommandStdOutput(String commandStdOutput) {
    this.commandStdOutput = commandStdOutput;
  }

  public TerraformResult commandStdError(String commandStdError) {
    this.commandStdError = commandStdError;
    return this;
  }

  /**
   * Get commandStdError
   * @return commandStdError
  */
  
  @Schema(name = "commandStdError", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("commandStdError")
  public String getCommandStdError() {
    return commandStdError;
  }

  public void setCommandStdError(String commandStdError) {
    this.commandStdError = commandStdError;
  }

  public TerraformResult commandSuccessful(Boolean commandSuccessful) {
    this.commandSuccessful = commandSuccessful;
    return this;
  }

  /**
   * Get commandSuccessful
   * @return commandSuccessful
  */
  
  @Schema(name = "commandSuccessful", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("commandSuccessful")
  public Boolean getCommandSuccessful() {
    return commandSuccessful;
  }

  public void setCommandSuccessful(Boolean commandSuccessful) {
    this.commandSuccessful = commandSuccessful;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TerraformResult terraformResult = (TerraformResult) o;
    return Objects.equals(this.commandStdOutput, terraformResult.commandStdOutput) &&
        Objects.equals(this.commandStdError, terraformResult.commandStdError) &&
        Objects.equals(this.commandSuccessful, terraformResult.commandSuccessful);
  }

  @Override
  public int hashCode() {
    return Objects.hash(commandStdOutput, commandStdError, commandSuccessful);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TerraformResult {\n");
    sb.append("    commandStdOutput: ").append(toIndentedString(commandStdOutput)).append("\n");
    sb.append("    commandStdError: ").append(toIndentedString(commandStdError)).append("\n");
    sb.append("    commandSuccessful: ").append(toIndentedString(commandSuccessful)).append("\n");
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

