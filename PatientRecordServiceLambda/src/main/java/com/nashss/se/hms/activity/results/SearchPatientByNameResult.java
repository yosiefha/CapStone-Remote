package com.nashss.se.hms.activity.results;

import com.nashss.se.hms.models.PatientModel;

import java.util.List;

/**
 * Represents the result of a patient search by name.
 */
public class SearchPatientByNameResult {
    private final List<PatientModel> patientModelList;

    /**
     * Creates a SearchPatientByNameResult object.
     * @param patientModelList The list of patient models that match the search criteria.
     */
    private SearchPatientByNameResult(List<PatientModel> patientModelList) {

        this.patientModelList = patientModelList;
    }

    /**
     * Retrieves the list of PatientModel objects.
     * @return The list of PatientModel objects.
     */
    public List<PatientModel> getPatientModelList() {
        return patientModelList;
    }

    @Override
    public String toString() {
        return "SearchPatientByNameResult{" +
                "patientModelList=" + patientModelList +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<PatientModel> patientModelList;

        public Builder withPatientList(List<PatientModel> patientModelList) {
            this.patientModelList = patientModelList;
            return this;
        }

        public SearchPatientByNameResult build() {

            return new SearchPatientByNameResult(patientModelList);
        }
    }

}
