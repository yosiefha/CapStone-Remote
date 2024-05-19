package com.nashss.se.hms.activity;

import com.nashss.se.hms.activity.requests.UpdatePatientRequest;
import com.nashss.se.hms.activity.results.UpdatePatientResult;
import com.nashss.se.hms.converters.ModelConverter;
import com.nashss.se.hms.dynamodb.PatientDAO;
import com.nashss.se.hms.dynamodb.models.Patient;

import javax.inject.Inject;

/**
 * This class represents the activity for updating a patient's information.
 */
public class UpdatePatientActivity {

    private final PatientDAO patientDAO;

    /**
     * This method handles the request to update a patient's information.
     * @param patientDAO the PatientDAO object used to access the patient data.
     *
     */
    @Inject
    public UpdatePatientActivity(PatientDAO patientDAO) {
        this.patientDAO = patientDAO;
    }


    /**
     * Handles the request to update a patient's information.
     * @param updatePatientRequest the UpdatePatientRequest object
     * containing the updated patient information
     * @return an UpdatePatientResult object containing the updated patient model
     */
    public UpdatePatientResult handleRequest(final UpdatePatientRequest updatePatientRequest) {

        Patient patient = patientDAO.getPatientByPatientId(updatePatientRequest.getPatientId());
        if (patient == null) {
            return UpdatePatientResult.builder()
                    .withPatientModel(new ModelConverter().toPatientModel(patient))
                    .build();

        }
        String patientId = updatePatientRequest.getPatientId();
        String firstName = updatePatientRequest.getFirstName();
        String lastName = updatePatientRequest.getLastName();
        String dateOfBirth = updatePatientRequest.getDOB();
        String contactNumber = updatePatientRequest.getContactNumber();
        String emailAddress = updatePatientRequest.getEmailAddress();
        String address = updatePatientRequest.getAddress();

        if (firstName != null) {
            patient.setFirstName(firstName);
        }
        if (lastName != null) {
            patient.setLastName(lastName);
        }
        if (dateOfBirth != null) {
            patient.setDateOfBirth(dateOfBirth);
        }
        if (contactNumber != null) {
            patient.setContactNumber(contactNumber);
        }
        if (emailAddress != null) {
            patient.setEmailAddress(emailAddress);
        }
        if (address != null) {
            patient.setAddress(address);
        }
        patient.setPatientId(patientId);
        patient = patientDAO.savePatient(patient);

        return UpdatePatientResult.builder()
                .withPatientModel(new ModelConverter().toPatientModel(patient))
                .build();


    }
}
