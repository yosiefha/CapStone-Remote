package com.nashss.se.hms.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.nashss.se.hms.dynamodb.models.Diagnosis;
import com.nashss.se.hms.exceptions.DiagnosisNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.mockito.internal.verification.VerificationModeFactory.times;

class DiagnosisDAOTest {

    @Mock
    private DynamoDBMapper mockDynamoDBMapper;



    private DiagnosisDAO diagnosisDAO;



    @BeforeEach
    void setup() {
        openMocks(this);
        mockDynamoDBMapper = Mockito.mock(DynamoDBMapper.class);
        diagnosisDAO = new DiagnosisDAO(mockDynamoDBMapper);
    }


    @Test
    void savePatientDiagnoses_happyPath() {
        // Given
        String diagnosisId = "123";
        DynamoDBMapper mockDynamoDBMapper = mock(DynamoDBMapper.class);
        DiagnosisDAO diagnosisDAO = new DiagnosisDAO(mockDynamoDBMapper);
        Diagnosis diagnosisToSave = new Diagnosis();
        diagnosisToSave.setDiagnosisId(diagnosisId);


        doNothing().when(mockDynamoDBMapper).save(any(Diagnosis.class));

        // When
        Diagnosis savedDiagnosis = diagnosisDAO.savePatientDiagnoses(diagnosisToSave);

        // Then
        assertNotNull(savedDiagnosis);
        assertEquals(diagnosisToSave, savedDiagnosis);
        verify(mockDynamoDBMapper, times(1)).save(eq(diagnosisToSave));


    }

    @Test
    void getDiagnosis_happyPath() {

        String diagnosisId = "123";
        String goalName = "goalName";
        when(mockDynamoDBMapper.load(Diagnosis.class, diagnosisId)).thenReturn(new Diagnosis());

        Diagnosis diagnosis = diagnosisDAO.getDiagnosis(diagnosisId);

        assertNotNull(diagnosis);
        verify(mockDynamoDBMapper, times(1)).load(eq(Diagnosis.class), eq(diagnosisId));


    }

    @Test
    void getDiagnoses_happyPath() {
        // Given
        String patientId = "123";
        DynamoDBMapper mockDynamoDBMapper = mock(DynamoDBMapper.class);
        DiagnosisDAO diagnosisDAO = new DiagnosisDAO(mockDynamoDBMapper);

        PaginatedQueryList<Diagnosis> paginatedQueryList = mock(PaginatedQueryList.class);


        // Stubbing the query method to return a list of diagnoses (happy path scenario)

        when(mockDynamoDBMapper.query(eq(Diagnosis.class), any(DynamoDBQueryExpression.class)))
                .thenReturn(paginatedQueryList);
        // When
        List<Diagnosis> diagnoses = diagnosisDAO.getDiagnoses(patientId);

        // Then
        assertNotNull(diagnoses);
        assertFalse(diagnoses.isEmpty());
        verify(mockDynamoDBMapper, times(1)).query(eq(Diagnosis.class), any(DynamoDBQueryExpression.class));
    }


    @Test
    void deleteDiagnosis_happyPath() {
        // Given
        DynamoDBMapper mockDynamoDBMapper = mock(DynamoDBMapper.class);
        DiagnosisDAO diagnosisDAO = new DiagnosisDAO(mockDynamoDBMapper);
        Diagnosis diagnosisToDelete = new Diagnosis();
        // When
        diagnosisDAO.deleteDiagnosis(diagnosisToDelete);

        // Then
        // Verification
        verify(mockDynamoDBMapper, times(1)).delete(eq(diagnosisToDelete));

    }

    @Test
    void deleteDiagnosis_diagnosisNotFound() {
        // Given
        DynamoDBMapper mockDynamoDBMapper = mock(DynamoDBMapper.class);
        DiagnosisDAO diagnosisDAO = new DiagnosisDAO(mockDynamoDBMapper);
        Diagnosis diagnosisToDelete = new Diagnosis(); // Create or set up a Diagnosis instance for deletion

        // Stubbing the delete method to throw an exception (e.g., NotFoundException) when the diagnosis is not found
        doThrow(DiagnosisNotFoundException.class).when(mockDynamoDBMapper).delete(eq(diagnosisToDelete));

        // When and Then
        assertThrows(DiagnosisNotFoundException.class, () -> {
            diagnosisDAO.deleteDiagnosis(diagnosisToDelete);
        });
        // Verification
        verify(mockDynamoDBMapper, times(1)).delete(eq(diagnosisToDelete));

    }

    @Test
    void deleteDiagnosisBatch_happyPath() {
        // Given
        DynamoDBMapper mockDynamoDBMapper = mock(DynamoDBMapper.class);
        DiagnosisDAO diagnosisDAO = new DiagnosisDAO(mockDynamoDBMapper);
        Diagnosis diagnosis1 = new Diagnosis();
        diagnosis1.setDiagnosisId("id1");
        Diagnosis diagnosis2 = new Diagnosis();
        diagnosis2.setDiagnosisId("id2");
        Diagnosis diagnosis3 = new Diagnosis();
        diagnosis3.setDiagnosisId("id3");
        List<Diagnosis> diagnosisListToDelete = Arrays.asList(
                diagnosis1,
                diagnosis2,
                diagnosis3
        ); // Create or set up a list of Diagnosis instances for deletion

        // When
        diagnosisDAO.deleteDiagnosisBatch(diagnosisListToDelete);

        // Then
        // Verification
        verify(mockDynamoDBMapper, times(1)).batchDelete(eq(diagnosisListToDelete));

    }

    @Test
    void deleteDiagnosisBatch_emptyList() {
        // Given
        DynamoDBMapper mockDynamoDBMapper = mock(DynamoDBMapper.class);
        DiagnosisDAO diagnosisDAO = new DiagnosisDAO(mockDynamoDBMapper);
        List<Diagnosis> emptyDiagnosisList = Collections.emptyList(); // Empty list

        // When
        diagnosisDAO.deleteDiagnosisBatch(emptyDiagnosisList);

        // Then
        // Verification
        // Ensure that the batchDelete method is not called with an empty list
        verify(mockDynamoDBMapper).batchDelete(anyList());

    }
    @Test
    void deleteDiagnosisBatch_exception() {
        // Given
        DynamoDBMapper mockDynamoDBMapper = mock(DynamoDBMapper.class);
        DiagnosisDAO diagnosisDAO = new DiagnosisDAO(mockDynamoDBMapper);
        Diagnosis diagnosis1 = new Diagnosis();
        diagnosis1.setDiagnosisId("id1");
        Diagnosis diagnosis3 = new Diagnosis();
        diagnosis3.setDiagnosisId("id3");
        List<Diagnosis> diagnosisListWithNull = Arrays.asList(
                diagnosis1,
                null,
                diagnosis3
        ); // Create or set

        // When and Then
        assertThrows(IllegalArgumentException.class, () -> {
            diagnosisDAO.deleteDiagnosisBatch(diagnosisListWithNull);
        });
        // Verification
        // Ensure that the batchDelete method is not called when the list contains null values
        verify(mockDynamoDBMapper, never()).batchDelete(anyList());
        // Add more assertions or verifications based on the expected behavior of the method
    }


}