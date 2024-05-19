package com.nashss.se.hms.activity;

import com.nashss.se.hms.activity.requests.GetDiagnosisDetailsRequest;
import com.nashss.se.hms.activity.results.GetDiagnosisDetailsResult;
import com.nashss.se.hms.converters.ModelConverter;
import com.nashss.se.hms.dynamodb.DiagnosisDAO;
import com.nashss.se.hms.dynamodb.models.Diagnosis;
import com.nashss.se.hms.models.DiagnosisModel;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;


/**
 * GetDiagnosisDetailsActivity class handles the request to get diagnosis details for a patient.
 */
public class GetDiagnosisDetailsActivity {

    private final DiagnosisDAO diagnosisDAO;

    /**
     * GetDiagnosisDetailsActivity class handles the request to get diagnosis
     * details for a patient.
     * @param diagnosisDAO The DiagnosisDAO object used to interact with
     * the database.
     */
    @Inject
    public GetDiagnosisDetailsActivity(DiagnosisDAO diagnosisDAO) {
        this.diagnosisDAO = diagnosisDAO;
    }

    /**
     * Handles the request to get diagnosis details for a patient.
     * @param getDiagnosisDetailsRequest The GetDiagnosisDetailsRequest object
     *  containing the patient ID.
     * @return The GetDiagnosisDetailsResult object containing the
     * diagnosis details.
     */
    public GetDiagnosisDetailsResult handleRequest(final GetDiagnosisDetailsRequest getDiagnosisDetailsRequest) {
        String patientId = getDiagnosisDetailsRequest.getPatientId();
        List<Diagnosis> diagnosisList = diagnosisDAO.getDiagnoses(patientId);
        List<DiagnosisModel> diagnosisModelList = new ArrayList<>();
        for (Diagnosis diagnosis : diagnosisList) {
            diagnosisModelList.add(new ModelConverter().toDiagnosisModel(diagnosis));

        }

        return GetDiagnosisDetailsResult.builder()
                .withDiagnosisList(diagnosisModelList)
                .build();
    }
}
