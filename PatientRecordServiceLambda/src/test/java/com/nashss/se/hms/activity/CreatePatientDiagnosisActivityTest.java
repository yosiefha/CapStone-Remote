package com.nashss.se.hms.activity;

import com.nashss.se.hms.activity.requests.CreatePatientDiagnosisRequest;
import com.nashss.se.hms.activity.results.CreatePatientDiagnosisResult;
import com.nashss.se.hms.dynamodb.DiagnosisDAO;
import com.nashss.se.hms.dynamodb.models.Diagnosis;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CreatePatientDiagnosisActivityTest {

    @Mock
    private DiagnosisDAO mockDiagnosisDAO;

    @InjectMocks
    private CreatePatientDiagnosisActivity createPatientDiagnosisActivity;

    CreatePatientDiagnosisRequest request;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        createPatientDiagnosisActivity = new CreatePatientDiagnosisActivity(mockDiagnosisDAO);

        request = CreatePatientDiagnosisRequest.builder()
                .withDiagnosisId("123")
                .withPatientId("456")
                .withDescription("Some description")
                .withHealthcareProfessionalId("789")
                .withDateCreated("2023-01-01")
                .build();
    }

    @Test
    public void addDiagnosisToTheDatabase() {
        // Arrange


        // Mocking behavior of DiagnosisDAO
        Diagnosis savedDiagnosis = new Diagnosis();
        when(mockDiagnosisDAO.savePatientDiagnoses(Mockito.any(Diagnosis.class))).thenReturn(savedDiagnosis);

        // Act
        CreatePatientDiagnosisResult result = createPatientDiagnosisActivity.handleRequest(request);

        // Assert
        assertEquals("123", result.getDiagnosisModel().getDiagnosisId());
        assertEquals("456", result.getDiagnosisModel().getPatientId());
        assertEquals("Some description", result.getDiagnosisModel().getDescription());
        assertEquals("789", result.getDiagnosisModel().getHealthcareProfessionalId());
        assertEquals("2023-01-01", result.getDiagnosisModel().getDateCreated());

        // Verify that savePatientDiagnoses method was called with the correct argument
        verify(mockDiagnosisDAO).savePatientDiagnoses(Mockito.any(Diagnosis.class));
    }

    @Test
    public void testHSaveDiagnosisFailure() {
        // Test Case for Save Diagnosis Failure
        // Arrange


        // Mocking behavior of DiagnosisDAO to simulate save failure
        when(mockDiagnosisDAO.savePatientDiagnoses(Mockito.any(Diagnosis.class))).thenThrow(new RuntimeException("Failed to save diagnosis"));

        // Act and Assert
        assertThrows(RuntimeException.class, () -> createPatientDiagnosisActivity.handleRequest(request));

        // Verify that savePatientDiagnoses method was called with the correct argument
        verify(mockDiagnosisDAO).savePatientDiagnoses(Mockito.any(Diagnosis.class));
    }

}