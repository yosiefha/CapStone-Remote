package com.nashss.se.hms.activity;

import com.nashss.se.hms.activity.requests.DeleteDiagnosisRequest;
import com.nashss.se.hms.activity.results.DeleteDiagnosisResult;
import com.nashss.se.hms.converters.ModelConverter;
import com.nashss.se.hms.dynamodb.DiagnosisDAO;
import com.nashss.se.hms.dynamodb.models.Diagnosis;
import com.nashss.se.hms.models.DiagnosisModel;

import javax.inject.Inject;

/**
 * Constructs a new DeleteDiagnosisActivity instance.
 *
 * @param diagnosisDAO the DiagnosisDAO object used to interact with the
 *                     diagnosis database
 */
public class DeleteDiagnosisActivity {

    private final DiagnosisDAO diagnosisDAO;

    /**
     * Constructs a new DeleteDiagnosisActivity instance.
     *
     * @param diagnosisDAO the DiagnosisDAO object used to interact
     *                     with the diagnosis database
     */
    @Inject
    public DeleteDiagnosisActivity(DiagnosisDAO diagnosisDAO) {
        this.diagnosisDAO = diagnosisDAO;
    }

    /**
     * Handles a delete diagnosis request.
     * @param deleteDiagnosisRequest The request object containing the
     * diagnosis ID to delete.
     * @return The result object containing the deleted diagnosis model.
     */
    public DeleteDiagnosisResult handleRequest(final DeleteDiagnosisRequest
                                                       deleteDiagnosisRequest) {
        Diagnosis newDiagnosis = new Diagnosis();
        newDiagnosis.setDiagnosisId(deleteDiagnosisRequest.getDiagnosisId());
        diagnosisDAO.deleteDiagnosis(newDiagnosis);
        DiagnosisModel diagnosisModel = new ModelConverter().toDiagnosisModel(newDiagnosis);

        return DeleteDiagnosisResult.builder()
                .withDiagnosisModel(diagnosisModel)
                .build();
    }
}
