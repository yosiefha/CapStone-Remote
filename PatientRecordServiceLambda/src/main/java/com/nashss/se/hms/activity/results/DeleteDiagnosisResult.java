package com.nashss.se.hms.activity.results;

import com.nashss.se.hms.models.DiagnosisModel;


/**
 * This class represents the result of a delete diagnosis operation.
 */
public class DeleteDiagnosisResult {

    private final DiagnosisModel diagnosisModel;

    /**
     * Initializes a new instance of the DeleteDiagnosisResult class.
     * @param diagnosisModel The DiagnosisModel object representing the diagnosis to be deleted.
     */
    private DeleteDiagnosisResult(DiagnosisModel diagnosisModel) {
        this.diagnosisModel = diagnosisModel;
    }

    /**
     * Retrieves the DiagnosisModel object associated with this DeleteDiagnosisResult.
     * @return the DiagnosisModel object representing the diagnosis that was deleted
     */
    public DiagnosisModel getDiagnosisModel() {
        return diagnosisModel;
    }

    @Override
    public String toString() {
        return "DeleteDiagnosisResult{" +
                "diagnosisModel=" + diagnosisModel +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private DiagnosisModel diagnosisModel;

        public Builder withDiagnosisModel(DiagnosisModel diagnosisModel) {
            this.diagnosisModel = diagnosisModel;
            return this;
        }

        public DeleteDiagnosisResult build() {
            return new DeleteDiagnosisResult(diagnosisModel);
        }
    }
}
