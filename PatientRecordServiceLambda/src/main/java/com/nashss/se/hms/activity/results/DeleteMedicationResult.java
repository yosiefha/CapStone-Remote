package com.nashss.se.hms.activity.results;

import com.nashss.se.hms.models.MedicationModel;

/**
 * Represents the result of deleting a medication.
 */
public class DeleteMedicationResult {

    private final MedicationModel medicationModel;

    /**
     * @param medicationModel input model.
     */
    private DeleteMedicationResult(MedicationModel medicationModel) {

        this.medicationModel = medicationModel;
    }

    /**
     * Retrieves the MedicationModel associated with the DeleteMedicationResult.
     * @return The MedicationModel associated with the DeleteMedicationResult.
     */
    public MedicationModel getMedicationModel() {
        return medicationModel;
    }

    @Override
    public String toString() {
        return "DeleteMedicationResult{" +
                "medicationModel=" + medicationModel +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private MedicationModel medicationModel;

        public Builder withMedicationModel(MedicationModel medicationModel) {
            this.medicationModel = medicationModel;
            return this;
        }

        public DeleteMedicationResult build() {
            return new DeleteMedicationResult(medicationModel);
        }
    }
}
