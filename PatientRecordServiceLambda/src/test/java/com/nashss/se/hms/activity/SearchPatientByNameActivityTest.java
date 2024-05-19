package com.nashss.se.hms.activity;

import com.nashss.se.hms.activity.requests.SearchPatientByNameRequest;
import com.nashss.se.hms.activity.results.SearchPatientByNameResult;
import com.nashss.se.hms.dynamodb.PatientDAO;
import com.nashss.se.hms.dynamodb.models.Patient;
import com.nashss.se.hms.models.PatientModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SearchPatientByNameActivityTest {
    @Mock
    private PatientDAO mockPatientDAO;
    @Inject
    private SearchPatientByNameActivity searchPatientByNameActivity;

    @BeforeEach
    void setUp() {
        mockPatientDAO = mock(PatientDAO.class);
        searchPatientByNameActivity = new SearchPatientByNameActivity(mockPatientDAO);
    }

    @Test
    public void testHandleRequestHappyCase() {
        // Happy Case for SearchPatientByNameActivity
        // Given
        String firstName = "John";
        String lastName = "Doe";
        SearchPatientByNameRequest request = SearchPatientByNameRequest.builder()
                .withFirstName(firstName)
                .withLastName(lastName)
                .build();

        List<Patient> patientList = new ArrayList<>();
        patientList.add(new Patient());

        // Mocking behavior of DAO
        when(mockPatientDAO.searchPatient(firstName, lastName)).thenReturn(patientList);

        // When
        SearchPatientByNameResult result = searchPatientByNameActivity.handleRequest(request);

        // Then
        assertNotNull(result);
        List<PatientModel> patientModelList = result.getPatientModelList();
        assertNotNull(patientModelList);
        assertEquals(1, patientModelList.size());

        // Verify that DAO method was called with the correct arguments
        verify(mockPatientDAO).searchPatient(firstName, lastName);
    }

    @Test
    public void testHandleRequestNoResults() {
        // Alternate Test Case for SearchPatientByNameActivity when no patients are found
        // Given
        String firstName = "Nonexistent";
        String lastName = "Patient";
        SearchPatientByNameRequest request = SearchPatientByNameRequest.builder()
                .withFirstName(firstName)
                .withLastName(lastName)
                .build();

        // Mocking behavior of DAO to simulate no results
        when(mockPatientDAO.searchPatient(firstName, lastName)).thenReturn(new ArrayList<>());

        // When
        SearchPatientByNameResult result = searchPatientByNameActivity.handleRequest(request);

        // Then
        assertNotNull(result);
        List<PatientModel> patientModelList = result.getPatientModelList();
        assertNotNull(patientModelList);
        assertTrue(patientModelList.isEmpty());

        // Verify that DAO method was called with the correct arguments
        verify(mockPatientDAO).searchPatient(firstName, lastName);
    }

}