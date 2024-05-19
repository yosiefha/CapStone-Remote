package com.nashss.se.hms.activity;


import com.nashss.se.hms.activity.requests.DeleteDiagnosisRequest;
import com.nashss.se.hms.activity.results.DeleteDiagnosisResult;
import com.nashss.se.hms.dynamodb.DiagnosisDAO;

import com.nashss.se.hms.dynamodb.models.Diagnosis;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeleteDiagnosisActivityTest {
    @Mock
    private DiagnosisDAO mockDiagnosisDAO;

    @InjectMocks
    private DeleteDiagnosisActivity deleteDiagnosisActivity;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        deleteDiagnosisActivity = new DeleteDiagnosisActivity(mockDiagnosisDAO);
    }

    @Test
    public void deleteDiagnosisActivity() {
        // Given
        DeleteDiagnosisRequest request = DeleteDiagnosisRequest.builder()
                .withDiagnosisId("123")
                .build();

        // Mocking behavior of DiagnosisDAO
        Diagnosis deletedDiagnosis = new Diagnosis();
        deletedDiagnosis.setDiagnosisId("123");
        doNothing().when(mockDiagnosisDAO).deleteDiagnosis(Mockito.any(Diagnosis.class));


        //When
        DeleteDiagnosisResult result = deleteDiagnosisActivity.handleRequest(request);

        // Then
        assertEquals("123", result.getDiagnosisModel().getDiagnosisId());

        // Verify that deleteDiagnosis method was called with the correct argument
        verify(mockDiagnosisDAO).deleteDiagnosis(Mockito.any(Diagnosis.class));
    }

    @Test
    public void testHandleRequestDeleteDiagnosisFailure() {
        // Test Case for Delete Diagnosis Failure
        // Arrange
        DeleteDiagnosisRequest request = DeleteDiagnosisRequest.builder()
                .withDiagnosisId("123")
                .build();

        // Mocking behavior of DiagnosisDAO to simulate delete failure
        doThrow(new RuntimeException("Failed to delete diagnosis")).when(mockDiagnosisDAO).deleteDiagnosis(Mockito.any(Diagnosis.class));

        // Act and Assert
        assertThrows(RuntimeException.class, () -> deleteDiagnosisActivity.handleRequest(request));

        // Verify that deleteDiagnosis method was called with the correct argument
        verify(mockDiagnosisDAO).deleteDiagnosis(Mockito.any(Diagnosis.class));
    }

}