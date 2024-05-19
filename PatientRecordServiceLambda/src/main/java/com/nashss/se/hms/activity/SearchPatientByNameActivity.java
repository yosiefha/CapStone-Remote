package com.nashss.se.hms.activity;

import com.nashss.se.hms.activity.requests.SearchPatientByNameRequest;
import com.nashss.se.hms.activity.results.SearchPatientByNameResult;
import com.nashss.se.hms.converters.ModelConverter;
import com.nashss.se.hms.dynamodb.PatientDAO;
import com.nashss.se.hms.dynamodb.models.Patient;
import com.nashss.se.hms.models.PatientModel;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;


/**
 * This class represents the activity for searching patients by name.
 */
public class SearchPatientByNameActivity {

    private final PatientDAO patientDAO;

    /**
     * Instantiates a new SearchPatientUsingNameActivity object.
     *
     * @param patientDAO PatientDAO to access the patients table.
     */
    @Inject
    public SearchPatientByNameActivity(PatientDAO patientDAO) {
        this.patientDAO = patientDAO;
    }

    /**
     * Handles a request to search patients by name.
     * @param searchPatientUsingNameRequest the search request
     * containing the first name and last name of the patient
     * @return the search result containing a list of patient models
     */
    public SearchPatientByNameResult handleRequest(final SearchPatientByNameRequest
                                                           searchPatientUsingNameRequest) {

        String firstName = searchPatientUsingNameRequest.getFirstName();
        String lastName = searchPatientUsingNameRequest.getLastName();
        List<Patient> patientlist = patientDAO.searchPatient(firstName, lastName);
        List<PatientModel> patientModelList = new ArrayList<>();
        for (Patient patient : patientlist) {
            patientModelList.add(new ModelConverter().toPatientModel(patient));

        }
        return SearchPatientByNameResult.builder()
                .withPatientList(patientModelList)
                .build();


    }
}



