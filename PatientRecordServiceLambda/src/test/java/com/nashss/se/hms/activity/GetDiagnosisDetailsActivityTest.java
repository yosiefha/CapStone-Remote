package com.nashss.se.hms.activity;

import com.nashss.se.hms.activity.requests.GetDiagnosisDetailsRequest;
import com.nashss.se.hms.activity.results.GetDiagnosisDetailsResult;
import com.nashss.se.hms.dynamodb.DiagnosisDAO;
import com.nashss.se.hms.dynamodb.models.Diagnosis;
import com.nashss.se.hms.models.DiagnosisModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class GetDiagnosisDetailsActivityTest {

    @Mock
    private DiagnosisDAO mockDiagnosisDAO;

    @InjectMocks
    private GetDiagnosisDetailsActivity getDiagnosisDetailsActivity;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        getDiagnosisDetailsActivity = new GetDiagnosisDetailsActivity(mockDiagnosisDAO);
    }

    @Test
    public void testHandleRequestHappyPath() {
        // Happy Test Case for GetDiagnosisDetailsActivity
        // Given
        String patientId = "123";
        GetDiagnosisDetailsRequest request = GetDiagnosisDetailsRequest.builder()
                .withPatientId(patientId)
                .build();

        // Mocking behavior of DAO
        Diagnosis diagnosis1 = new Diagnosis();
        diagnosis1.setDiagnosisId("1");
        diagnosis1.setDescription("Headache");

        Diagnosis diagnosis2 = new Diagnosis();
        diagnosis2.setDiagnosisId("2");
        diagnosis2.setDescription("Fever");

        List<Diagnosis> diagnosisList = Arrays.asList(diagnosis1, diagnosis2);
        when(mockDiagnosisDAO.getDiagnoses(patientId)).thenReturn(diagnosisList);

        // When
        GetDiagnosisDetailsResult result = getDiagnosisDetailsActivity.handleRequest(request);

        // Then
        assertNotNull(result);
        List<DiagnosisModel> diagnosisModelList = result.getDiagnosisModelList();
        assertNotNull(diagnosisModelList);
        assertEquals(2, diagnosisModelList.size());

        DiagnosisModel diagnosisModel1 = diagnosisModelList.get(0);
        assertEquals("1", diagnosisModel1.getDiagnosisId());
        assertEquals("Headache", diagnosisModel1.getDescription());

        DiagnosisModel diagnosisModel2 = diagnosisModelList.get(1);
        assertEquals("2", diagnosisModel2.getDiagnosisId());
        assertEquals("Fever", diagnosisModel2.getDescription());

        // Verify that DAO method was called with the correct argument
        verify(mockDiagnosisDAO).getDiagnoses(patientId);
    }

    @Test
    public void testHandleRequestNoDiagnoses() {
        // Alternate Test Case for GetDiagnosisDetailsActivity when there are no diagnoses
        // Given
        String patientId = "456";
        GetDiagnosisDetailsRequest request = GetDiagnosisDetailsRequest.builder()
                .withPatientId(patientId)
                .build();

        // Mocking behavior of DAO
        when(mockDiagnosisDAO.getDiagnoses(patientId)).thenReturn(Collections.emptyList());

        // When
        GetDiagnosisDetailsResult result = getDiagnosisDetailsActivity.handleRequest(request);

        // Then
        assertNotNull(result);
        List<DiagnosisModel> diagnosisModelList = result.getDiagnosisModelList();
        assertNotNull(diagnosisModelList);
        assertTrue(diagnosisModelList.isEmpty());

        // Verify that DAO method was called with the correct argument
        verify(mockDiagnosisDAO).getDiagnoses(patientId);
    }

}