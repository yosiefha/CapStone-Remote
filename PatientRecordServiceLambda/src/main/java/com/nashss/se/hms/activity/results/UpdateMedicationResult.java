package com.nashss.se.hms.activity.results;

import com.nashss.se.hms.models.MedicationModel;

/**
 * UpdateMedicationResult class represents the result of updating medication details.
 */
public class UpdateMedicationResult {

    private final MedicationModel medicationModel;

    /**
     * UpdateMedicationResult represents the result of updating medication details.
     * @param medicationModel the MedicationModel object containing the updated medication details
     */
    private UpdateMedicationResult(MedicationModel medicationModel) {
        this.medicationModel = medicationModel;
    }

    /**
     * Retrieves the MedicationModel object associated with this UpdateMedicationResult.
     * @return the MedicationModel object associated with this UpdateMedicationResult
     */
    public MedicationModel getMedicationModel() {
        return medicationModel;
    }

    @Override
    public String toString() {
        return "UpdateMedicationResult{" +
                "medicationModel=" + medicationModel +
                '}';
    }

    //CHECKSTYLE:OFF:Builder

    public static Builder builder() {
        return new UpdateMedicationResult.Builder();
    }

    public static class Builder {
        private MedicationModel medicationModel;

        public Builder withMedicationModel(MedicationModel medicationModel) {
            this.medicationModel = medicationModel;
            return this;
        }

        public UpdateMedicationResult build() {
            return new UpdateMedicationResult(medicationModel);
        }
    }
}
