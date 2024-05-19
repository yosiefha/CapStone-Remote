package com.nashss.se.hms.activity;

import com.nashss.se.hms.activity.requests.UpdateMedicationRequest;
import com.nashss.se.hms.activity.requests.UpdatePatientRequest;
import com.nashss.se.hms.activity.results.UpdateMedicationResult;
import com.nashss.se.hms.activity.results.UpdatePatientResult;
import com.nashss.se.hms.dynamodb.MedicationDAO;
import com.nashss.se.hms.dynamodb.PatientDAO;
import com.nashss.se.hms.dynamodb.models.Medication;
import com.nashss.se.hms.dynamodb.models.Patient;
import com.nashss.se.hms.models.MedicationModel;
import com.nashss.se.hms.models.PatientModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UpdatePatientActivityTest {


    @Mock
    private PatientDAO  patientDAO;
    @Inject
    private UpdatePatientActivity  updatePatientActivity;

    @BeforeEach
    void setUp() {
        patientDAO = mock(PatientDAO.class);
        updatePatientActivity = new UpdatePatientActivity(patientDAO);
    }

    @Test
    public void testHandleRequestHappyCase() {
        // Happy Case for UpdateDiagnosisActivity
        // Given
        String patientId = "123";
        UpdatePatientRequest request = UpdatePatientRequest.builder()
                .withPatientId(patientId)
                .withFirstName("Solomon")
                .withLastName("Tewelde")
                .withDOB("03.05.1970")
                .withContactNumber("3566729991")
                .withEmailAddress("solo@gmail.com")
                .withAddress("North Carolina")
                .build();

        Patient existingPatient = new Patient();

        // Mocking behavior of DAO
        when(patientDAO.getPatientByPatientId(patientId)).thenReturn(existingPatient);
        when(patientDAO.savePatient(any(Patient.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // When
        UpdatePatientResult  result = updatePatientActivity.handleRequest(request);

        // Then
        assertNotNull(result);
        PatientModel updatedPatient = result.getPatientModel();
        assertNotNull(updatedPatient);
        assertEquals(patientId , updatedPatient.getPatientId());
        assertEquals("Solomon", updatedPatient.getFirstName());
        assertEquals("Tewelde", updatedPatient.getLastName());
        assertEquals("03.05.1970", updatedPatient.getDOB());
        assertEquals("3566729991", updatedPatient.getContactNumber());
        assertEquals("solo@gmail.com", updatedPatient.getEmailAddress());
        assertEquals("North Carolina", updatedPatient.getAddress());


        // Verify that DAO methods were called with the correct arguments

        verify(patientDAO).getPatientByPatientId(patientId);
        verify(patientDAO).savePatient(existingPatient);

    }

    @Test
    public void testHandleRequestDiagnosisNotFound() {
        // Alternate Test Case for UpdateDiagnosisActivity when the diagnosis is not found
        // Given
        String patientId = "123";
        UpdatePatientRequest request = UpdatePatientRequest.builder()
                .withPatientId(patientId)
                .withFirstName("Solomon")
                .withLastName("Tewelde")
                .withDOB("03.05.1970")
                .withContactNumber("3566729991")
                .withEmailAddress("solo@gmail.com")
                .withAddress("North Carolina")
                .build();

        // Mocking behavior of DAO to simulate diagnosis not found
        when(patientDAO.getPatientByPatientId(patientId)).thenReturn(null);

        // When
        UpdatePatientResult result = updatePatientActivity.handleRequest(request);

        // Then
        assertNotNull(result);
        assertNull(result.getPatientModel());
        Patient patient = new Patient();
        // Verify that DAO method was called with the correct argument
        verify(patientDAO).getPatientByPatientId(patientId);
        verify(patientDAO, never()).savePatient(patient);

    }

}