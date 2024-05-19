package com.nashss.se.hms.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Objects;

/**
 * Class representing a diagnosis in a medical system.
 * This class is annotated with @DynamoDBTable to specify the table name in DynamoDB.
 */
@DynamoDBTable(tableName = "diagnoses")
public class Diagnosis {
    public static final String SEARCH_ByPatientId_INDEX = "SearchByPatientIdIndex";
    private String diagnosisId;
    private String description;
    private String dateCreated;
    private String healthcareProfessionalId;
    private String patientId;

    /**
     * Get the diagnosis ID.
     *
     * @return the diagnosis ID as a String.
     */
    @DynamoDBHashKey(attributeName = "diagnosisId")
    public String getDiagnosisId() {
        return diagnosisId;
    }

    /**
     * Returns the description of the Diagnosis.
     *
     * @return the description of the Diagnosis as a String.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the date when the diagnosis was created.
     *
     * @return the date created as a string.
     */
    public String getDateCreated() {
        return dateCreated;
    }

    /**
     * Retrieves the healthcare professional ID associated with this diagnosis.
     *
     * @return The healthcare professional ID as a String.
     */
    public String getHealthcareProfessionalId() {
        return healthcareProfessionalId;
    }

    /**
     * Retrieves the patient ID associated with this Diagnosis.
     *
     * @return the patient ID as a String
     */

    @DynamoDBIndexHashKey(globalSecondaryIndexNames = SEARCH_ByPatientId_INDEX, attributeName = "patientId")
    public String getPatientId() {
        return patientId;
    }

    /**
     * Sets the diagnosis ID for a Diagnosis object.
     *
     * @param diagnosisId The diagnosis ID to set.
     */
    public void setDiagnosisId(String diagnosisId) {
        this.diagnosisId = diagnosisId;
    }

    /**
     * Sets the description of the Diagnosis.
     *
     * @param description The description to set. (String)
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the date when the diagnosis was created.
     *
     * @param dateCreated The date created to set. (String)
     */
    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     * Sets the healthcare professional ID for a Diagnosis object.
     *
     * @param healthcareProfessionalId The healthcare professional ID to set. (String)
     */
    public void setHealthcareProfessionalId(String healthcareProfessionalId) {
        this.healthcareProfessionalId = healthcareProfessionalId;
    }


    /**
     * Sets the patient ID for a Diagnosis object.
     *
     * @param patientId The patient ID to set.
     */
    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Diagnosis)) {
            return false;
        }
        Diagnosis diagnosis = (Diagnosis) o;
        return Objects.equals(getDiagnosisId(), diagnosis.getDiagnosisId()) && Objects.equals(getDescription(), diagnosis.getDescription()) && Objects.equals(getDateCreated(), diagnosis.getDateCreated()) && Objects.equals(getHealthcareProfessionalId(), diagnosis.getHealthcareProfessionalId()) && Objects.equals(getPatientId(), diagnosis.getPatientId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDiagnosisId(), getDescription(), getDateCreated(), getHealthcareProfessionalId(), getPatientId());
    }
}
