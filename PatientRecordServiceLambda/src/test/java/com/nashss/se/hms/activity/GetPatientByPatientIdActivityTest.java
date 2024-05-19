package com.nashss.se.hms.activity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.nashss.se.hms.activity.requests.GetPatientByPatientIdRequest;
import com.nashss.se.hms.activity.results.GetPatientByPatientIdResult;
import com.nashss.se.hms.dynamodb.PatientDAO;
import com.nashss.se.hms.dynamodb.models.Patient;
import com.nashss.se.hms.exceptions.PatientNotFoundException;
import com.nashss.se.hms.models.PatientModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetPatientByPatientIdActivityTest {

    @Mock
    private PatientDAO mockPatientDAO;

    @InjectMocks
    private GetPatientByPatientIdActivity getPatientByPatientIdActivity;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        getPatientByPatientIdActivity = new GetPatientByPatientIdActivity(mockPatientDAO);
    }

    @Test
    public void testHandleRequestHappyPath() {
        // Happy Test Case for GetPatientByPatientIdActivity
        // Given
        String patientId = "123";
        GetPatientByPatientIdRequest request = GetPatientByPatientIdRequest.builder()
                .withPatientId(patientId)
                .build();

        // Mocking behavior of DAO
        Patient patient = new Patient();
        patient.setPatientId(patientId);
        patient.setFirstName("John");
        patient.setLastName("Doe");

        when(mockPatientDAO.getPatientByPatientId(patientId)).thenReturn(patient);

        // When
        GetPatientByPatientIdResult result = getPatientByPatientIdActivity.handleRequest(request);

        // Then
        assertNotNull(result);
        PatientModel patientModel = result.getPatientModel();
        assertNotNull(patientModel);

        assertEquals(patientId, patientModel.getPatientId());
        assertEquals("John", patientModel.getFirstName());
        assertEquals("Doe", patientModel.getLastName());

        // Verify that DAO method was called with the correct argument
        verify(mockPatientDAO).getPatientByPatientId(patientId);
    }

    @Test
    public void testHandleRequestPatientNotFound() {
        // Alternate Test Case for GetPatientByPatientIdActivity when patient is not found
        // Given
        String patientId = "456";
        GetPatientByPatientIdRequest request = GetPatientByPatientIdRequest.builder()
                .withPatientId(patientId)
                .build();

        // Mocking behavior of DAO
        when(mockPatientDAO.getPatientByPatientId(patientId)).thenReturn(null);

        // When
        GetPatientByPatientIdResult result = getPatientByPatientIdActivity.handleRequest(request);

        // Then
        assertNotNull(result);
        PatientModel patientModel = result.getPatientModel();
        assertNull(patientModel);

        // Verify that DAO method was called with the correct argument
        verify(mockPatientDAO).getPatientByPatientId(patientId);
    }


}