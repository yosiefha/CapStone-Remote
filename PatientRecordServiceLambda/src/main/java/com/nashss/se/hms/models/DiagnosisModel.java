package com.nashss.se.hms.models;

import java.util.Objects;

/**
 * Represents a diagnosis model.
 */
public class DiagnosisModel {

    private final String diagnosisId;
    private final String description;
    private final String dateCreated;
    private final String healthcareProfessionalId;
    private final String patientId;

    /**
     * Represent  the class constructor.
     *
     * @param diagnosisId              a unique diagnosis ID.
     * @param description              description of the diagnosis.
     * @param dateCreated              the date of creation for the diagnosis.
     * @param healthcareProfessionalId the ID of the health care professional
     *                                 registering it
     * @param patientId                the unique ID of the patient being diagnosed.
     */
    public DiagnosisModel(String diagnosisId, String description, String dateCreated,
                          String healthcareProfessionalId, String patientId) {
        this.diagnosisId = diagnosisId;
        this.description = description;
        this.dateCreated = dateCreated;
        this.healthcareProfessionalId = healthcareProfessionalId;
        this.patientId = patientId;
    }

    /**
     * Compares this DiagnosisModel instance with the specified object for equality.
     *
     * @param o the object to compare with
     * @return true if the specified object is equal to this DiagnosisModel instance, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DiagnosisModel)) {
            return false;
        }
        DiagnosisModel that = (DiagnosisModel) o;
        return Objects.equals(getDiagnosisId(), that.getDiagnosisId()) && Objects.equals(getDescription(),
                that.getDescription()) && Objects.equals(getDateCreated(), that.getDateCreated()) &&
                Objects.equals(getHealthcareProfessionalId(), that.getHealthcareProfessionalId()) &&
                Objects.equals(getPatientId(), that.getPatientId());
    }

    /**
     * Calculates the hash code value for an instance of the DiagnosisModel class.
     *
     * @return The hash code value for the DiagnosisModel instance.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getDiagnosisId(), getDescription(), getDateCreated(),
                getHealthcareProfessionalId(), getPatientId());
    }

    /**
     * Retrieves the diagnosis ID.
     *
     * @return The diagnosis ID as a String.
     */
    public String getDiagnosisId() {
        return diagnosisId;
    }

    /**
     * Retrieves the description of the diagnosis.
     *
     * @return The description of the diagnosis as a String.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Retrieves the date the object was created.
     *
     * @return The date the object was created as a String.
     */
    public String getDateCreated() {
        return dateCreated;
    }

    /**
     * Retrieves the healthcare professional ID.
     *
     * @return The healthcare professional ID as a String.
     */

    public String getHealthcareProfessionalId() {
        return healthcareProfessionalId;
    }

    /**
     * Retrieves the patient ID.
     *
     * @return The patient ID as a String.
     */
    public String getPatientId() {
        return patientId;
    }


    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String diagnosisId;
        private String description;
        private String dateCreated;
        private String healthcareProfessionalId;
        private String patientId;

        public Builder withDiagnosisId(String diagnosisId) {
            this.diagnosisId = diagnosisId;
            return this;
        }

        public Builder withPatientId(String patientId) {
            this.patientId = patientId;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withDateCreated(String dateCreated) {
            this.dateCreated = dateCreated;
            return this;
        }

        public Builder withHealthcareProfessionalId(String healthcareProfessionalId) {
            this.healthcareProfessionalId = healthcareProfessionalId;
            return this;
        }

        public DiagnosisModel build() {
            return new DiagnosisModel(diagnosisId, description, dateCreated, healthcareProfessionalId, patientId);
        }


    }
}
