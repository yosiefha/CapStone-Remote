package com.nashss.se.hms.activity.results;

import com.nashss.se.hms.models.DiagnosisModel;

/**
 * The CreatePatientDiagnosisResult class represents the result of creating a patient diagnosis.
 * It encapsulates a DiagnosisModel object.
 */
public class CreatePatientDiagnosisResult {

    private final DiagnosisModel diagnosisModel;

    /**
     * Creates a new CreatePatientDiagnosisResult object with the given DiagnosisModel.
     * @param diagnosisModel the DiagnosisModel object to be encapsulated in
     *                       the CreatePatientDiagnosisResult
     */
    private CreatePatientDiagnosisResult(DiagnosisModel diagnosisModel) {
        this.diagnosisModel = diagnosisModel;

    }

    /**
     * Retrieves the DiagnosisModel object.
     * @return the DiagnosisModel object
     */
    public DiagnosisModel getDiagnosisModel() {
        return diagnosisModel;
    }

    @Override
    public String toString() {
        return "CreatePatientDiagnosisResult{" +
                "diagnosisModel=" + diagnosisModel +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {

        return new CreatePatientDiagnosisResult.Builder();
    }

    public static class Builder {
        private DiagnosisModel diagnosisModel;

        public Builder withDiagnosis(DiagnosisModel diagnosisModel) {
            this.diagnosisModel = diagnosisModel;
            return this;
        }

        public CreatePatientDiagnosisResult build() {

            return new CreatePatientDiagnosisResult(diagnosisModel);
        }
    }
}
