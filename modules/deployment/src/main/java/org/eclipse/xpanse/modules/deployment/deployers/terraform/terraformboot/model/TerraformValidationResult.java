/*
 * SPDX-License-Identifier: Apache-2.0
 * SPDX-FileCopyrightText: Huawei Inc.
 *
 */

package org.eclipse.xpanse.modules.deployment.deployers.terraform.terraformboot.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.validation.Valid;


/**
 * TerraformValidationResult
 */

public class TerraformValidationResult {

    private Boolean valid;

    @Valid
    private List<TerraformValidateDiagnostics> diagnostics;

    public TerraformValidationResult valid(Boolean valid) {
        this.valid = valid;
        return this;
    }

    /**
     * Get valid
     *
     * @return valid
     */

    @Schema(name = "valid", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("valid")
    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public TerraformValidationResult diagnostics(
            List<TerraformValidateDiagnostics> diagnostics) {
        this.diagnostics = diagnostics;
        return this;
    }

    public TerraformValidationResult addDiagnosticsItem(
            TerraformValidateDiagnostics diagnosticsItem) {
        if (this.diagnostics == null) {
            this.diagnostics = new ArrayList<>();
        }
        this.diagnostics.add(diagnosticsItem);
        return this;
    }

    /**
     * Get diagnostics
     *
     * @return diagnostics
     */
    @Valid
    @Schema(name = "diagnostics", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("diagnostics")
    public List<TerraformValidateDiagnostics> getDiagnostics() {
        return diagnostics;
    }

    public void setDiagnostics(List<TerraformValidateDiagnostics> diagnostics) {
        this.diagnostics = diagnostics;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TerraformValidationResult terraformValidationResult = (TerraformValidationResult) o;
        return Objects.equals(this.valid, terraformValidationResult.valid) &&
                Objects.equals(this.diagnostics, terraformValidationResult.diagnostics);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valid, diagnostics);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TerraformValidationResult {\n");
        sb.append("    valid: ").append(toIndentedString(valid)).append("\n");
        sb.append("    diagnostics: ").append(toIndentedString(diagnostics)).append("\n");
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

