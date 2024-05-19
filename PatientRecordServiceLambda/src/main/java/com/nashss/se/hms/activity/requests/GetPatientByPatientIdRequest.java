package com.nashss.se.hms.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;


/**
 * This class represents a request to get a patient by their ID.
 */
@JsonDeserialize(builder = GetPatientByPatientIdRequest.Builder.class)
public class GetPatientByPatientIdRequest {

    private final String patientId;

    /**
     * Creates a new instance of GetPatientByPatientIdRequest with the given patient ID.
     *
     * @param patientId the ID of the patient
     */
    private GetPatientByPatientIdRequest(String patientId) {
        this.patientId = patientId;
    }

    public String getPatientId() {
        return patientId;
    }

    @Override
    public String toString() {
        return "getPatientByPatientIdRequest{" +
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

        public GetPatientByPatientIdRequest build() {
            return new GetPatientByPatientIdRequest(patientId);
        }
    }
}
