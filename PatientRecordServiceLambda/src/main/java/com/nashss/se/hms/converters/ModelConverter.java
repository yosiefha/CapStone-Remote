package com.nashss.se.hms.converters;

import com.nashss.se.hms.dynamodb.models.Diagnosis;
import com.nashss.se.hms.dynamodb.models.Medication;
import com.nashss.se.hms.dynamodb.models.Patient;
import com.nashss.se.hms.models.DiagnosisModel;
import com.nashss.se.hms.models.MedicationModel;
import com.nashss.se.hms.models.PatientModel;

/**
 * The ModelConverter class is responsible for converting various objects to their corresponding model objects.
 */
public class ModelConverter {

    /**
     * Converts a Patient object to a PatientModel object.
     *
     * @param patient the Patient object to be converted
     * @return the corresponding PatientModel object, or null if the patient is null
     */
    public PatientModel toPatientModel(Patient patient) {

        if (patient == null) {
            return null;
        }
        return PatientModel.builder()
                .withPatientId(patient.getPatientId())
                .withFirstName(patient.getFirstName())
                .withLastName(patient.getLastName())
                .withDOB(patient.getDateOfBirth())
                .withContactNumber(patient.getContactNumber())
                .withEmailAddress(patient.getEmailAddress())
                .withAddress(patient.getAddress())
                .build();
    }

    /**
     * Converts a Diagnosis object to a DiagnosisModel object.
     *
     * @param diagnosis the Diagnosis object to be converted
     * @return the corresponding DiagnosisModel object, or null if the diagnosis is null
     */
    public DiagnosisModel toDiagnosisModel(Diagnosis diagnosis) {
        if (diagnosis == null) {
            return null;
        }
        return DiagnosisModel.builder()
                .withDiagnosisId(diagnosis.getDiagnosisId())
                .withDescription(diagnosis.getDescription())
                .withDateCreated(diagnosis.getDateCreated())
                .withHealthcareProfessionalId(diagnosis.getHealthcareProfessionalId())
                .withPatientId(diagnosis.getPatientId())
                .build();
    }


    /**
     * Converts a {@link Medication} object to a {@link MedicationModel} object.
     *
     * @param medication the {@link Medication} object to be converted
     * @return the corresponding {@link MedicationModel} object, or null if the medication is null
     */
    public MedicationModel toMedicationModel(Medication medication) {
        if (medication == null) {
            return null;
        }

        return MedicationModel.builder()
                .withMedicationId(medication.getMedicationId())
                .withMedicationName(medication.getMedicationName())
                .withDosage(medication.getDosage())
                .withEndDate(medication.getEndDate())
                .withInstructions(medication.getInstructions())
                .withStartDate(medication.getStartDate())
                .withPatientId(medication.getPatientId())
                .build();
    }
}
