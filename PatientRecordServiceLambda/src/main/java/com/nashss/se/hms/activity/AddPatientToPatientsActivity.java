package com.nashss.se.hms.activity;

import com.nashss.se.hms.activity.requests.AddPatientToPatientsRequest;
import com.nashss.se.hms.activity.results.AddPatientToPatientsResult;
import com.nashss.se.hms.converters.ModelConverter;
import com.nashss.se.hms.dynamodb.PatientDAO;
import com.nashss.se.hms.dynamodb.models.Patient;
import com.nashss.se.hms.models.PatientModel;

import java.util.UUID;
import javax.inject.Inject;



/** the database.
 * A new patient is created in the `handleRequest` method. This method accepts an `AddPatientToPatientsRequest`
 * object, which contains the relevant details necessary for creating a new patient such as first name, last name,
 * date of birth, contact number, email address, and physical address. Upon setting these attributes, the new patient
 * is saved using the `patientsDAO` database interface.
 * It returns an `AddPatientToPatientsResult` object, containing the newly added patient's details converted
 * into a `PatientModel` format.
 */

public class AddPatientToPatientsActivity {
    /**
     * @param patientsDAO represents an instance of the PatientDAO class, which is
     * responsible for interacting with the database
     * to perform operations related to patients. It is injected into the
     * AddPatientToPatientsActivity class to save new
     * patients and retrieve patient information.
     */
    private final PatientDAO patientsDAO;

    /**
     * Injects a new patient into the patients database.
     * @param patientsDAO an instance of the PatientDAO class responsible
     * for interacting with the database.
     */
    @Inject
    public AddPatientToPatientsActivity(PatientDAO patientsDAO) {
        this.patientsDAO = patientsDAO;

    }

    /**
     * Handles a request to add a new patient to the patients database.
     * @param addPatientToPatientsRequest the request object containing the details of the patient to be added.
     * @return the result object containing the newly added patient's details in a PatientModel format.
     */
    public AddPatientToPatientsResult handleRequest(final AddPatientToPatientsRequest addPatientToPatientsRequest) {
        Patient newPatient = new Patient();
        newPatient.setPatientId(UUID.randomUUID().toString());
        newPatient.setFirstName(addPatientToPatientsRequest.getFirstName());
        newPatient.setLastName(addPatientToPatientsRequest.getLastName());
        newPatient.setDateOfBirth(addPatientToPatientsRequest.getDOB());
        newPatient.setContactNumber(addPatientToPatientsRequest.getContactNumber());
        newPatient.setEmailAddress(addPatientToPatientsRequest.getEmailAddress());
        newPatient.setAddress(addPatientToPatientsRequest.getAddress());
        patientsDAO.savePatient(newPatient);

        PatientModel patientModel = new ModelConverter().toPatientModel(newPatient);


        return AddPatientToPatientsResult.builder()
           .withPatient(patientModel)
           .build();


    }
}
