package com.nashss.se.hms.activity.results;

import com.nashss.se.hms.models.DiagnosisModel;

import java.util.List;

/**
 * Class representing the result of a diagnosis details retrieval operation.
 */
public class GetDiagnosisDetailsResult {

    private final List<DiagnosisModel> diagnosisModelList;

    /**
     * Initializes a new instance of the GetDiagnosisDetailsResult class with
     * the specified diagnosis model list.
     * @param diagnosisModelList The list of diagnosis models.
     */
    private GetDiagnosisDetailsResult(List<DiagnosisModel> diagnosisModelList) {
        this.diagnosisModelList = diagnosisModelList;
    }

    /**
     * Returns the list of DiagnosisModel objects.
     * @return The list of DiagnosisModel objects.
     */
    public List<DiagnosisModel> getDiagnosisModelList() {
        return diagnosisModelList;
    }

    @Override
    public String toString() {
        return "GetDiagnosisDetailsResult{" +
                "diagnosisModelList=" + diagnosisModelList +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private List<DiagnosisModel> diagnosisModelList;

        public Builder withDiagnosisList(List<DiagnosisModel> diagnosisModelList) {
            this.diagnosisModelList = diagnosisModelList;
            return this;
        }

        public GetDiagnosisDetailsResult build() {
            return new GetDiagnosisDetailsResult(diagnosisModelList);
        }
    }
}
