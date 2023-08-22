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
 * Configuration information of webhook.
 */

@Schema(name = "WebhookConfig", description = "Configuration information of webhook.")
public class WebhookConfig {

  private String url;

  /**
   * The permission type when calling back.
   */
  public enum AuthTypeEnum {
    NONE("NONE"),
    
    OAUTH2("OAUTH2");

    private String value;

    AuthTypeEnum(String value) {
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
    public static AuthTypeEnum fromValue(String value) {
      for (AuthTypeEnum b : AuthTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private AuthTypeEnum authType;

  /**
   * Default constructor
   * @deprecated Use {@link WebhookConfig#WebhookConfig(String, AuthTypeEnum)}
   */
  @Deprecated
  public WebhookConfig() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public WebhookConfig(String url, AuthTypeEnum authType) {
    this.url = url;
    this.authType = authType;
  }

  public WebhookConfig url(String url) {
    this.url = url;
    return this;
  }

  /**
   * Callback address after deployment/destroy is completed.
   * @return url
  */
  @NotNull 
  @Schema(name = "url", description = "Callback address after deployment/destroy is completed.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("url")
  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public WebhookConfig authType(AuthTypeEnum authType) {
    this.authType = authType;
    return this;
  }

  /**
   * The permission type when calling back.
   * @return authType
  */
  @NotNull 
  @Schema(name = "authType", description = "The permission type when calling back.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("authType")
  public AuthTypeEnum getAuthType() {
    return authType;
  }

  public void setAuthType(AuthTypeEnum authType) {
    this.authType = authType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WebhookConfig webhookConfig = (WebhookConfig) o;
    return Objects.equals(this.url, webhookConfig.url) &&
        Objects.equals(this.authType, webhookConfig.authType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(url, authType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class WebhookConfig {\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
    sb.append("    authType: ").append(toIndentedString(authType)).append("\n");
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

