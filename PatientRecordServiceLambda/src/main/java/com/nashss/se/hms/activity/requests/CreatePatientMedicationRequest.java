package com.nashss.se.hms.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

/**
 * The CreatePatientMedicationRequest class represents a request object for creating a patient medication.
 * The class contains the following fields:
 * - medicationId: The ID of the medication (String).
 * - medicationName: The name of the medication (String).
 * - dosage: The dosage of the medication (String).
 * - startDate: The start date of the medication (String).
 * - endDate: The end date of the medication (String).
 * - instructions: The instructions for taking the medication (String).
 * - patientId: The ID of the patient (String).
 * The class provides getter methods for each field, as well as a toString() method for generating a
 * string representation of the object.
 * The class also contains a static Builder class for conveniently creating instances of
 * CreatePatientMedicationRequest.
 * The Builder class follows the builder pattern and provides methods for setting each field.
 * It also provides a build() method for creating the final CreatePatientMedicationRequest object.
 * Example usage:
 * - To create a CreatePatientMedicationRequest object using the Builder:
 *   CreatePatientMedicationRequest request = CreatePatientMedicationRequest.builder()
 *           .withMedicationId("123")
 *           .withMedicationName("Medication")
 *           .withDosage("10mg")
 *           .withStartDate("2021-01-01")
 *           .withEndDate("2021-01-30")
 *           .withInstructions("Take once daily")
 *           .withPatientId("456")
 *           .build();
 *
 * - To get the values of the fields:
 *   String medicationId = request.getMedicationId();
 *   String medicationName = request.getMedicationName();
 *   String dosage = request.getDosage();
 *   String startDate = request.getStartDate();
 *   String endDate = request.getEndDate();
 *   String instructions = request.getInstructions();
 *   String patientId = request.getPatientId();
 */
@JsonDeserialize(builder = CreatePatientMedicationRequest.Builder.class)
public class CreatePatientMedicationRequest {

    private final String medicationId;
    private final String medicationName;
    private final String dosage;
    private final String  startDate;
    private final String endDate;
    private final String instructions;
    private final String patientId;


    /**
     * Represents a request to create a patient medication record.
     * @param medicationId     the ID of the medication
     * @param medicationName   the name of the medication
     * @param dosage           the dosage of the medication
     * @param startDate        the start date of the medication
     * @param endDate          the end date of the medication
     * @param instructions     the instructions for taking the medication
     * @param patientId        the ID of the patient
     */
    private CreatePatientMedicationRequest(String medicationId, String medicationName, String dosage,
                                          String startDate, String endDate, String instructions,
                                          String patientId) {
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
        return "CreatePatientMedicationRequest{" +
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
    public static Builder builder() {
        return new Builder();
    }
    @JsonPOJOBuilder
    public static class Builder {

        private  String medicationId;
        private String medicationName;
        private String dosage;
        private  String  startDate;
        private String endDate;
        private String instructions;
        private String patientId;

        public Builder withMedicationId(String medicationId){
            this.medicationId = medicationId;
            return this;
        }
        public Builder withMedicationName(String medicationName){
            this.medicationName = medicationName;
            return this;
        }
        public Builder withDosage(String dosage){
            this.dosage = dosage;
            return this;
        }
        public Builder withStartDate(String startDate){
            this.startDate = startDate;
            return this;
        }
        public Builder withEndDate(String endDate){
            this.endDate = endDate;
            return this;
        }
        public Builder withInstructions(String instructions){
            this.instructions = instructions;
            return this;
        }
        public Builder withPatientId(String patientId){
            this.patientId = patientId;
            return this;
        }
        public CreatePatientMedicationRequest build(){
            return new CreatePatientMedicationRequest(medicationId,  medicationName, dosage, startDate, endDate, instructions, patientId);
        }

    }
}
