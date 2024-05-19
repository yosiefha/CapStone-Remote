package com.nashss.se.hms.activity;

import com.nashss.se.hms.activity.requests.GetMedicationDetailsRequest;
import com.nashss.se.hms.activity.results.GetMedicationDetailsResult;
import com.nashss.se.hms.dynamodb.MedicationDAO;
import com.nashss.se.hms.dynamodb.models.Medication;
import com.nashss.se.hms.models.MedicationModel;
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

class GetMedicationDetailsActivityTest {

    @Mock
    private MedicationDAO mockMedicationDAO;

    @InjectMocks
    private GetMedicationDetailsActivity getMedicationDetailsActivity;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        getMedicationDetailsActivity = new GetMedicationDetailsActivity(mockMedicationDAO);
    }

    @Test
    public void testHandleRequestHappyPath() {
        // Happy Test Case for GetMedicationDetailsActivity
        // Given
        String patientId = "123";
        GetMedicationDetailsRequest request = GetMedicationDetailsRequest.builder()
                .withPatientId(patientId)
                .build();

        // Mocking behavior of DAO
        Medication medication1 = new Medication();
        medication1.setMedicationId("1");
        medication1.setMedicationName("Aspirin");

        Medication medication2 = new Medication();
        medication2.setMedicationId("2");
        medication2.setMedicationName("Tylenol");

        List<Medication> medicationList = Arrays.asList(medication1, medication2);
        when(mockMedicationDAO.getMedications(patientId)).thenReturn(medicationList);

        // When
        GetMedicationDetailsResult result = getMedicationDetailsActivity.handleRequest(request);

        // Then
        assertNotNull(result);
        List<MedicationModel> medicationModelList = result.getMedicationModelList();
        assertNotNull(medicationModelList);
        assertEquals(2, medicationModelList.size());

        MedicationModel medicationModel1 = medicationModelList.get(0);
        assertEquals("1", medicationModel1.getMedicationId());
        assertEquals("Aspirin", medicationModel1.getMedicationName());

        MedicationModel medicationModel2 = medicationModelList.get(1);
        assertEquals("2", medicationModel2.getMedicationId());
        assertEquals("Tylenol", medicationModel2.getMedicationName());

        // Verify that DAO method was called with the correct argument
        verify(mockMedicationDAO).getMedications(patientId);
    }

    @Test
    public void testHandleRequestNoMedications() {
        // Alternate Test Case for GetMedicationDetailsActivity when there are no medications
        // Given
        String patientId = "456";
        GetMedicationDetailsRequest request = GetMedicationDetailsRequest.builder()
                .withPatientId(patientId)
                .build();

        // Mocking behavior of DAO
        when(mockMedicationDAO.getMedications(patientId)).thenReturn(Collections.emptyList());

        // When
        GetMedicationDetailsResult result = getMedicationDetailsActivity.handleRequest(request);

        // Then
        assertNotNull(result);
        List<MedicationModel> medicationModelList = result.getMedicationModelList();
        assertNotNull(medicationModelList);
        assertTrue(medicationModelList.isEmpty());

        // Verify that DAO method was called with the correct argument
        verify(mockMedicationDAO).getMedications(patientId);
    }

}