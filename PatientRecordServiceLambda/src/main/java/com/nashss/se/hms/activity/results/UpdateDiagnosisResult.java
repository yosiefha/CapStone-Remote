package com.nashss.se.hms.activity.results;

import com.nashss.se.hms.models.DiagnosisModel;

/**
 * Represents the result of updating a diagnosis.
 */
public class UpdateDiagnosisResult {

    private final DiagnosisModel diagnosisModel;

    /**
     * Initializes a new instance of the UpdateDiagnosisResult
     * class with a DiagnosisModel object.
     * @param diagnosisModel model input.
     */
    private UpdateDiagnosisResult(DiagnosisModel diagnosisModel) {
        this.diagnosisModel = diagnosisModel;
    }

    /**
     *
     * @return nothing.
     */
    public DiagnosisModel getDiagnosisModel() {
        return diagnosisModel;
    }

    @Override
    public String toString() {
        return "UpdateDiagnosisResult{" +
                "diagnosisModel=" + diagnosisModel +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new UpdateDiagnosisResult.Builder();
    }

    public static class Builder {
        private DiagnosisModel diagnosisModel;

        public Builder withDiagnosisModel(DiagnosisModel diagnosisModel) {
            this.diagnosisModel = diagnosisModel;
            return this;
        }

        public UpdateDiagnosisResult build() {
            return new UpdateDiagnosisResult(diagnosisModel);
        }
    }

}
