package com.nashss.se.hms.activity;

import com.nashss.se.hms.activity.requests.CreatePatientMedicationRequest;
import com.nashss.se.hms.activity.results.CreatePatientMedicationResult;
import com.nashss.se.hms.dynamodb.MedicationDAO;
import com.nashss.se.hms.dynamodb.models.Medication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CreatePatientMedicationActivityTest {

    @Mock
    private MedicationDAO mockMedicationDAO;

    @InjectMocks
    private CreatePatientMedicationActivity createPatientMedicationActivity;
    CreatePatientMedicationRequest request;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        createPatientMedicationActivity = new CreatePatientMedicationActivity(mockMedicationDAO);
        request = CreatePatientMedicationRequest.builder()
                .withMedicationId("123")
                .withMedicationName("MedName")
                .withDosage("5mg")
                .withInstructions("Take with food")
                .withStartDate("2023-01-01")
                .withEndDate("2023-01-31")
                .withPatientId("456")
                .build();
    }

    @Test
    public void addMedicationToTheDatabase() {
        // Arrange


        // Mocking behavior of MedicationDAO
        Medication savedMedication = new Medication();
        when(mockMedicationDAO.savePatientMedicationDetails(Mockito.any(Medication.class))).thenReturn(savedMedication);

        // Act
        CreatePatientMedicationResult result = createPatientMedicationActivity.handleRequest(request);

        // Assert
        assertEquals("123", result.getMedicationModel().getMedicationId());
        assertEquals("MedName", result.getMedicationModel().getMedicationName());
        assertEquals("5mg", result.getMedicationModel().getDosage());
        assertEquals("Take with food", result.getMedicationModel().getInstructions());
        assertEquals("2023-01-01", result.getMedicationModel().getStartDate());
        assertEquals("2023-01-31", result.getMedicationModel().getEndDate());
        assertEquals("456", result.getMedicationModel().getPatientId());

        // Verify that savePatientMedicationDetails method was called with the correct argument
        verify(mockMedicationDAO).savePatientMedicationDetails(Mockito.any(Medication.class));
    }

    @Test
    public void testHandleRequestSaveMedicationFailure() {
        // Test Case for Save Medication Failure
        // Arrange


        // Mocking behavior of MedicationDAO to simulate save failure
        when(mockMedicationDAO.savePatientMedicationDetails(Mockito.any(Medication.class))).thenThrow(new RuntimeException("Failed to save medication"));

        // Act and Assert
        assertThrows(RuntimeException.class, () -> createPatientMedicationActivity.handleRequest(request));

        // Verify that savePatientMedicationDetails method was called with the correct argument
        verify(mockMedicationDAO).savePatientMedicationDetails(Mockito.any(Medication.class));
    }

}