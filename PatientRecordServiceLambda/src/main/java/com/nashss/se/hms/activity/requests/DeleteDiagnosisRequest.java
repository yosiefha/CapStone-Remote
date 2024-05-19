package com.nashss.se.hms.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

/**
 * Represents a request to delete a diagnosis.
 */
@JsonDeserialize(builder = DeleteDiagnosisRequest.Builder.class)
public class DeleteDiagnosisRequest {
    private final String diagnosisId;

    /**
     * Creates a new DeleteDiagnosisRequest with the provided diagnosisId.
     *
     * @param diagnosisId the ID of the diagnosis to be deleted
     */
    private DeleteDiagnosisRequest(String diagnosisId) {
        this.diagnosisId = diagnosisId;
    }

    /**
     * Retrieves the diagnosis ID.
     *
     * @return The diagnosis ID.
     */
    public String getDiagnosisId() {
        return diagnosisId;
    }

    @Override
    public String toString() {
        return "DeleteDiagnosisRequest{" +
                "diagnosisId='" + diagnosisId + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String diagnosisId;

        public Builder withDiagnosisId(String diagnosisId) {
            this.diagnosisId = diagnosisId;
            return this;
        }

        public DeleteDiagnosisRequest build() {

            return new DeleteDiagnosisRequest(diagnosisId);
        }
    }
}
