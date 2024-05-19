package com.nashss.se.hms.dynamodb;
import com.nashss.se.hms.dynamodb.models.Diagnosis;
import com.nashss.se.hms.exceptions.DiagnosisNotFoundException;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

import static com.nashss.se.hms.dynamodb.models.Diagnosis.SEARCH_ByPatientId_INDEX;


/**
 * The DiagnosisDAO class provides methods to interact with the diagnoses table in DynamoDB.
 *
 * This class is responsible for saving, retrieving, and deleting diagnoses in the medical system.
 * It uses the DynamoDBMapper to perform the database operations.
 */
public class DiagnosisDAO {

    private final DynamoDBMapper dynamoDBMapper1;

    /**
     * @param dynamoDbMapper a mapper.
     */
    @Inject
    public DiagnosisDAO(DynamoDBMapper dynamoDbMapper) {
        this.dynamoDBMapper1 = dynamoDbMapper;
    }

    /**
     * Saves a patient diagnosis in the medical system.
     *
     * @param diagnosis The diagnosis object to be saved.
     * @return The saved diagnosis object.
     */
    public Diagnosis savePatientDiagnoses(Diagnosis diagnosis) {
        this.dynamoDBMapper1.save(diagnosis);
        return diagnosis;
    }

    /**
     * Retrieves a list of diagnoses for a given patient.
     *
     * @param patientId The ID of the patient.
     * @return A list of Diagnosis objects.
     */
    public List<Diagnosis> getDiagnoses(String patientId) {

        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":patientId", new AttributeValue().withS(patientId));
        DynamoDBQueryExpression<Diagnosis> queryExpression = new DynamoDBQueryExpression<Diagnosis>()
                .withIndexName(SEARCH_ByPatientId_INDEX)
                .withConsistentRead(false)
                .withKeyConditionExpression("patientId = :patientId")
                .withExpressionAttributeValues(valueMap);

        return this.dynamoDBMapper1.query(Diagnosis.class, queryExpression);

    }

    /**
     * Retrieves a Diagnosis object based on the provided diagnosisId.
     *
     * @param diagnosisId The ID of the diagnosis.
     * @return The Diagnosis object with the given diagnosisId.
     * @throws DiagnosisNotFoundException If no diagnosis is found with the given diagnosisId.
     */
    public Diagnosis getDiagnosis(String diagnosisId) {
        Diagnosis diagnosis = this.dynamoDBMapper1.load(Diagnosis.class, diagnosisId);
        if (diagnosis == null) {
            throw new DiagnosisNotFoundException("Could not find diagnosis with id " + diagnosisId);
        }
        return diagnosis;
    }

    /**
     * Deletes a diagnosis from the medical system.
     *
     * @param diagnosis The diagnosis object to be deleted.
     */
    public void deleteDiagnosis(Diagnosis diagnosis) {

        dynamoDBMapper1.delete(diagnosis);
    }

    /**
     * Deletes a batch of diagnoses from the medical system.
     *
     * @param diagnosisList The list of Diagnosis objects to be deleted. Must not contain null values.
     * @throws IllegalArgumentException if the input list contains null values.
     */
    public void deleteDiagnosisBatch(List<Diagnosis> diagnosisList) {
        if (diagnosisList != null && diagnosisList.contains(null)) {
            throw new IllegalArgumentException("Input list must not contain null values");
        }

        dynamoDBMapper1.batchDelete(diagnosisList);
    }


}
