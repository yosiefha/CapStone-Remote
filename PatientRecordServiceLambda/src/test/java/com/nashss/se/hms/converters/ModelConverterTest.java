package com.nashss.se.hms.converters;

import com.nashss.se.hms.dynamodb.models.Diagnosis;
import com.nashss.se.hms.dynamodb.models.Medication;
import com.nashss.se.hms.dynamodb.models.Patient;
import com.nashss.se.hms.models.DiagnosisModel;
import com.nashss.se.hms.models.MedicationModel;
import com.nashss.se.hms.models.PatientModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModelConverterTest {
    private ModelConverter modelConverter = new ModelConverter();

    @Test
    void toPatientModel_convertsPatient() {
        Patient patient = new Patient();
        patient.setPatientId("id");
        patient.setFirstName("Isak");
        patient.setLastName("Mehari");
        patient.setDateOfBirth("08.07.1985");
        patient.setContactNumber("1234567");
        patient.setEmailAddress("isak@gmail.com");
        patient.setAddress("California");


        PatientModel patientModel = modelConverter.toPatientModel(patient);
        assertEquals(patient.getPatientId(), patientModel.getPatientId());
        assertEquals(patient.getFirstName(), patientModel.getFirstName());
        assertEquals(patient.getDateOfBirth(), patientModel.getDOB());
        assertEquals(patient.getContactNumber() , patientModel.getContactNumber());
        assertEquals(patient.getEmailAddress() , patientModel.getEmailAddress());
        assertEquals(patient.getAddress() , patientModel.getAddress());
    }

    @Test
    void toDiagnosisModel_convertsDiagnosis() {
        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setPatientId("id");
        diagnosis.setDiagnosisId("123");
        diagnosis.setDateCreated("12.01.2023");
        diagnosis.setDescription("Fever");
        diagnosis.setHealthcareProfessionalId("ann@gmail.com");

        DiagnosisModel diagnosisModel = modelConverter.toDiagnosisModel(diagnosis);

        assertEquals(diagnosis.getDiagnosisId(), diagnosisModel.getDiagnosisId());
        assertEquals(diagnosis.getPatientId(), diagnosisModel.getPatientId());
        assertEquals(diagnosis.getDateCreated(), diagnosisModel.getDateCreated());
        assertEquals(diagnosis.getDescription(), diagnosisModel.getDescription());
        assertEquals(diagnosis.getHealthcareProfessionalId(), diagnosisModel.getHealthcareProfessionalId());

    }

    @Test
    void toMedicationModel_convertsMedication() {
        Medication medication = new Medication();
        medication.setMedicationId("id");
        medication.setPatientId("123");
        medication.setMedicationName("Asprin");
        medication.setInstructions("Once per day");
        medication.setStartDate("12.01.2023");
        medication.setEndDate("12.15.2023");
        medication.setDosage("5mg");

        MedicationModel  medicationModel = modelConverter.toMedicationModel(medication);

        assertEquals(medication.getMedicationId(), medicationModel.getMedicationId());
        assertEquals(medication.getPatientId(), medicationModel.getPatientId());
        assertEquals(medication.getMedicationName(), medicationModel.getMedicationName());
        assertEquals(medication.getInstructions(), medicationModel.getInstructions());
        assertEquals(medication.getStartDate(), medicationModel.getStartDate());
        assertEquals(medication.getEndDate(), medicationModel.getEndDate());
        assertEquals(medication.getDosage(), medicationModel.getDosage());

    }







}