package com.nashss.se.hms.activity.results;

import com.nashss.se.hms.models.PatientModel;

/**
 * A class representing the result of a delete patient operation.
 */
public class DeletePatientResult {

    private final PatientModel patientModel;

    /**
     * Deletes a patient from the system.
     *
     * @param patientModel the patient model to be deleted.
     */
    private DeletePatientResult(PatientModel patientModel) {
        this.patientModel = patientModel;
    }

    /**
     * Retrieves the PatientModel object from the DeletePatientResult.
     * @return the PatientModel object representing the patient
     */
    public PatientModel getPatientModel() {
        return patientModel;
    }

    @Override
    public String toString() {
        return "DeletePatientResult{" +
                "patient=" + patientModel +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private PatientModel patientModel;

        public Builder withPatientModel(PatientModel patientModel) {
            this.patientModel = patientModel;
            return this;
        }

        public DeletePatientResult build() {
            return new DeletePatientResult(patientModel);
        }
    }
}
