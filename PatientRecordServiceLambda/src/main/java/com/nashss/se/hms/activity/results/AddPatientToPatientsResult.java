package com.nashss.se.hms.activity.results;

import com.nashss.se.hms.models.PatientModel;

/**
 * Represents a result object after adding a patient to the patients database.
 */
public class AddPatientToPatientsResult {
    private final PatientModel patient;

    /**
     * Adds a patient to the patients database and returns the result object.
     * @param patient the patient to be added to the database
     */
    private AddPatientToPatientsResult(PatientModel patient) {
        this.patient = patient;
    }

    /**
     * Retrieves the patient object associated with this result.
     * @return the patient object
     */
    public PatientModel getPatient() {
        return patient;
    }

    @Override
    public String toString() {
        return "AddPatientToPatientsResult{" +
                "patient=" + patient +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private PatientModel patient;

        public Builder withPatient(PatientModel patient) {
            this.patient = patient;
            return this;
        }

        public AddPatientToPatientsResult build() {
            return new AddPatientToPatientsResult(patient);
        }
    }
}
