package com.nashss.se.hms.activity;

import com.nashss.se.hms.activity.requests.DeletePatientRequest;
import com.nashss.se.hms.activity.results.DeletePatientResult;
import com.nashss.se.hms.dynamodb.DiagnosisDAO;
import com.nashss.se.hms.dynamodb.MedicationDAO;
import com.nashss.se.hms.dynamodb.PatientDAO;
import com.nashss.se.hms.dynamodb.models.Diagnosis;
import com.nashss.se.hms.dynamodb.models.Medication;
import com.nashss.se.hms.dynamodb.models.Patient;
import com.nashss.se.hms.models.PatientModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeletePatientActivityTest {

    @Mock
    private PatientDAO mockPatientDAO;

    @Mock
    private DiagnosisDAO mockDiagnosisDAO;

    @Mock
    private MedicationDAO mockMedicationDAO;

    @InjectMocks
    private DeletePatientActivity deletePatientActivity;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        deletePatientActivity = new DeletePatientActivity(mockPatientDAO, mockDiagnosisDAO, mockMedicationDAO);
    }

    @Test
    public void testHandleRequestDeletePatientSuccess() {
        // Happy Test Case for Delete Patient
        // Given
        String patientId = "123";
        DeletePatientRequest request = DeletePatientRequest.builder()
                .withPatientId(patientId)
                .build();

        // Mocking behavior of DAOs
        when(mockDiagnosisDAO.getDiagnoses(patientId)).thenReturn(Collections.emptyList());
        when(mockMedicationDAO.getMedications(patientId)).thenReturn(Collections.emptyList());

        // When
        DeletePatientResult result = deletePatientActivity.handleRequest(request);

        // Then

        verify(mockDiagnosisDAO).deleteDiagnosisBatch(Mockito.anyList());
        verify(mockMedicationDAO).deleteMedicationBatch(Mockito.anyList());
        verify(mockPatientDAO).deletePatient(Mockito.any(Patient.class));

        assertNotNull(result);
        PatientModel deletedPatientModel = result.getPatientModel();
        assertNotNull(deletedPatientModel);
        assertEquals(patientId, deletedPatientModel.getPatientId());
    }

    @Test
    public void testHandleRequestDeletePatientFailure() {
        // Alternate Test Case for Delete Patient Failure
        // Given
        String patientId = "123";
        DeletePatientRequest request = DeletePatientRequest.builder()
                .withPatientId(patientId)
                .build();

        // Mocking behavior of DAOs to simulate delete failure
        when(mockDiagnosisDAO.getDiagnoses(patientId)).thenReturn(Collections.emptyList());
        when(mockMedicationDAO.getMedications(patientId)).thenReturn(Collections.emptyList());
        doThrow(new RuntimeException("Failed to delete patient")).when(mockPatientDAO).deletePatient(Mockito.any(Patient.class));

        // When and Then
        assertThrows(RuntimeException.class, () -> deletePatientActivity.handleRequest(request));

        // Verify that delete methods were called with the correct arguments

        verify(mockDiagnosisDAO).deleteDiagnosisBatch(Mockito.anyList());
        verify(mockMedicationDAO).deleteMedicationBatch(Mockito.anyList());
        verify(mockPatientDAO).deletePatient(Mockito.any(Patient.class));
    }
}