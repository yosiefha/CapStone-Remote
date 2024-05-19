package com.nashss.se.hms.activity;

import com.nashss.se.hms.activity.requests.DeleteMedicationRequest;
import com.nashss.se.hms.activity.results.DeleteMedicationResult;
import com.nashss.se.hms.dynamodb.MedicationDAO;
import com.nashss.se.hms.dynamodb.models.Diagnosis;
import com.nashss.se.hms.dynamodb.models.Medication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeleteMedicationActivityTest {

    @Mock
    private MedicationDAO mockMedicationDAO;

    @InjectMocks
    private DeleteMedicationActivity deleteMedicationActivity;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        deleteMedicationActivity = new DeleteMedicationActivity(mockMedicationDAO);
    }

    @Test
    public void testHandleRequest() {
        // Happy Test Case for Delete Medication
        // Given
        DeleteMedicationRequest request = DeleteMedicationRequest.builder()
                .withMedicationId("123")
                .build();

        // Mocking behavior of MedicationDAO
        Medication deletedMedication = new Medication();
        deletedMedication.setMedicationId("123");

        doNothing().when(mockMedicationDAO).deleteMedication(Mockito.any(Medication.class));


        // When
        DeleteMedicationResult result = deleteMedicationActivity.handleRequest(request);

        //Then
        assertEquals("123", result.getMedicationModel().getMedicationId());

        // Verify that deleteMedication method was called with the correct argument
        verify(mockMedicationDAO).deleteMedication(Mockito.any(Medication.class));
    }

    @Test
    public void testHandleRequestDeleteMedicationFailure() {
        // Alternate Test Case for Delete Medication Failure
        // Given
        DeleteMedicationRequest request = DeleteMedicationRequest.builder()
                .withMedicationId("123")
                .build();

        // Mocking behavior of MedicationDAO to simulate delete failure
        doThrow(new RuntimeException("Failed to delete medication")).when(mockMedicationDAO).deleteMedication(Mockito.any(Medication.class));

        // When and Then
        assertThrows(RuntimeException.class, () -> deleteMedicationActivity.handleRequest(request));

        // Verify that deleteMedication method was called with the correct argument
        verify(mockMedicationDAO).deleteMedication(Mockito.any(Medication.class));
    }



}