package com.nashss.se.hms.activity.results;

import com.nashss.se.hms.models.MedicationModel;


/**
 * This class represents the result of creating a patient medication.
 */
public class CreatePatientMedicationResult {

    private final MedicationModel medicationModel;

    /**
     * Creates a new instance of CreatePatientMedicationResult.
     * @param medicationModel the MedicationModel object representing the patient's medication information
     */
    private CreatePatientMedicationResult(MedicationModel medicationModel) {
        this.medicationModel = medicationModel;
    }

    /**
     * Retrieves the MedicationModel object representing the patient's medication information.
     * @return the MedicationModel object representing the patient's medication information
     */
    public MedicationModel getMedicationModel() {
        return medicationModel;
    }

    @Override
    public String toString() {
        return "CreatePatientMedicationResult{" +
                "medicationModel=" + medicationModel +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {

        return new CreatePatientMedicationResult.Builder();
    }

    public static class Builder {
        private MedicationModel medicationModel;

        public Builder withMedication(MedicationModel medicationModel) {
            this.medicationModel = medicationModel;
            return this;
        }

        public CreatePatientMedicationResult build() {

            return new CreatePatientMedicationResult(medicationModel);
        }
    }
}
