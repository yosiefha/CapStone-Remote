package com.nashss.se.hms.dependency;

import com.nashss.se.hms.activity.AddPatientToPatientsActivity;
import com.nashss.se.hms.activity.CreatePatientDiagnosisActivity;
import com.nashss.se.hms.activity.CreatePatientMedicationActivity;
import com.nashss.se.hms.activity.DeleteDiagnosisActivity;
import com.nashss.se.hms.activity.DeleteMedicationActivity;
import com.nashss.se.hms.activity.DeletePatientActivity;
import com.nashss.se.hms.activity.GetDiagnosisDetailsActivity;
import com.nashss.se.hms.activity.GetMedicationDetailsActivity;
import com.nashss.se.hms.activity.GetPatientByPatientIdActivity;
import com.nashss.se.hms.activity.SearchPatientByNameActivity;
import com.nashss.se.hms.activity.UpdateDiagnosisActivity;
import com.nashss.se.hms.activity.UpdateMedicationActivity;
import com.nashss.se.hms.activity.UpdatePatientActivity;

import dagger.Component;

import javax.inject.Singleton;

/**
 * Dagger component for providing dependency injection in the Music Playlist Service.
 */
@Singleton
@Component(modules = {DaoModule.class, MetricsModule.class})
public interface ServiceComponent {

    /**
     * Provides the relevant activity.
     *
     * @return AddPatientToPatientsActivity
     */
    AddPatientToPatientsActivity provideAddPatientToPatientsActivity();

    /**
     * Provides an instance of the SearchPatientByNameActivity class.
     *
     * @return an instance of the SearchPatientByNameActivity class.
     */
    SearchPatientByNameActivity provideSearchPatientByNameActivity();

    /**
     * This method provides an instance of CreatePatientDiagnosisActivity,
     * which represents an activity for creating a diagnosis for a patient.
     *
     * @return An instance of CreatePatientDiagnosisActivity.
     */
    CreatePatientDiagnosisActivity provideCreatePatientDiagnosisActivity();

    /**
     * Provides an instance of CreatePatientMedicationActivity,
     * which represents an activity for creating a patient medication.
     *
     * @return An instance of CreatePatientMedicationActivity.
     */
    CreatePatientMedicationActivity provideCreatePatientMedicationActivity();

    /**
     * Provides an instance of GetPatientByPatientIdActivity,
     * which represents an activity to get a patient by their ID.
     *
     * @return An instance of GetPatientByPatientIdActivity.
     */
    GetPatientByPatientIdActivity provideGetPatientByPatientIdActivity();

    /**
     * Returns an instance of the GetDiagnosisDetailsActivity class
     * which handles the request to get diagnosis details for a patient.
     *
     * @return an instance of GetDiagnosisDetailsActivity.
     */
    GetDiagnosisDetailsActivity provideGetDiagnosisDetailsActivity();

    /**
     * Retrieves medication details for a patient.
     *
     * @return The result object containing the list of medication details.
     */
    GetMedicationDetailsActivity provideGetMedicationDetailsActivity();

    /**
     * Provides an instance of DeletePatientActivity.
     *
     * @return An instance of DeletePatientActivity.
     */
    DeletePatientActivity provideDeletePatientActivity();

    /**
     * Provides a DeleteDiagnosisActivity object which represents
     * an activity for deleting a diagnosis.
     *
     * @return an instance of DeleteDiagnosisActivity
     */
    DeleteDiagnosisActivity provideDeleteDiagnosisActivity();

    /**
     * Provides an instance of DeleteMedicationActivity,
     * which represents an activity for deleting a medication.
     *
     * @return An instance of DeleteMedicationActivity.
     */
    DeleteMedicationActivity provideDeleteMedicationActivity();

    /**
     * Provides an instance of UpdatePatientActivity,
     * which represents an activity for updating a patient's information.
     *
     * @return An instance of UpdatePatientActivity.
     */
    UpdatePatientActivity provideUpdatePatientActivity();

    /**
     * Retrieves an instance of the UpdateMedicationActivity class,
     * which represents an activity for updating medication details.
     *
     * @return An instance of the UpdateMedicationActivity class.
     */
    UpdateMedicationActivity provideUpdateMedicationActivity();

    /**
     * Provides an instance of UpdateDiagnosisActivity,
     * which represents an activity for updating a diagnosis.
     *
     * @return an instance of UpdateDiagnosisActivity.
     */
    UpdateDiagnosisActivity provideUpdateDiagnosisActivity();


}
