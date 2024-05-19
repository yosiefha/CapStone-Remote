package com.nashss.se.hms.activity;


import com.nashss.se.hms.activity.requests.AddPatientToPatientsRequest;
import com.nashss.se.hms.activity.results.AddPatientToPatientsResult;
import com.nashss.se.hms.dynamodb.PatientDAO;
import com.nashss.se.hms.dynamodb.models.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;


public class AddPatientToPatientsActivityTest {

    @Mock
    private PatientDAO mockPatientDAO;

    @InjectMocks
    private AddPatientToPatientsActivity addPatientToPatientsActivity;


    @BeforeEach
    void setup() {
        openMocks(this);
        addPatientToPatientsActivity = new AddPatientToPatientsActivity(mockPatientDAO);
    }

    @Test
    public void testHandleRequest() {
        // Arrange
        AddPatientToPatientsRequest request = AddPatientToPatientsRequest.builder()
                .withFirstName("John")
                .withLastName("Abraham")
                .build();

        // Mocking behavior of PatientDAO

        Patient savedPatient = new Patient();
        when(mockPatientDAO.savePatient(Mockito.any(Patient.class))).thenReturn(savedPatient);

        // Act
        AddPatientToPatientsResult result = addPatientToPatientsActivity.handleRequest(request);

        // Assert

        assertEquals("John", result.getPatient().getFirstName());
        assertEquals("Abraham", result.getPatient().getLastName());
        // Assert other properties as needed...

        // Verify that savePatient method was called with the correct argument
        Mockito.verify(mockPatientDAO).savePatient(Mockito.any(Patient.class));
    }

    @Test
    public void testHandleRequestSavePatientFailure() {
        // Test Case for Save Patient Failure
        // Arrange
        AddPatientToPatientsRequest request = AddPatientToPatientsRequest.builder()
                .withFirstName("Jane")
                .withLastName("Eric")
                .build();

        // Mocking behavior of PatientDAO to simulate save failure
        when(mockPatientDAO.savePatient(Mockito.any(Patient.class))).thenThrow(new RuntimeException("Failed to save patient"));

        // Act and Assert
        assertThrows(RuntimeException.class, () -> addPatientToPatientsActivity.handleRequest(request));

        // Verify that savePatient method was called with the correct argument
        Mockito.verify(mockPatientDAO).savePatient(Mockito.any(Patient.class));
    }
}
