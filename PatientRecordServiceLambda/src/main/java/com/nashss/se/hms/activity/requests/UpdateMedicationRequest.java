package com.nashss.se.hms.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;


/**
 * The UpdateMedicationRequest class represents a request to update medication details.
 */
@JsonDeserialize(builder = UpdateMedicationRequest.Builder.class)
public class UpdateMedicationRequest {
    private final String medicationId;
    private final String medicationName;
    private final String dosage;
    private final  String  startDate;
    private final String endDate;
    private final String instructions;
    private final  String patientId;

    /**
     * Represents a medication update request.
     * @param medicationId   the ID of the medication
     * @param medicationName the name of the medication
     * @param dosage         the dosage of the medication
     * @param startDate      the start date of the medication
     * @param endDate        the end date of the medication
     * @param instructions   the instructions for taking the medication
     * @param patientId      the ID of the patient
     */
    private UpdateMedicationRequest(String medicationId, String medicationName, String dosage, String startDate,
                                   String endDate, String instructions, String patientId) {
        this.medicationId = medicationId;
        this.medicationName = medicationName;
        this.dosage = dosage;
        this.startDate = startDate;
        this.endDate = endDate;
        this.instructions = instructions;
        this.patientId = patientId;
    }

    public String getMedicationId() {
        return medicationId;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public String getDosage() {
        return dosage;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getInstructions() {
        return instructions;
    }

    public String getPatientId() {
        return patientId;
    }

    @Override
    public String toString() {
        return "UpdateMedicationRequest{" +
                "medicationId='" + medicationId + '\'' +
                ", medicationName='" + medicationName + '\'' +
                ", dosage='" + dosage + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", instructions='" + instructions + '\'' +
                ", patientId='" + patientId + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder(){
        return new UpdateMedicationRequest.Builder();
    }

    @JsonPOJOBuilder
    public static class Builder{

        private String medicationId;
        private String medicationName;
        private String dosage;
        private String  startDate;
        private String endDate;
        private String instructions;
        private String patientId;

        public  Builder withMedicationId(String medicationId){
            this.medicationId = medicationId;
            return this;
        }

        public  Builder withMedicationName(String medicationName){
            this.medicationName = medicationName;
            return this;
        }

        public  Builder withDosage(String dosage){
            this.dosage = dosage;
            return this;
        }
        public  Builder withStartDate(String startDate){
            this.startDate = startDate;
            return this;
        }

        public  Builder withEndDated(String endDate){
            this.endDate = endDate;
            return this;
        }
        public  Builder withInstructions(String instructions){
            this.instructions =  instructions;
            return this;
        }
        public  Builder withPatientId(String patientId){
            this.patientId = patientId;
            return this;
        }

        public UpdateMedicationRequest build(){
            return  new  UpdateMedicationRequest (medicationId, medicationName, dosage, startDate, endDate,instructions, patientId);
        }



    }

}

