package com.nashss.se.hms.dynamodb;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.nashss.se.hms.activity.GetMedicationDetailsActivity;
import com.nashss.se.hms.dynamodb.models.Medication;
import com.nashss.se.hms.exceptions.MedicationNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class MedicationDAOTest {

    @Mock
    private DynamoDBMapper mockDynamoDBMapper;


    @Mock
    private MedicationDAO medicationDAO;

    @InjectMocks
    private GetMedicationDetailsActivity getMedicationDetailsActivity;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        getMedicationDetailsActivity = new GetMedicationDetailsActivity(medicationDAO);
        mockDynamoDBMapper = mock(DynamoDBMapper.class);
        medicationDAO = new MedicationDAO(mockDynamoDBMapper);
    }

    @Test
    void getMedication_medicationIdNotFound_throwMedicationNotFoundException() {
        // Given
        String medicationId = "123";
        when(mockDynamoDBMapper.load(eq(Medication.class), eq(medicationId))).thenReturn(null);

        // When and Then
        MedicationNotFoundException exception = assertThrows(MedicationNotFoundException.class,
                () -> medicationDAO.getMedication(medicationId));

        assertEquals("Could not find medication with id " + medicationId, exception.getMessage());

        // Verify that the load method was called with the correct arguments
        verify(mockDynamoDBMapper).load(eq(Medication.class), eq(medicationId));
    }

    @Test
    void getMedication_withMedicationId_returnsMedication() {
        // Given
        String medicationId = "123";
        Medication mockMedication = new Medication();
        when(mockDynamoDBMapper.load(eq(Medication.class), eq(medicationId))).thenReturn(mockMedication);

        // When
        Medication result = medicationDAO.getMedication(medicationId);

        // Then
        assertNotNull(result);
        assertEquals(mockMedication, result);

        // Verify that the load method was called with the correct arguments
        verify(mockDynamoDBMapper).load(eq(Medication.class), eq(medicationId));
    }

    @Test
    void getMedications_returnsListOfMedication() {

        // Given
        String medicationId = "123";
        List<Medication> expectedMedications = Arrays.asList(new Medication(), new Medication());
        PaginatedQueryList<Medication> paginatedQueryList = mock(PaginatedQueryList.class);
        MedicationDAO mockMedicationDAO = mock(MedicationDAO.class);

        // When
        when(mockDynamoDBMapper.query(eq(Medication.class), any())).thenReturn(paginatedQueryList);
        when(paginatedQueryList.iterator()).thenReturn(expectedMedications.iterator());
        when(mockMedicationDAO.getMedications(medicationId)).thenReturn(expectedMedications);

        // Then
        List<Medication> actualMedications = mockMedicationDAO.getMedications(medicationId);



        // Verify
        assertNotNull(actualMedications);
        assertTrue(actualMedications.containsAll(expectedMedications)
                        && expectedMedications.containsAll(actualMedications),
                "Actual medications should contain the same elements as expected medications");


    }

    @Test
    void deleteMedicationBatch_happyCase() {
        // Given
        List<Medication> medicationList = Arrays.asList(new Medication(), new Medication());

        // When
        medicationDAO.deleteMedicationBatch(medicationList);

        // Then
        verify(mockDynamoDBMapper, times(1)).batchDelete(medicationList);
    }

    @Test
    void deleteMedication_happyCase() {
        // Given
        Medication medication = new Medication();

        // When
        medicationDAO.deleteMedication(medication);

        // Then
        verify(mockDynamoDBMapper, times(1)).delete(medication);
    }




}