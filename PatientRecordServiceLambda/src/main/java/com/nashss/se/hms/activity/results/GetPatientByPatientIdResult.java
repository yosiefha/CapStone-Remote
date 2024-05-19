package com.nashss.se.hms.activity.results;

import com.nashss.se.hms.models.PatientModel;

/**
 * Represents the result of the GetPatientByPatientId operation.
 */
public class GetPatientByPatientIdResult {

    private final PatientModel patientModel;

    /**
     * Constructs a new GetPatientByPatientIdResult object with the provided PatientModel.
     * @param patientModel the PatientModel object representing the patient information
     */
    private GetPatientByPatientIdResult(PatientModel patientModel) {
        this.patientModel = patientModel;
    }

    /**
     * Retrieves the PatientModel object representing the patient information.
     * @return the PatientModel object representing the patient information
     */
    public PatientModel getPatientModel() {
        return patientModel;
    }

    @Override
    public String toString() {
        return "GetPatientByPatientIdResult{" +
                "patientModel=" + patientModel +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private PatientModel patientModel;

        public Builder withPatientId(PatientModel patientModel) {
            this.patientModel = patientModel;
            return this;
        }

        public GetPatientByPatientIdResult build() {
            return new GetPatientByPatientIdResult(patientModel);
        }

    }
}
