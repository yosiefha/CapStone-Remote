package com.nashss.se.hms.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

/**
 * The GetDiagnosisDetailsRequest class represents a request to get diagnosis details for a patient.
 * It contains the patient's ID.
 */
@JsonDeserialize(builder = GetDiagnosisDetailsRequest.Builder.class)
public class GetDiagnosisDetailsRequest {

    private final String patientId;

    /**
     * Creates a new instance of the GetDiagnosisDetailsRequest class.
     *
     * @param patientId The ID of the patient.
     */
    private GetDiagnosisDetailsRequest(String patientId) {
        this.patientId = patientId;
    }

    public String getPatientId() {
        return patientId;
    }

    @Override
    public String toString() {
        return "GetDiagnosisDetailsRequest{" +
                "patientId='" + patientId + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {

        private String patientId;

        public Builder withPatientId(String patientId) {
            this.patientId = patientId;
            return this;
        }

        public GetDiagnosisDetailsRequest build() {
            return new GetDiagnosisDetailsRequest(patientId);
        }
    }
}
