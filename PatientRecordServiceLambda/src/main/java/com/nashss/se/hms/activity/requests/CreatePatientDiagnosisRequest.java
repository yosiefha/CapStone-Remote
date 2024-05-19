package com.nashss.se.hms.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

/**
 * Represents a request to create a patient diagnosis.
 */
@JsonDeserialize(builder = CreatePatientDiagnosisRequest.Builder.class)
public class CreatePatientDiagnosisRequest {
    private final String diagnosisId;
    private final String description;
    private final String dateCreated;
    private final String healthcareProfessionalId;
    private final String patientId;

    /**
     * Constructs a new instance of CreatePatientDiagnosisRequest with the provided parameters.
     * @param diagnosisId               the diagnosis ID
     * @param description               the diagnosis description
     * @param dateCreated               the date when the diagnosis was created
     * @param healthcareProfessionalId the ID of the healthcare professional
     * @param patientId                 the ID of the patient
     */
    private CreatePatientDiagnosisRequest(String diagnosisId, String description, String dateCreated,
                                         String healthcareProfessionalId, String patientId) {
        this.diagnosisId = diagnosisId;
        this.description = description;
        this.dateCreated = dateCreated;
        this.healthcareProfessionalId = healthcareProfessionalId;
        this.patientId = patientId;
    }

    public String getDiagnosisId() {
        return diagnosisId;
    }

    public String getDescription() {
        return description;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public String getHealthcareProfessionalId() {
        return healthcareProfessionalId;
    }

    public String getPatientId() {
        return patientId;
    }

    @Override
    public String toString() {
        return "CreatePatientDiagnosisRequest{" +
                "diagnosisId='" + diagnosisId + '\'' +
                ", description='" + description + '\'' +
                ", dateCreated='" + dateCreated + '\'' +
                ", healthcareProfessionalId='" + healthcareProfessionalId + '\'' +
                ", patientId='" + patientId + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new CreatePatientDiagnosisRequest.Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {

        private String diagnosisId;
        private String description;
        private String dateCreated;
        private String healthcareProfessionalId;
        private String patientId;


        public Builder withDiagnosisId(String diagnosisId) {
            this.diagnosisId = diagnosisId;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withDateCreated(String dateCreated) {
            this.dateCreated = dateCreated;
            return this;
        }

        public Builder withHealthcareProfessionalId(String healthcareProfessionalId) {
            this.healthcareProfessionalId = healthcareProfessionalId;
            return this;
        }

        public Builder withPatientId(String patientId) {
            this.patientId = patientId;
            return this;
        }

        public CreatePatientDiagnosisRequest build() {
            return new CreatePatientDiagnosisRequest(diagnosisId, description, dateCreated, healthcareProfessionalId, patientId);
        }


    }

}
