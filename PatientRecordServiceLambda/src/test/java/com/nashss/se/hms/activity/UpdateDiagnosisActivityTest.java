package com.nashss.se.hms.activity;

import com.nashss.se.hms.activity.requests.UpdateDiagnosisRequest;
import com.nashss.se.hms.activity.results.UpdateDiagnosisResult;
import com.nashss.se.hms.dynamodb.DiagnosisDAO;
import com.nashss.se.hms.dynamodb.models.Diagnosis;
import com.nashss.se.hms.models.DiagnosisModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UpdateDiagnosisActivityTest {
    @Mock
    private DiagnosisDAO mockDiagnosisDAO;
    @Inject
    private UpdateDiagnosisActivity updateDiagnosisActivity;

    @BeforeEach
    void setUp() {
        mockDiagnosisDAO = mock(DiagnosisDAO.class);
        updateDiagnosisActivity = new UpdateDiagnosisActivity(mockDiagnosisDAO);
    }

    @Test
    public void testHandleRequestHappyCase() {
        // Happy Case for UpdateDiagnosisActivity
        // Given
        String diagnosisId = "123";
        String description = "Updated Description";
        String dateCreated = "2023-12-15";
        String healthcareProfessionalId = "456";
        String patientId = "789";

        UpdateDiagnosisRequest request = UpdateDiagnosisRequest.builder()
                .withDiagnosisId(diagnosisId)
                .withDescription(description)
                .withDateCreated(dateCreated)
                .withHealthcareProfessionalId(healthcareProfessionalId)
                .withPatientId(patientId)
                .build();

        Diagnosis existingDiagnosis = new Diagnosis();

        // Mocking behavior of DAO
        when(mockDiagnosisDAO.getDiagnosis(diagnosisId)).thenReturn(existingDiagnosis);
        when(mockDiagnosisDAO.savePatientDiagnoses(any(Diagnosis.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // When
        UpdateDiagnosisResult result = updateDiagnosisActivity.handleRequest(request);

        // Then
        assertNotNull(result);
        DiagnosisModel updatedDiagnosis = result.getDiagnosisModel();
        assertNotNull(updatedDiagnosis);
        assertEquals(diagnosisId, updatedDiagnosis.getDiagnosisId());
        assertEquals(description, updatedDiagnosis.getDescription());
        assertEquals(dateCreated, updatedDiagnosis.getDateCreated());
        assertEquals(healthcareProfessionalId, updatedDiagnosis.getHealthcareProfessionalId());
        assertEquals(patientId, updatedDiagnosis.getPatientId());

        // Verify that DAO methods were called with the correct arguments
        verify(mockDiagnosisDAO).getDiagnosis(diagnosisId);

    }

    @Test
    public void testHandleRequestDiagnosisNotFound() {
        // Alternate Test Case for UpdateDiagnosisActivity when the diagnosis is not found
        // Given
        String diagnosisId = "NonexistentDiagnosis";
        UpdateDiagnosisRequest request = UpdateDiagnosisRequest.builder()
                .withDiagnosisId(diagnosisId)
                .withDescription("Updated Description")
                .build();

        // Mocking behavior of DAO to simulate diagnosis not found
        when(mockDiagnosisDAO.getDiagnosis(diagnosisId)).thenReturn(null);

        // When
        UpdateDiagnosisResult result = updateDiagnosisActivity.handleRequest(request);

        // Then
        assertNotNull(result);
        assertNull(result.getDiagnosisModel());

        // Verify that DAO method was called with the correct argument
        verify(mockDiagnosisDAO).getDiagnosis(diagnosisId);

    }

}