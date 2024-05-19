package com.nashss.se.hms.activity;

import com.nashss.se.hms.activity.requests.CreatePatientMedicationRequest;
import com.nashss.se.hms.activity.results.CreatePatientMedicationResult;
import com.nashss.se.hms.converters.ModelConverter;
import com.nashss.se.hms.dynamodb.MedicationDAO;
import com.nashss.se.hms.dynamodb.models.Medication;
import com.nashss.se.hms.models.MedicationModel;

import javax.inject.Inject;

/**
 * Represents an activity for creating a patient medication.
 */
public class CreatePatientMedicationActivity {

    private final MedicationDAO medicationDAO;

    /**
     * Represents an activity for creating a patient medication.
     *
     * @param medicationDAO The MedicationDAO object used for saving patient medication details.
     */
    @Inject
    public CreatePatientMedicationActivity(MedicationDAO medicationDAO) {
        this.medicationDAO = medicationDAO;
    }

    /**
     * Handles a request to create a patient medication.
     *
     * @param createPatientMedicationRequest The request object containing the
     * information for creating a patient medication.
     * @return The result object containing the newly created medication.
     */
    public CreatePatientMedicationResult handleRequest(final CreatePatientMedicationRequest
                                                               createPatientMedicationRequest) {
        Medication newMedication = new Medication();
        newMedication.setMedicationId(createPatientMedicationRequest.getMedicationId());
        newMedication.setMedicationName(createPatientMedicationRequest.getMedicationName());
        newMedication.setDosage(createPatientMedicationRequest.getDosage());
        newMedication.setInstructions(createPatientMedicationRequest.getInstructions());
        newMedication.setStartDate(createPatientMedicationRequest.getStartDate());
        newMedication.setEndDate(createPatientMedicationRequest.getEndDate());
        newMedication.setPatientId(createPatientMedicationRequest.getPatientId());
        medicationDAO.savePatientMedicationDetails(newMedication);

        MedicationModel medicationModel = new ModelConverter().toMedicationModel(newMedication);

        return CreatePatientMedicationResult.builder()
                .withMedication(medicationModel)
                .build();


    }
}
