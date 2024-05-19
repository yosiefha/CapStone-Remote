package com.nashss.se.hms.activity;

import com.nashss.se.hms.activity.requests.DeleteMedicationRequest;
import com.nashss.se.hms.activity.results.DeleteMedicationResult;
import com.nashss.se.hms.converters.ModelConverter;
import com.nashss.se.hms.dynamodb.MedicationDAO;
import com.nashss.se.hms.dynamodb.models.Medication;
import com.nashss.se.hms.models.MedicationModel;

import javax.inject.Inject;

/**
 * Represents an activity for deleting a medication.
 */
public class DeleteMedicationActivity {

    private final MedicationDAO medicationDAO;


    /**
     * Deletes a medication from the medication table in DynamoDB.
     * @param medicationDAO the Medication object to be deleted.
     */
    @Inject
    public DeleteMedicationActivity(MedicationDAO medicationDAO) {
        this.medicationDAO = medicationDAO;
    }

    /**
     * Handles a request to delete a medication.
     * @param deleteMedicationRequest the request object containing
     *  the medication ID to delete
     * @return the result of the delete operation, including the deleted
     * medication model
     */
    public DeleteMedicationResult handleRequest(final DeleteMedicationRequest deleteMedicationRequest) {
        Medication newMedication = new Medication();
        newMedication.setMedicationId(deleteMedicationRequest.getMedicationId());
        MedicationModel medicationModel = new ModelConverter().toMedicationModel(newMedication);
        medicationDAO.deleteMedication(newMedication);
        return DeleteMedicationResult.builder()
                .withMedicationModel(medicationModel)
                .build();
    }
}
