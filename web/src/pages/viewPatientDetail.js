import PatientRecordClient from '../api/patientRecordClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";

/**
 * Logic needed for the view playlist page of the website.
 */

 const SEARCH_CRITERIA_KEY = 'patientId';
 const SEARCH_RESULTS_KEY_DIAGNOSIS = 'search-results-diagnosis';
 const SEARCH_RESULTS_KEY_MEDICATION = 'search-results-medication';
 const EMPTY_DATASTORE_STATE = {
     [SEARCH_CRITERIA_KEY]: '',
     [SEARCH_RESULTS_KEY_DIAGNOSIS]: [],
     [SEARCH_RESULTS_KEY_MEDICATION]: [],

 };

 let viewPatientDetail;
class ViewPatientDetail extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount','addDiagnosis','addMedication','updateRow','saveRow'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);


    }


async clientLoaded() {
            this.client = new PatientRecordClient();
            const urlParams = new URLSearchParams(window.location.search);
            const patientId = urlParams.get('id');
            // document.getElementById('patient-name').innerText = "Loading...";
            const patient = await this.client.getPatient(patientId , (error)=>{
                                        errorMessageDisplay.innerText = `Error: ${error.message}`;
                                                errorMessageDisplay.classList.remove('hidden');
                                                });
            document.getElementById('patient-full-name').innerText = patient.firstName+" "+patient.lastName;

            // -----------------------Load the Diagnosis Details--------------------------------
            const results = await this.client.getPatientDiagnosis(patientId, (error)=>{
                     errorMessageDisplay.innerText = `Error: ${error.message}`;
                     errorMessageDisplay.classList.remove('hidden');
                     } );
            if(patientId){
            this.dataStore.setState({
                       [SEARCH_CRITERIA_KEY]: patientId,
                       [SEARCH_RESULTS_KEY_DIAGNOSIS]: results,
                       [SEARCH_RESULTS_KEY_MEDICATION]: [],
                   });
            }else{
            this.dataStore.setState(EMPTY_DATASTORE_STATE);
            }

            this.displaySearchResults();
            //-------------------------------  Load the medication Details----------------------------------

            if(patientId){

            const results = await this.client.getPatientMedication(patientId, (error)=>{
                          errorMessageDisplay.innerText = `Error: ${error.message}`;
                          errorMessageDisplay.classList.remove('hidden');
                          } );

            this.dataStore.setState({
                               [SEARCH_CRITERIA_KEY]: patientId,
                               [SEARCH_RESULTS_KEY_DIAGNOSIS]: [],
                               [SEARCH_RESULTS_KEY_MEDICATION]: results,

                           });
            }else{
             this.dataStore.setState(EMPTY_DATASTORE_STATE);
            }

            this.displaySearchResultsMedication();

}

/**
* Add the header to the page and load the MusicPlaylistClient.
*/
mount() {
           document.getElementById('add-diagnosis').addEventListener('click', this.addDiagnosis);
           document.getElementById('add-medication').addEventListener('click', this.addMedication);
           this.header.addHeaderToPage();
           this.client = new PatientRecordClient();
           this.clientLoaded();
}

/**
* Pulls search results from the datastore and displays them on the html page.
*/
displaySearchResults() {
                const searchCriteria = this.dataStore.get(SEARCH_CRITERIA_KEY);
                const searchResults = this.dataStore.get(SEARCH_RESULTS_KEY_DIAGNOSIS);

                const searchResultsDisplay = document.getElementById('diagnosis-details');

                if (searchCriteria === '') {
                    searchResultsDisplay.innerHTML = '';
                } else {
                  searchResultsDisplay.innerHTML = this.getHTMLForSearchResults(searchResults);
                }
}

//---------------------------------Diagnosis Details HTML------------------------
getHTMLForSearchResults(searchResults) {
                if (searchResults.length === 0) {
                    return '<h4>No results found</h4>';
                }

                let html = '<table><tr><th>Diagnosis Id</th><th>Healthcare Professional Id</th><th>Date Created</th><th>Description</th><th>Action</th></tr>';
                let i = 0;

                 for (const res of searchResults) {
                        const prefix = 'delete';
                        const suffix = res.diagnosisId;
                        const eleId = prefix+""+suffix;
                       // alert(eleId);
                        html += `<tr data-id="${res.diagnosisId}" >
                               <td>${res.diagnosisId}</td>
                               <td>${res.healthcareProfessionalId}</td>
                               <td class="action-column" >${res.dateCreated}</td>
                                <td class="vertical-cell">
                                  <div>${res.description}</div>
                                </td>
                               <td class="action-column" >
                                   <button onclick="viewPatientDetail.updateRow('${res.diagnosisId}')">Update</button>
                                   <button id="${eleId}" onclick="viewPatientDetail.deleteRow('${res.diagnosisId}', '${eleId}')">Delete</button>
                               </td>
                           </tr>`;


                   }

                   html += '</table>';
                   return html;
}


displaySearchResultsMedication() {
                const searchCriteria = this.dataStore.get(SEARCH_CRITERIA_KEY);
                const searchResults = this.dataStore.get(SEARCH_RESULTS_KEY_MEDICATION);

                const searchResultsDisplay = document.getElementById('medication-details');

                if (searchCriteria === '') {
                    searchResultsDisplay.innerHTML = '';
                } else {
                  searchResultsDisplay.innerHTML = this.getHTMLForSearchResultsMedication(searchResults);
                }
}


//-----------------------------------------------Medication details HTML-----------------------

getHTMLForSearchResultsMedication(searchResults) {
                if (searchResults.length === 0) {
                    return '<h4>No results found</h4>';
                }

                let html = '<table><tr><th>Medication Name</th><th>Dosage</th><th>Start Date </th><th>End Date</th><th>Instructions</th><th>Action</th></tr>';
                let i = 0;

                for (const res of searchResults) {

                     const prefix = 'delete';
                     const suffix = res.medicationId;
                     const eleId = prefix+""+suffix;

                    html += `
                    <tr data-id="${res.medicationId}">
                        <td>${res.medicationName}</td>
                        <td>${res.dosage}</td>
                        <td>${res.startDate}</td>
                        <td>${res.endDate}</td>
                        <td>${res.instructions}</td>
                        <td class = "action-column">
                            <button onclick="viewPatientDetail.updateMedicationRow('${res.medicationId}')">Update</button>
                            <button id="${eleId}" onclick="viewPatientDetail.deleteRowMedication('${res.medicationId}', '${eleId}')">Delete</button>
                        </td>

                    </tr>`;
                    i+=1;
                }
                html += '</table>';



                return html;
}

//------------------------- Create Diagnosis-------------------------------------------------

async addDiagnosis(evt) {
                evt.preventDefault();
                const errorMessageDisplay = document.getElementById('error-message');
                errorMessageDisplay.innerText = ``;
                errorMessageDisplay.classList.add('hidden');

                const addButtonDiagnosis = document.getElementById('add-diagnosis');
                const origButtonTextDiagnosis = addButtonDiagnosis.innerText;
                addButtonDiagnosis.innerText = 'Adding...';

                const urlParams = new URLSearchParams(window.location.search);
                const patientId = urlParams.get('id');
                const description = document.getElementById('description').value;


                const diagnosisResult = await this.client.addDiagnosis(patientId,description, (error) => {
                    addButton.innerText = origButtonText;
                    errorMessageDisplay.innerText = `Error: ${error.message}`;
                    errorMessageDisplay.classList.remove('hidden');
                });

                this.dataStore.set('diagnoses', diagnosisResult);
                addButtonDiagnosis.innerText = 'Add diagnosis'
                window.location.reload();

}

//-----------------------------Create Medication------------------------------------------

async addMedication(evt) {
            evt.preventDefault();
            const errorMessageDisplay = document.getElementById('error-message');
            errorMessageDisplay.innerText = ``;
            errorMessageDisplay.classList.add('hidden');

            const addButtonMedication = document.getElementById('add-medication');
            const origButtonTextMedication = addButtonMedication.innerText;
            addButtonMedication.innerText = 'Adding...';

            const urlParams = new URLSearchParams(window.location.search);
            const patientId = urlParams.get('id');
            const medicationName = document.getElementById('medicationName').value;
            const dosage = document.getElementById('dosage').value;
            const startDate = document.getElementById('startDate').value;
            const endDate = document.getElementById('endDate').value;
            const instruction = document.getElementById('instruction').value;


            const medicationResult = await this.client.addMedication(medicationName,dosage,startDate,endDate,instruction,patientId, (error) => {
                addButtonMedication.innerText = origButtonTextMedication;
                errorMessageDisplay.innerText = `Error: ${error.message}`;
                errorMessageDisplay.classList.remove('hidden');
            });

            this.dataStore.set('medications', medicationResult);
            addButtonMedication.innerText = 'Add Medication'
            window.location.reload();

}

//----------------------------Toggle edit Mode--------------------------------


updateRow(rowId) {
               const row = document.querySelector(`tr[data-id="${rowId}"]`);
               const isEditMode = row.classList.toggle('edit-mode');

               if (isEditMode) {
                   const cells = row.querySelectorAll('td:not(:last-child)');
                   cells.forEach(cell => {
                       const value = cell.innerText;
                       cell.innerHTML = `<input type="text" value="${value}">`;
                   });

                   const updateButton = row.querySelector('button');
                   updateButton.textContent = 'Save';
                   updateButton.onclick = () => {
                       this.saveRow(rowId); // Use arrow function to preserve 'this'
                   };
               } else {
                   // Restore original content
                   const cells = row.querySelectorAll('td:not(:last-child)');
                   cells.forEach(cell => {
                       const value = cell.querySelector('input').value;
                       cell.textContent = value;
                   });

                   const updateButton = row.querySelector('button');
                   updateButton.textContent = 'Update';
                   updateButton.onclick = () => {
                       this.updateRow(rowId); // Use arrow function to preserve 'this'
                   };
               }
}


updateMedicationRow(rowId) {
             

               const row = document.querySelector(`tr[data-id="${rowId}"]`);
               const isEditMode = row.classList.toggle('edit-mode');

               if (isEditMode) {

                   const cells = row.querySelectorAll('td:not(:last-child)');
                   cells.forEach(cell => {
                       const value = cell.innerText;
                       cell.innerHTML = `<input type="text" value="${value}">`;
                   });

                   const updateButton = row.querySelector('button');
                   updateButton.textContent = 'Save';
                   updateButton.onclick = () => {
                       this.saveRowMedication(rowId); // Use arrow function to preserve 'this'
                   };
               } else {
                   // Restore original content
                   const cells = row.querySelectorAll('td:not(:last-child)');
                   cells.forEach(cell => {
                       const value = cell.querySelector('input').value;
                       cell.textContent = value;
                   });

                   const updateButton = row.querySelector('button');
                   updateButton.textContent = 'Update';
                   updateButton.onclick = () => {
                       this.updateMedicationRow(rowId); // Use arrow function to preserve 'this'
                   };
               }

}


async saveRow(rowId){
            const row = document.querySelector(`tr[data-id="${rowId}"]`);
            const cells = row.querySelectorAll('td:not(:last-child) input');

            // Collect the updated values from the input fields
            const updatedValues = Array.from(cells).map(input => input.value.trim());


            //  logic to save the updated values
            const errorMessageDisplay = document.getElementById('error-message');
            errorMessageDisplay.innerText = '';
            errorMessageDisplay.classList.add('hidden');

            const urlParams = new URLSearchParams(window.location.search);
            const patientId = urlParams.get('id');
            const diagnosisId = rowId;
            const description = updatedValues[3];
            const dateCreated = updatedValues[2];

             const result = await this.client.updateDiagnosis(diagnosisId, patientId, dateCreated, description,(error) => {

                       errorMessageDisplay.innerText = `Error: ${error.message}`;
                       errorMessageDisplay.classList.remove('hidden');
                   });
             this.dataStore.set('updateDiagnosis', result);
             const results = await this.client.search(diagnosisId, (error)=>{
                                            errorMessageDisplay.innerText = `Error: ${error.message}`;
                                            errorMessageDisplay.classList.remove('hidden');
                                            } );


            this.dataStore.setState({
                [SEARCH_CRITERIA_KEY]: diagnosisId,
                [SEARCH_RESULTS_KEY_DIAGNOSIS]: results,
            });


              // Restore original content
             cells.forEach((cell, index) => {
                 cell.innerHTML = updatedValues[index];
             });



            // Update button text and event handler
             const updateButton = row.querySelector('button');
             updateButton.innerText = 'Update';
             updateButton.onclick = () => {
                 this.updateRow(rowId); // Use arrow function to maintain 'this'
             };


}
async saveRowMedication(rowId){

            const row = document.querySelector(`tr[data-id="${rowId}"]`);
            const cells = row.querySelectorAll('td:not(:last-child) input');

            // Collect the updated values from the input fields
            const updatedValues = Array.from(cells).map(input => input.value.trim());


            //  logic to save the updated values
            const errorMessageDisplay = document.getElementById('error-message');
            errorMessageDisplay.innerText = '';
            errorMessageDisplay.classList.add('hidden');

            const urlParams = new URLSearchParams(window.location.search);
            const patientId = urlParams.get('id');
            const medicationId = rowId;
            const medicationName = updatedValues[0];
            const dosage = updatedValues[1];
            const startDate = updatedValues[2]
            const endDated = updatedValues[3];
            const instructions = updatedValues[4];

             const result = await this.client.updateMedication(medicationId,medicationName, dosage,startDate,endDated,instructions,patientId,(error) => {

                       errorMessageDisplay.innerText = `Error: ${error.message}`;
                       errorMessageDisplay.classList.remove('hidden');
                   });
             this.dataStore.set('updateMedication', result);
             const results = await this.client.search(medicationId, (error)=>{
                                                         errorMessageDisplay.innerText = `Error: ${error.message}`;
                                                         errorMessageDisplay.classList.remove('hidden');
                                                         } );


             this.dataStore.setState({
                 [SEARCH_CRITERIA_KEY]: medicationId,
                 [SEARCH_RESULTS_KEY_MEDICATION]: results,
             });

              // Restore original content
             cells.forEach((cell, index) => {
                 cell.innerText = updatedValues[index];
             });

             // Update button text and event handler
             const updateButton = row.querySelector('button');
             updateButton.innerText = 'Update';
             updateButton.onclick = () => {
                 this.updateMedicationRow(rowId); // Use arrow function to maintain 'this'
             };



}


//-------------------------------------Delete Row functionality ------------------------------

/**
 * Delete a diagnosis or medication based on the provided ID.
 * @param {string} rowId - The ID of the diagnosis or medication to be deleted.
 */
async deleteRow(rowId,eleId) {
            const confirmation = confirm("Are you sure you want to delete this record?");
            if (!confirmation) {
            return;
            }

            const errorMessageDisplay = document.getElementById('error-message');
            errorMessageDisplay.innerText = '';
            errorMessageDisplay.classList.add('hidden');

            try {
                    // Determine whether it's a diagnosis or medication based on some condition
                    const isDiagnosis = true; // Set this condition based on your logic

                    const result=  await this.client.deleteDiagnosis(rowId, (error) => {
                        errorMessageDisplay.innerText = `Error: ${error.message}`;
                        errorMessageDisplay.classList.remove('hidden');
                    });

                        this.dataStore.set('deleteDiagnosis/{diagnosisId}', result);


                    // Remove the deleted row from the UI
                    const row = document.querySelector(`tr[data-id="${rowId}"]`);
                    if (row) {
                                const deleteButton = document.getElementById(eleId);
                                deleteButton.innerText = 'Deleting...';
                                await new Promise(resolve => setTimeout(resolve, 500));
                                row.remove();
                    }

            } catch (error) {
                    errorMessageDisplay.innerText = `Error: ${error.message}`;
                    errorMessageDisplay.classList.remove('hidden');
            }
}

async deleteRowMedication(rowId,eleId) {

            const confirmation = confirm("Are you sure you want to delete this record?");

            if (!confirmation) {
            return;
            }

            const errorMessageDisplay = document.getElementById('error-message');
            errorMessageDisplay.innerText = '';
            errorMessageDisplay.classList.add('hidden');

            try {
            // Determine whether it's a diagnosis or medication based on some condition
            const isDiagnosis = true; // Set this condition based on your logic

            if (isDiagnosis) {
                // Delete a diagnosis
                const result=  await this.client.deleteMedication(rowId, (error) => {
                    errorMessageDisplay.innerText = `Error: ${error.message}`;
                    errorMessageDisplay.classList.remove('hidden');
                });

                this.dataStore.set('deleteMedication/{medicationId}', result);
            } else {
                // Delete a medication
                await this.client.deleteMedication(rowId, (error) => {
                    errorMessageDisplay.innerText = `Error: ${error.message}`;
                    errorMessageDisplay.classList.remove('hidden');
                });
            }

            // Remove the deleted row from the UI
            const row = document.querySelector(`tr[data-id="${rowId}"]`);
            if (row) {
                        const deleteButton = document.getElementById(eleId);
                        deleteButton.innerText = 'Deleting...';
                        await new Promise(resolve => setTimeout(resolve, 500));
                        row.remove();
             }

            } catch (error) {
                errorMessageDisplay.innerText = `Error: ${error.message}`;
                errorMessageDisplay.classList.remove('hidden');
            }
}





}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
            const viewPatientDetail = new ViewPatientDetail();
            window.viewPatientDetail = viewPatientDetail;
            viewPatientDetail.mount();
};

window.addEventListener('DOMContentLoaded', main);
