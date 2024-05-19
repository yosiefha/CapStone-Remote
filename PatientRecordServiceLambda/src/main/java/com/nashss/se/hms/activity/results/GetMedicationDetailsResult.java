package com.nashss.se.hms.activity.results;

import com.nashss.se.hms.models.MedicationModel;

import java.util.List;

/**
 * Represents a result object containing the medication details for a patient.
 */
public class GetMedicationDetailsResult {

    private final List<MedicationModel> medicationModelList;

    /**
     * @param medicationModelList input list.
     */
    private GetMedicationDetailsResult(List<MedicationModel> medicationModelList) {
        this.medicationModelList = medicationModelList;
    }

    /**
     * Retrieves the list of medication models associated with the medication details result.
     * @return the list of medication models
     */
    public List<MedicationModel> getMedicationModelList() {
        return medicationModelList;
    }

    @Override
    public String toString() {
        return "GetMedicationDetailsResult{" +
                "medicationModelList=" + medicationModelList +
                '}';
    }

    //CHECKSTYLE:OFF:Builder

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<MedicationModel> medicationModelList;

        public Builder withMedicationList(List<MedicationModel> medicationModelList) {
            this.medicationModelList = medicationModelList;
            return this;
        }

        public GetMedicationDetailsResult build() {
            return new GetMedicationDetailsResult(medicationModelList);
        }
    }
}
