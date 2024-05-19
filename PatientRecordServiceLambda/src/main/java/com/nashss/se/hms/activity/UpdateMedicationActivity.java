package com.nashss.se.hms.activity;

import com.nashss.se.hms.activity.requests.UpdateMedicationRequest;
import com.nashss.se.hms.activity.results.UpdateMedicationResult;
import com.nashss.se.hms.converters.ModelConverter;
import com.nashss.se.hms.dynamodb.MedicationDAO;
import com.nashss.se.hms.dynamodb.models.Medication;

import javax.inject.Inject;

/**
 * The UpdateMedicationActivity class is responsible for handling the requests to update medication details.
 */
public class UpdateMedicationActivity {

    private final MedicationDAO medicationDAO;

    /**
     * Updates medication details.
     * @param medicationDAO The MedicationDAO object used to interact
     *  with the medication data.
     */
    @Inject
    public UpdateMedicationActivity(MedicationDAO medicationDAO) {
        this.medicationDAO = medicationDAO;
    }

    /**
     * Handles the request to update medication details.
     * @param updateMedicationRequest The UpdateMedicationRequest
     * object containing the medication details to update.
     * @return The UpdateMedicationResult object containing the updated
     * medication details.
     */
    public UpdateMedicationResult handleRequest(final UpdateMedicationRequest updateMedicationRequest) {
        Medication medication = medicationDAO.getMedication(updateMedicationRequest.getMedicationId());
        if (medication == null) {
            return UpdateMedicationResult.builder()
                    .withMedicationModel(new ModelConverter().toMedicationModel(medication))
                    .build();

        }
        String medicationId = updateMedicationRequest.getMedicationId();
        String medicationName = updateMedicationRequest.getMedicationName();
        String dosage = updateMedicationRequest.getDosage();
        String startDate = updateMedicationRequest.getStartDate();
        String endDate = updateMedicationRequest.getEndDate();
        String instructions = updateMedicationRequest.getInstructions();
        String patientId = updateMedicationRequest.getPatientId();
        if (medicationName != null) {
            medication.setMedicationName(medicationName);
        }
        if (dosage != null) {
            medication.setDosage(dosage);
        }
        if (startDate != null) {
            medication.setStartDate(startDate);
        }
        if (endDate != null) {
            medication.setEndDate(endDate);
        }
        if (instructions != null) {
            medication.setInstructions(instructions);
        }
        medication.setPatientId(patientId);
        medication.setMedicationId(medicationId);
        medication = medicationDAO.savePatientMedicationDetails(medication);
        return UpdateMedicationResult.builder()
                .withMedicationModel(new ModelConverter().toMedicationModel(medication))
                .build();
    }
}
