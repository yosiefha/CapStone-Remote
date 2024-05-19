package com.nashss.se.hms.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

/**
 * Represents a request to update a diagnosis.
 */
@JsonDeserialize(builder = UpdateDiagnosisRequest.Builder.class)
public class UpdateDiagnosisRequest {

    private final String diagnosisId;
    private final String description;
    private final String dateCreated;
    private final String healthcareProfessionalId;
    private final String patientId;

    /**
     * Constructs a new UpdateDiagnosisRequest object with the specified parameters.
     * @param diagnosisId The ID of the diagnosis.
     * @param description The description of the diagnosis.
     * @param dateCreated The date when the diagnosis was created.
     * @param healthcareProfessionalId The ID of the healthcare professional associated with the diagnosis.
     * @param patientId The ID of the patient associated with the diagnosis.
     */
    private UpdateDiagnosisRequest(String diagnosisId, String description, String dateCreated,
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
        return "UpdateDiagnosisRequest{" +
                "diagnosisId='" + diagnosisId + '\'' +
                ", description='" + description + '\'' +
                ", dateCreated='" + dateCreated + '\'' +
                ", healthcareProfessionalId='" + healthcareProfessionalId + '\'' +
                ", patientId='" + patientId + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder(){
        return new Builder();
    }
    @JsonPOJOBuilder
    public static class Builder{

        private String diagnosisId;
        private String description;
        private String dateCreated;
        private String healthcareProfessionalId;
        private String patientId;

        public Builder withDiagnosisId(String diagnosisId){
            this.diagnosisId = diagnosisId;
            return this;
        }

        public Builder withDescription(String description){
            this.description = description;
            return this;
        }

        public Builder withDateCreated(String dateCreated){
            this.dateCreated =dateCreated;
            return this;
        }
        public Builder withHealthcareProfessionalId(String healthcareProfessionalId){
            this.healthcareProfessionalId = healthcareProfessionalId;
            return this;
        }

        public Builder withPatientId(String patientId){
            this.patientId = patientId;
            return this;
        }

        public UpdateDiagnosisRequest build(){
            return new UpdateDiagnosisRequest( diagnosisId,  description, dateCreated,healthcareProfessionalId, patientId);
        }


    }


}
