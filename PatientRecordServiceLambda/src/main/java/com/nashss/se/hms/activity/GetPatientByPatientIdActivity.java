package com.nashss.se.hms.activity;

import com.nashss.se.hms.activity.requests.GetPatientByPatientIdRequest;
import com.nashss.se.hms.activity.results.GetPatientByPatientIdResult;
import com.nashss.se.hms.converters.ModelConverter;
import com.nashss.se.hms.dynamodb.PatientDAO;
import com.nashss.se.hms.dynamodb.models.Patient;
import com.nashss.se.hms.models.PatientModel;

import javax.inject.Inject;

/**
 * This class represents an activity to get a patient by their ID.
 */
public class GetPatientByPatientIdActivity {

    private final PatientDAO patientDAO;

    /**
     * Initializes a new instance of the GetPatientByPatientIdActivity class.
     *
     * @param patientDAO The PatientDAO object used to interact
     * with the patient data.
     */
    @Inject
    public GetPatientByPatientIdActivity(PatientDAO patientDAO) {
        this.patientDAO = patientDAO;
    }

    /**
     * Handles a request to get a patient by their ID.
     * @param getPatientByPatientIdRequest The request object containing
     * the patient ID.
     * @return The result object containing the patient details.
     */
    public GetPatientByPatientIdResult handleRequest(final GetPatientByPatientIdRequest getPatientByPatientIdRequest) {
        String patientId = getPatientByPatientIdRequest.getPatientId();
        Patient patient = patientDAO.getPatientByPatientId(patientId);
        PatientModel patientModel = new ModelConverter().toPatientModel(patient);

        return GetPatientByPatientIdResult.builder()
                .withPatientId(patientModel)
                .build();
    }
}
