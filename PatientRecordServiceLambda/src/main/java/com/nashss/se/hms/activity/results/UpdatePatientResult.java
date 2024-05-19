package com.nashss.se.hms.activity.results;

import com.nashss.se.hms.models.PatientModel;

/**
 * Represents the result of updating a patient's information.
 */
public class UpdatePatientResult {
    private final PatientModel patientModel;


    /**
     * Updates the patient result with the given patient model.
     *
     * @param patientModel the patient model containing the updated information
     */
    private UpdatePatientResult(PatientModel patientModel) {
        this.patientModel = patientModel;
    }

    /**
     * Retrieves the patient model associated with the patient.
     * @return the patient model
     */
    public PatientModel getPatientModel() {
        return patientModel;
    }

    @Override
    public String toString() {
        return "UpdatePatientResult{" +
                "patientModel=" + patientModel +
                '}';
    }

    //CHECKSTYLE:OFF:Builder

    public static Builder builder() {
        return new UpdatePatientResult.Builder();
    }

    public static class Builder {
        private PatientModel patientModel;

        public Builder withPatientModel(PatientModel patientModel) {
            this.patientModel = patientModel;
            return this;
        }

        public UpdatePatientResult build() {
            return new UpdatePatientResult(patientModel);
        }


    }
}
