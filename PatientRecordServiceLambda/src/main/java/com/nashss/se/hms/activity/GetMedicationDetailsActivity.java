package com.nashss.se.hms.activity;

import com.nashss.se.hms.activity.requests.GetMedicationDetailsRequest;
import com.nashss.se.hms.activity.results.GetMedicationDetailsResult;
import com.nashss.se.hms.converters.ModelConverter;
import com.nashss.se.hms.dynamodb.MedicationDAO;
import com.nashss.se.hms.dynamodb.models.Medication;
import com.nashss.se.hms.models.MedicationModel;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Represents an activity to get medication details for a patient.
 */
public class GetMedicationDetailsActivity {

    private final MedicationDAO medicationDAO;

    /**
     * Represents an activity to get medication details for a patient.
     * @param medicationDAO The MedicationDAO instance used to retrieve
     * medication details.
     */
    @Inject
    public GetMedicationDetailsActivity(MedicationDAO medicationDAO) {
        this.medicationDAO = medicationDAO;
    }


    /**
     * Retrieves medication details for a patient.
     * @param getMedicationDetailsRequest The request object containing
     * the patient ID.
     * @return The result object containing the list of medication details.
     */
    public GetMedicationDetailsResult handleRequest(final GetMedicationDetailsRequest
                                                            getMedicationDetailsRequest) {
        String patientId = getMedicationDetailsRequest.getPatientId();
        List<Medication> medicationList = medicationDAO.getMedications(patientId);
        List<MedicationModel> medicationModelList = new ArrayList<>();
        for (Medication medication : medicationList) {
            medicationModelList.add(new ModelConverter().toMedicationModel(medication));
        }
        return GetMedicationDetailsResult.builder()
                .withMedicationList(medicationModelList)
                .build();
    }
}
