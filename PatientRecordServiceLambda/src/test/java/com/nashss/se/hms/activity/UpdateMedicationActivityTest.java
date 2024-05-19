package com.nashss.se.hms.activity;


import com.nashss.se.hms.activity.requests.UpdateMedicationRequest;
import com.nashss.se.hms.activity.results.UpdateMedicationResult;
import com.nashss.se.hms.dynamodb.MedicationDAO;
import com.nashss.se.hms.dynamodb.models.Medication;
import com.nashss.se.hms.models.MedicationModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UpdateMedicationActivityTest {

    @Mock
    private MedicationDAO medicationDAO;
    @Inject
    private UpdateMedicationActivity  updateMedicationActivity;

    @BeforeEach
    void setUp() {
        medicationDAO= mock(MedicationDAO.class);
        updateMedicationActivity = new UpdateMedicationActivity(medicationDAO);
    }

    @Test
    public void testHandleRequestHappyCase() {
        // Happy Case for UpdateDiagnosisActivity
        // Given
        String medicationId = "123";
        UpdateMedicationRequest request = UpdateMedicationRequest.builder()
                .withMedicationId(medicationId)
                .withMedicationName("Updated Medication")
                .withDosage("Updated Dosage")
                .withStartDate("2023-01-01")
                .withEndDated("2023-02-01")
                .withInstructions("Updated Instructions")
                .withPatientId("456")
                .build();

        Medication existingMedication = new Medication();
        // Mocking behavior of DAO
        when(medicationDAO.getMedication(medicationId)).thenReturn(existingMedication);
        when(medicationDAO.savePatientMedicationDetails(any(Medication.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // When
        UpdateMedicationResult  result = updateMedicationActivity.handleRequest(request);

        // Then
        assertNotNull(result);
        MedicationModel updatedMedication = result.getMedicationModel();
        assertNotNull(updatedMedication);
        assertEquals(medicationId, updatedMedication.getMedicationId());
        assertEquals("Updated Medication", updatedMedication.getMedicationName());
        assertEquals("Updated Dosage", updatedMedication.getDosage());
        assertEquals("2023-01-01", updatedMedication.getStartDate());
        assertEquals("2023-02-01", updatedMedication.getEndDate() );

        // Verify that DAO methods were called with the correct arguments
        verify(medicationDAO).getMedication(medicationId);

    }

    @Test
    public void testHandleRequestDiagnosisNotFound() {
        // Alternate Test Case for UpdateDiagnosisActivity when the diagnosis is not found
        // Given
        String medicationId = "NonexistentDiagnosis";
        UpdateMedicationRequest request = UpdateMedicationRequest.builder()
                .withMedicationId(medicationId)
                .withMedicationName("Updated Medication")
                .withDosage("Updated Dosage")
                .withStartDate("2023-01-01")
                .withEndDated("2023-02-01")
                .withInstructions("Updated Instructions")
                .withPatientId("456")
                .build();

        // Mocking behavior of DAO to simulate diagnosis not found
        when(medicationDAO.getMedication(medicationId)).thenReturn(null);

        // When
        UpdateMedicationResult result = updateMedicationActivity.handleRequest(request);

        // Then
        assertNotNull(result);
        assertNull(result.getMedicationModel());

        // Verify that DAO method was called with the correct argument
        verify(medicationDAO).getMedication(medicationId);

    }

}