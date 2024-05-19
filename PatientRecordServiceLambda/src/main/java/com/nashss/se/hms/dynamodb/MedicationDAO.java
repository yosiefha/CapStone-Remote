package com.nashss.se.hms.dynamodb;
import com.nashss.se.hms.dynamodb.models.Medication;
import com.nashss.se.hms.exceptions.MedicationNotFoundException;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

import static com.nashss.se.hms.dynamodb.models.Medication.SEARCH_ByPatientId_INDEX;


/**
 * The MedicationDAO class provides methods for interacting with the medication table in DynamoDB.
 */
public class MedicationDAO {

    private final DynamoDBMapper dynamoDBMapper;

    /**
     *
     * @param dynamoDbMapper a mapper to dynamoDB.
     */
    @Inject
    public MedicationDAO(DynamoDBMapper dynamoDbMapper) {
        this.dynamoDBMapper = dynamoDbMapper;
    }

    /**
     * Saves the details of a medication for a patient.
     *
     * @param medication the Medication object containing the medication details
     * @return the Medication object with the saved details
     */
    public Medication savePatientMedicationDetails(Medication medication) {
        this.dynamoDBMapper.save(medication);
        return medication;
    }

    /**
     * Retrieves a list of medications for a given patient.
     *
     * @param patientId the ID of the patient
     * @return a list of medications for the patient
     */
    public List<Medication> getMedications(String patientId) {

        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":patientId", new AttributeValue().withS(patientId));
        DynamoDBQueryExpression<Medication> queryExpression = new DynamoDBQueryExpression<Medication>()
                .withIndexName(SEARCH_ByPatientId_INDEX)
                .withConsistentRead(false)
                .withKeyConditionExpression("patientId = :patientId")
                .withExpressionAttributeValues(valueMap);

        return this.dynamoDBMapper.query(Medication.class, queryExpression);

    }

    /**
     * Retrieves a medication based on the medication ID.
     *
     * @param medicationId the ID of the medication
     * @return the Medication object with the specified medication ID
     * @throws MedicationNotFoundException if medication with the specified medication ID is not found
     */
    public Medication getMedication(String medicationId) {
        Medication medication = this.dynamoDBMapper.load(Medication.class, medicationId);
        if (medication == null) {
            throw new MedicationNotFoundException("Could not find medication with id " + medicationId);
        }
        return medication;
    }

    /**
     * Deletes a medication from the medication table in DynamoDB.
     *
     * @param medication the Medication object to be deleted
     */
    public void deleteMedication(Medication medication) {
        dynamoDBMapper.delete(medication);
    }

    /**
     * Deletes a batch of medications from the medication table in DynamoDB.
     *
     * @param medicationList the list of Medication objects to be deleted
     */
    public void deleteMedicationBatch(List<Medication> medicationList) {
        dynamoDBMapper.batchDelete(medicationList);
    }

}
