package com.nashss.se.hms.models;

import java.util.Objects;

/**
 * The MedicationModel class represents a medication object with its details.
 * It provides methods to retrieve the medication details.
 */
public class MedicationModel {

    private final String medicationId;
    private final String medicationName;
    private final String dosage;
    private final String startDate;
    private final String endDate;
    private final String instructions;
    private final String patientId;

    /**
     * Represents a medication in the application.
     * <p>
     * This class provides a model for storing and retrieving information about a medication.
     * It contains fields for medication ID, medication name, dosage, start date, end date,
     * instructions, and patient ID.
     * The class also provides getter methods for retrieving the values of these fields.
     *
     * @param medicationId   represents a unique ID of the medication.
     * @param medicationName represents the name of the medication.
     * @param dosage         represents the dosage of the medication.
     * @param startDate      represents the start date for the medication to take.
     * @param endDate        represents the end date for the medication to take.
     * @param instructions   Instruction on how to take the medication.
     * @param patientId      the ID of the patient taking the medication.
     *                       Usage example:
     *                       MedicationModel medication = new MedicationModel.Builder()
     *                       .withMedicationId("123")
     *                       .withMedicationName("Ibuprofen")
     *                       .withDosage("500mg")
     *                       .withStartDate("2021-01-01")
     *                       .withEndDate("2021-01-31")
     *                       .withInstructions("Take with food")
     *                       .withPatientId("456")
     *                       .build();
     */
    public MedicationModel(String medicationId, String medicationName, String dosage, String startDate,
                           String endDate, String instructions, String patientId) {
        this.medicationId = medicationId;
        this.medicationName = medicationName;
        this.dosage = dosage;
        this.startDate = startDate;
        this.endDate = endDate;
        this.instructions = instructions;
        this.patientId = patientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MedicationModel)) {
            return false;
        }
        MedicationModel that = (MedicationModel) o;
        return Objects.equals(medicationId, that.medicationId) && Objects.equals(medicationName, that.medicationName) &&
                Objects.equals(dosage, that.dosage) && Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate) && Objects.equals(instructions, that.instructions) &&
                Objects.equals(patientId, that.patientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(medicationId, medicationName, dosage, startDate, endDate, instructions, patientId);
    }

    /**
     * Retrieves the medication ID.
     *
     * @return The medication ID.
     */
    public String getMedicationId() {
        return medicationId;
    }

    /**
     * Retrieves the medication name.
     *
     * @return The medication name.
     */
    public String getMedicationName() {
        return medicationName;
    }

    /**
     * Retrieves the dosage of the medication.
     *
     * @return The dosage of the medication.
     */
    public String getDosage() {
        return dosage;
    }

    /**
     * Retrieves the start date of the medication.
     *
     * @return The start date of the medication.
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Retrieves the end date of the medication.
     *
     * @return The end date of the medication.
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Retrieves the instructions for the medication.
     *
     * @return The instructions for the medication.
     */
    public String getInstructions() {
        return instructions;
    }

    /**
     * Retrieves the patient ID associated with the medication.
     *
     * @return The patient ID.
     */
    public String getPatientId() {
        return patientId;
    }


    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String medicationId;
        private String medicationName;
        private String dosage;
        private String startDate;
        private String endDate;
        private String instructions;
        private String patientId;

        public Builder withMedicationId(String medicationId) {
            this.medicationId = medicationId;
            return this;
        }

        public Builder withMedicationName(String medicationName) {
            this.medicationName = medicationName;
            return this;
        }

        public Builder withStartDate(String startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder withEndDate(String endDate) {
            this.endDate = endDate;
            return this;
        }

        public Builder withInstructions(String instructions) {
            this.instructions = instructions;
            return this;
        }

        public Builder withDosage(String dosage) {
            this.dosage = dosage;
            return this;
        }

        public Builder withPatientId(String patientId) {
            this.patientId = patientId;
            return this;
        }

        public MedicationModel build() {
            return new MedicationModel(medicationId, medicationName, dosage, startDate, endDate, instructions, patientId);
        }
    }
}
