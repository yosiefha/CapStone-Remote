package com.nashss.se.hms.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Objects;


/**
 * Represents a patient in the system.
 * <p>
 * This class is annotated with the @DynamoDBTable annotation to map it to the "patients" table in the database.
 * It also defines the attributes required for a patient, such as patientId, firstName, lastName, dateOfBirth,
 * contactNumber, emailAddress, and address.
 * <p>
 * The class provides getters and setters for each attribute, allowing access to and modification of the patient
 * details.
 */
@DynamoDBTable(tableName = "patients")
public class Patient {
    public static final String SEARCH_ByNAME_INDEX = "SearchByNameIndex";
    private String patientId;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String contactNumber;
    private String emailAddress;
    private String address;

    /**
     * Retrieves the patient ID associated with the patient.
     *
     * @return the patient ID
     */
    @DynamoDBHashKey(attributeName = "patientId")
    public String getPatientId() {
        return patientId;
    }

    /**
     * Sets the patient ID for the patient.
     *
     * @param patientId the patient ID to set
     */
    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    /**
     * Retrieves the first name of the patient.
     *
     * @return the first name of the patient
     */
    @DynamoDBIndexRangeKey(globalSecondaryIndexName = SEARCH_ByNAME_INDEX, attributeName = "firstName")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @DynamoDBIndexHashKey(globalSecondaryIndexNames = SEARCH_ByNAME_INDEX, attributeName = "lastName")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Patient)) {
            return false;
        }
        Patient patient = (Patient) o;
        return Objects.equals(getPatientId(), patient.getPatientId()) && Objects.equals(getFirstName(), patient.getFirstName()) && Objects.equals(getLastName(), patient.getLastName()) && Objects.equals(getDateOfBirth(), patient.getDateOfBirth()) && Objects.equals(getContactNumber(), patient.getContactNumber()) && Objects.equals(getEmailAddress(), patient.getEmailAddress()) && Objects.equals(getAddress(), patient.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPatientId(), getFirstName(), getLastName(), getDateOfBirth(), getContactNumber(), getEmailAddress(), getAddress());
    }
}
