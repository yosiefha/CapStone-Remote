package com.nashss.se.hms.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Objects;


/**
 * The Medication class represents a medication record in the database.
 * It contains information such as medicationId, medicationName, dosage, startDate, endDate,
 * instructions, and patientId.
 * <p>
 * This class also includes annotations to map the class and its attributes
 * to a DynamoDB table and its corresponding attributes.
 */
@DynamoDBTable(tableName = "medications")
public class Medication {

    public static final String SEARCH_ByPatientId_INDEX = "SearchByPatientIdIndex";
    private String medicationId;
    private String medicationName;
    private String dosage;
    private String startDate;
    private String endDate;
    private String instructions;
    private String patientId;

    /**
     * Retrieves the medication ID.
     *
     * @return the medication ID
     */
    @DynamoDBHashKey(attributeName = "medicationId")
    public String getMedicationId() {
        return medicationId;
    }

    /**
     * Sets the medication ID of the Medication.
     *
     * @param medicationId the medication ID to be set
     */
    public void setMedicationId(String medicationId) {
        this.medicationId = medicationId;
    }

    /**
     * Retrieves the name of the medication.
     *
     * @return the medication name
     */
    public String getMedicationName() {
        return medicationName;
    }


    /**
     * Sets the medication name.
     *
     * @param medicationName the medication name to be set
     */
    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    /**
     * Retrieves the dosage of the medication.
     *
     * @return the dosage of the medication
     */
    public String getDosage() {
        return dosage;
    }

    /**
     * Sets the dosage of the medication.
     *
     * @param dosage the dosage to be set
     */
    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    /**
     * Retrieves the start date of the medication.
     *
     * @return the start date of the medication
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Sets the start date of the medication.
     *
     * @param startDate the start date to be set
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * Retrieves the end date of the medication.
     *
     * @return the end date of the medication
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Sets the end date of the medication.
     *
     * @param endDate the end date to be set
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * Retrieves the instructions of the medication.
     *
     * @return the instructions of the medication
     */
    public String getInstructions() {
        return instructions;
    }

    /**
     * Sets the instructions of the medication.
     *
     * @param instructions The instructions to be set for the medication.
     */
    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }


    /**
     * Retrieves the patient ID.
     *
     * @return the patient ID
     */
    @DynamoDBIndexHashKey(globalSecondaryIndexNames = SEARCH_ByPatientId_INDEX, attributeName = "patientId")
    public String getPatientId() {
        return patientId;
    }

    /**
     * Sets the patient ID for the Medication.
     *
     * @param patientId The patient ID to be set
     */
    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Medication)) {
            return false;
        }
        Medication that = (Medication) o;
        return Objects.equals(getMedicationId(), that.getMedicationId()) && Objects.equals(getMedicationName(), that.getMedicationName()) && Objects.equals(getDosage(), that.getDosage()) && Objects.equals(getStartDate(), that.getStartDate()) && Objects.equals(getEndDate(), that.getEndDate()) && Objects.equals(getInstructions(), that.getInstructions()) && Objects.equals(getPatientId(), that.getPatientId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMedicationId(), getMedicationName(), getDosage(), getStartDate(), getEndDate(), getInstructions(), getPatientId());
    }
}
