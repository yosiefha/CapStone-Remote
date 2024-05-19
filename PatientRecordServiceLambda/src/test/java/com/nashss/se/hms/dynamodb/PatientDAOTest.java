package com.nashss.se.hms.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.nashss.se.hms.activity.GetPatientByPatientIdActivity;

import com.nashss.se.hms.dynamodb.models.Patient;
import com.nashss.se.hms.exceptions.PatientNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class PatientDAOTest {

    private DynamoDBMapper mockDynamoDBMapper;


    @Mock
    private PatientDAO patientDAO;

    @InjectMocks
    private GetPatientByPatientIdActivity getPatientByPatientIdActivity;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        getPatientByPatientIdActivity = new GetPatientByPatientIdActivity(patientDAO);
        mockDynamoDBMapper = mock(DynamoDBMapper.class);
        patientDAO = new PatientDAO(mockDynamoDBMapper);
    }

    @Test
    void testGetPatientByPatientIdNotFound() {
        // Given
        String patientId = "123";
        when(mockDynamoDBMapper.load(eq(Patient.class), eq(patientId))).thenReturn(null);

        // When and Then
        PatientNotFoundException exception = assertThrows(PatientNotFoundException.class,
                () -> patientDAO.getPatientByPatientId(patientId));

        assertEquals("Could not find Patient with id " + patientId, exception.getMessage());

        // Verify that the load method was called with the correct arguments
        verify(mockDynamoDBMapper).load(eq(Patient.class), eq(patientId));
    }

    @Test
    void testGetPatientByPatientIdHappyCase() {
        // Given
        String patientId = "123";
        Patient mockPatient = new Patient();
        when(mockDynamoDBMapper.load(eq(Patient.class), eq(patientId))).thenReturn(mockPatient);

        // When
        Patient result = patientDAO.getPatientByPatientId(patientId);

        // Then
        assertNotNull(result);
        assertEquals(mockPatient, result);

        // Verify that the load method was called with the correct arguments
        verify(mockDynamoDBMapper).load(eq(Patient.class), eq(patientId));
    }


}