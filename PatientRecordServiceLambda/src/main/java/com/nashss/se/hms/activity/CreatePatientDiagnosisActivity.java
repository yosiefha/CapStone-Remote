package com.nashss.se.hms.activity;

import com.nashss.se.hms.activity.requests.CreatePatientDiagnosisRequest;
import com.nashss.se.hms.activity.results.CreatePatientDiagnosisResult;
import com.nashss.se.hms.converters.ModelConverter;
import com.nashss.se.hms.dynamodb.DiagnosisDAO;
import com.nashss.se.hms.dynamodb.models.Diagnosis;
import com.nashss.se.hms.models.DiagnosisModel;

import javax.inject.Inject;

/**
 * This class represents an activity for creating a diagnosis for a patient.
 */
public class CreatePatientDiagnosisActivity {


    private final DiagnosisDAO diagnosisDAO;

    /**
     * This method initializes a CreatePatientDiagnosisActivity object
     * with the provided DiagnosisDAO dependency.
     *
     * @param diagnosisDAO The DiagnosisDAO dependency used for
     *                     saving patient diagnoses.
     */
    @Inject
    public CreatePatientDiagnosisActivity(DiagnosisDAO diagnosisDAO) {
        this.diagnosisDAO = diagnosisDAO;
    }

    /**
     * Handles a request to create a patient diagnosis.
     *
     * @param searchPatientDiagnosisRequest The request object containing the
     *                                      information for creating the diagnosis.
     * @return The result of creating the patient diagnosis.
     */
    public CreatePatientDiagnosisResult handleRequest(final CreatePatientDiagnosisRequest
                                                              searchPatientDiagnosisRequest) {
        Diagnosis newDiagnosis = new Diagnosis();
        newDiagnosis.setDiagnosisId(searchPatientDiagnosisRequest.getDiagnosisId());
        newDiagnosis.setPatientId(searchPatientDiagnosisRequest.getPatientId());
        newDiagnosis.setDescription(searchPatientDiagnosisRequest.getDescription());
        newDiagnosis.setHealthcareProfessionalId(searchPatientDiagnosisRequest.getHealthcareProfessionalId());
        newDiagnosis.setDateCreated(searchPatientDiagnosisRequest.getDateCreated());
        diagnosisDAO.savePatientDiagnoses(newDiagnosis);


        DiagnosisModel diagnosisModel = new ModelConverter().toDiagnosisModel(newDiagnosis);


        return CreatePatientDiagnosisResult.builder()
                .withDiagnosis(diagnosisModel)
                .build();
    }
}
