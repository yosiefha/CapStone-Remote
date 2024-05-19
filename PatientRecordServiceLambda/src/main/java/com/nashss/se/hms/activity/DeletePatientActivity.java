package com.nashss.se.hms.activity;

import com.nashss.se.hms.activity.requests.DeletePatientRequest;
import com.nashss.se.hms.activity.results.DeletePatientResult;
import com.nashss.se.hms.converters.ModelConverter;
import com.nashss.se.hms.dynamodb.DiagnosisDAO;
import com.nashss.se.hms.dynamodb.MedicationDAO;
import com.nashss.se.hms.dynamodb.PatientDAO;
import com.nashss.se.hms.dynamodb.models.Diagnosis;
import com.nashss.se.hms.dynamodb.models.Medication;
import com.nashss.se.hms.dynamodb.models.Patient;
import com.nashss.se.hms.models.PatientModel;

import java.util.List;
import javax.inject.Inject;


/**
 * DeletePatientActivity is responsible for handling delete patient requests.
 * It interacts with the PatientDAO, DiagnosisDAO, and MedicationDAO to delete the patient and associated data.
 */

public class DeletePatientActivity {
    private final PatientDAO patientDAO;
    private final DiagnosisDAO diagnosisDAO;
    private final MedicationDAO medicationDAO;

    /**
     * Deletes a patient and associated data from the database.
     * @param patientDAO     The PatientDAO used to interact with the patient
     * table in the database.
     * @param diagnosisDAO   The DiagnosisDAO used to interact with the
     * diagnosis table in the database.
     * @param medicationDAO  The MedicationDAO used to interact with the
     * medication table in the database.
     */
    @Inject
    public DeletePatientActivity(PatientDAO patientDAO, DiagnosisDAO diagnosisDAO, MedicationDAO medicationDAO) {
        this.patientDAO = patientDAO;
        this.diagnosisDAO = diagnosisDAO;
        this.medicationDAO = medicationDAO;
    }

    /**
     * Handles a delete patient request. Deletes the patient and associated
     * data from the database.
     * @param deletePatientRequest The request to delete a patient.
     * @return The result of the delete patient operation.
     */
    public DeletePatientResult handleRequest(final DeletePatientRequest
                                                     deletePatientRequest) {
        Patient patient = new Patient();

        List<Diagnosis> diagnosisList = diagnosisDAO.getDiagnoses(deletePatientRequest.getPatientId());
        List<Medication> medicationList = medicationDAO.getMedications(deletePatientRequest.getPatientId());
        diagnosisDAO.deleteDiagnosisBatch(diagnosisList);
        medicationDAO.deleteMedicationBatch(medicationList);
        patient.setPatientId(deletePatientRequest.getPatientId());
        patientDAO.deletePatient(patient);


        PatientModel patientModel = new ModelConverter().toPatientModel(patient);
        return DeletePatientResult.builder()
                .withPatientModel(patientModel)
                .build();
    }
}
