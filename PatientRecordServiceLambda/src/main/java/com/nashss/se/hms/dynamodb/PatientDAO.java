package com.nashss.se.hms.dynamodb;
import com.nashss.se.hms.dynamodb.models.Patient;
import com.nashss.se.hms.exceptions.PatientNotFoundException;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

import static com.nashss.se.hms.dynamodb.models.Patient.SEARCH_ByNAME_INDEX;



/**
 * Represents a Data Access Object (DAO) for managing Patient entities in the database.
 */
public class PatientDAO {

    private final DynamoDBMapper dynamoDBMapper;

    /**
     * @param dynamoDbMapper a dynamoDb mapper.
     */
    @Inject
    public PatientDAO(DynamoDBMapper dynamoDbMapper) {
        this.dynamoDBMapper = dynamoDbMapper;
    }

    /**
     * Saves the patient object to the database.
     *
     * @param patient the patient object to be saved.
     * @return the saved patient object.
     */
    public Patient savePatient(Patient patient) {
        this.dynamoDBMapper.save(patient);
        return patient;
    }

    /**
     * Searches for patients with the given first name and last name.
     *
     * @param firstName the first name of the patients to search for.
     * @param lastName  the last name of the patients to search for.
     * @return a list of patients matching the given first name and last name.
     */
    public List<Patient> searchPatient(String firstName, String lastName) {

        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":firstName", new AttributeValue().withS(firstName));
        valueMap.put(":lastName", new AttributeValue().withS(lastName));
        DynamoDBQueryExpression<Patient> queryExpression = new DynamoDBQueryExpression<Patient>()
                .withIndexName(SEARCH_ByNAME_INDEX)
                .withConsistentRead(false)
                .withKeyConditionExpression("firstName = :firstName and lastName = :lastName")
                .withExpressionAttributeValues(valueMap);

        return this.dynamoDBMapper.query(Patient.class, queryExpression);
    }

    /**
     * Retrieves a patient from the database using the provided patient ID.
     *
     * @param patientId the ID of the patient to retrieve.
     * @return the Patient object corresponding to the provided patient ID.
     * @throws PatientNotFoundException if no patient is found with the given patient ID.
     */
    public Patient getPatientByPatientId(String patientId) {

        Patient patient = this.dynamoDBMapper.load(Patient.class, patientId);
        if (patient == null) {
            throw new PatientNotFoundException("Could not find Patient with id " + patientId);
        }
        return patient;


    }

    /**
     * Deletes a patient from the database.
     *
     * @param patient the patient object to be deleted.
     */
    public void deletePatient(Patient patient) {

        dynamoDBMapper.delete(patient);
    }

}
