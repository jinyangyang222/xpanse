/*
 * SPDX-License-Identifier: Apache-2.0
 * SPDX-FileCopyrightText: Huawei Inc.
 *
 */

package org.eclipse.xpanse.modules.deployment.deployers.terraform.terraformboot.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Objects;
import javax.validation.constraints.NotNull;

/**
 * SystemStatus
 */

public class SystemStatus {

  /**
   * The health status of Xpanse api service.
   */
  public enum HealthStatusEnum {
    OK("OK"),
    
    NOK("NOK");

    private String value;

    HealthStatusEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static HealthStatusEnum fromValue(String value) {
      for (HealthStatusEnum b : HealthStatusEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private HealthStatusEnum healthStatus;

  /**
   * Default constructor
   * @deprecated Use {@link SystemStatus#SystemStatus(HealthStatusEnum)}
   */
  @Deprecated
  public SystemStatus() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public SystemStatus(HealthStatusEnum healthStatus) {
    this.healthStatus = healthStatus;
  }

  public SystemStatus healthStatus(HealthStatusEnum healthStatus) {
    this.healthStatus = healthStatus;
    return this;
  }

  /**
   * The health status of Xpanse api service.
   * @return healthStatus
  */
  @NotNull 
  @Schema(name = "healthStatus", description = "The health status of Xpanse api service.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("healthStatus")
  public HealthStatusEnum getHealthStatus() {
    return healthStatus;
  }

  public void setHealthStatus(HealthStatusEnum healthStatus) {
    this.healthStatus = healthStatus;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SystemStatus systemStatus = (SystemStatus) o;
    return Objects.equals(this.healthStatus, systemStatus.healthStatus);
  }

  @Override
  public int hashCode() {
    return Objects.hash(healthStatus);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SystemStatus {\n");
    sb.append("    healthStatus: ").append(toIndentedString(healthStatus)).append("\n");
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

