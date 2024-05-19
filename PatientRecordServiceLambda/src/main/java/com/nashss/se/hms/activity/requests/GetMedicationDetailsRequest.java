package com.nashss.se.hms.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;


/**
 * Represents a request to retrieve medication details for a patient.
 */
@JsonDeserialize(builder = GetMedicationDetailsRequest.Builder.class)
public class GetMedicationDetailsRequest {

    private final String patientId;

    /**
     * Creates a GetMedicationDetailsRequest object with the specified patient ID.
     * @param patientId the ID of the patient
     */
    private GetMedicationDetailsRequest(String patientId) {
        this.patientId = patientId;
    }

    public String getPatientId() {
        return patientId;
    }

    @Override
    public String toString() {
        return "GetMedicationDetailsRequest{" +
                "patientId='" + patientId + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String patientId;

        public Builder withPatientId(String patientId) {
            this.patientId = patientId;
            return this;
        }

        public GetMedicationDetailsRequest build() {
            return new GetMedicationDetailsRequest(patientId);
        }
    }

}
