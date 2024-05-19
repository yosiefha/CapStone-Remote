
import PatientRecordClient from '../api/patientRecordClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";


const SEARCH_CRITERIA_KEY = 'firstName';
const SEARCH_CRITERIA_KEY1 = 'lastName';
const SEARCH_RESULTS_KEY = 'search-results';
const EMPTY_DATASTORE_STATE = {
    [SEARCH_CRITERIA_KEY]: '',
    [SEARCH_CRITERIA_KEY1]: '',
    [SEARCH_RESULTS_KEY]: [],
};
let searchPatient;

/**
 * Logic needed for the view playlist page of the website.
 */
class SearchPatient extends BindingClass {
    constructor() {
        super();

        this.bindClassMethods(['mount', 'search', 'displaySearchResults', 'getHTMLForSearchResults','updatePatientRow','deletePatientRow'], this);

        // Create a enw datastore with an initial "empty" state.
        this.dataStore = new DataStore(EMPTY_DATASTORE_STATE);
        this.header = new Header(this.dataStore);
        console.log("searchPatient constructor");


    }

    /**
     * Add the header to the page and load the MusicPlaylistClient.
     */
     mount() {
            // Wire up the form's 'submit' event and the button's 'click' event to the search method.
            document.getElementById('search-patient-form').addEventListener('submit', this.search);
            document.getElementById('search-btn').addEventListener('click', this.search);
            document.getElementById('new-patient-btn').addEventListener('click', () => {
                // Navigate to createPatient.html
                window.location.href = 'createPatient.html';
            });

            // Set up event delegation for the update buttons
            document.getElementById('search-results-display').addEventListener('click', (event) => {
                const target = event.target;
                if (target.tagName === 'BUTTON' && target.classList.contains('update-button')) {
                    const rowId = target.dataset.id;
                    this.updatePatientRow(event);
                } else if (target.tagName === 'BUTTON' && target.classList.contains('delete-button')) {
                    const rowId = target.dataset.id;
                    this.deletePatientRow(event);
                }
            });

            this.header.addHeaderToPage();
            this.client = new PatientRecordClient();
            this.checkUserLogin();



    }



    checkUserLogin() {

        // Check if the user is logged in
        this.client.getIdentity()
            .then(currentUser => {
                const searchButton = document.getElementById('search-btn');
                const newPatientButton = document.getElementById('new-patient-btn');

                if (currentUser) {
                    // User is logged in, enable search and new patient buttons

                    searchButton.removeAttribute('disabled');
                    newPatientButton.removeAttribute('disabled');

                } else {
                     alert("user not logged in")
                    // User is not logged in, disable search and new patient buttons
                    searchButton.setAttribute('disabled', 'disabled');
                    newPatientButton.setAttribute('disabled', 'disabled');
                }
            })
            .catch(error => {
                console.error('Error checking user login:', error);
            });

    }


    /**
     * Uses the client to perform the search,
     * then updates the datastore with the criteria and results.
     * @param evt The "event" object representing the user-initiated event that triggered this method.
     */
    async search(evt) {
             evt.preventDefault();

             const errorMessageDisplay = document.getElementById('error-message');
             errorMessageDisplay.innerText = "";
             errorMessageDisplay.classList.add('hidden');

            const searchCriteria = document.getElementById('firstName').value;
            const searchCriteria1 = document.getElementById('lastName').value;
            const previousSearchCriteria = this.dataStore.get(SEARCH_CRITERIA_KEY);
            const previousSearchCriteria1 = this.dataStore.get(SEARCH_CRITERIA_KEY1);

            if (previousSearchCriteria === searchCriteria && previousSearchCriteria1 === searchCriteria1) {
                return;
            }
            if (searchCriteria && searchCriteria1) {
                const results = await this.client.search(searchCriteria, searchCriteria1, (error)=>{
                errorMessageDisplay.innerText = `Error: ${error.message}`;
                errorMessageDisplay.classList.remove('hidden');
                } );


                this.dataStore.setState({
                    [SEARCH_CRITERIA_KEY]: searchCriteria,
                    [SEARCH_CRITERIA_KEY1]: searchCriteria1,
                    [SEARCH_RESULTS_KEY]: results,
                });
            } else {
                this.dataStore.setState(EMPTY_DATASTORE_STATE);
            }

            this.displaySearchResults();

    }

    /**
     * Pulls search results from the datastore and displays them on the html page.
     */
    displaySearchResults() {
            const searchCriteria = this.dataStore.get(SEARCH_CRITERIA_KEY);
            const searchCriteria1 = this.dataStore.get(SEARCH_CRITERIA_KEY1);
            const searchResults = this.dataStore.get(SEARCH_RESULTS_KEY);

            const searchResultsContainer = document.getElementById('search-results-container');
            const searchCriteriaDisplay = document.getElementById('search-criteria-display');
            const searchResultsDisplay = document.getElementById('search-results-display');

            if (searchCriteria === '' && searchCriteria1 === '') {
                searchResultsContainer.classList.add('hidden');
                searchCriteriaDisplay.innerHTML = '';
                searchResultsDisplay.innerHTML = '';
            } else {
                searchResultsContainer.classList.remove('hidden');
                searchCriteriaDisplay.innerHTML = `"${searchCriteria}  ${searchCriteria1}"`;

                searchResultsDisplay.innerHTML = this.getHTMLForSearchResults(searchResults);
            }
    }

    /**
     * Create appropriate HTML for displaying searchResults on the page.
     * @param searchResults An array of playlists objects to be displayed on the page.
     * @returns A string of HTML suitable for being dropped on the page.
     */
    getHTMLForSearchResults(searchResults) {
                    if (searchResults.length === 0) {
                        return '<h4>No results found</h4>';
                    }

                    let html = '<table><tr><th>PatientId</th><th>First Name</th><th>Last Name</th><th>Date of Birth</th><th>Contact Number</th><th>emailAddress</th><th>Address</th></tr>';
                    let i = 0;

                    for (const res of searchResults) {
                        const prefix = 'delete';
                        const suffix = res.patientId;
                        const eleId = prefix+""+suffix;

                        html += `
                        <tr data-id="${res.patientId}">
                            <td>
                                <a href="viewPatientHistory.html?id=${res.patientId}">${res.patientId}</a>
                            </td>
                            <td>${res.firstName}</td>
                            <td>${res.lastName}</td>
                            <td>${res.dob}</td>
                            <td>${res.contactNumber}</td>
                            <td>${res.emailAddress}</td>
                            <td>${res.address}</td>
                            <td>
                               <button class="update-button" data-id="${res.patientId}">Update</button>
                               <button id="${eleId}" onclick="searchPatient.deletePatientRow('${res.patientId}', '${eleId}')">Delete</button>

                            </td>
                        </tr>`;
                        i+=1;
                    }
                    html += '</table>';



                    return html;
    }


     updatePatientRow = (event) => {

         const target = event.target;

         // Check if the clicked element is a button with the 'update-button' class
         if (target.tagName === 'BUTTON' && target.classList.contains('update-button')) {
             const rowId = target.dataset.id;


             // Select the row using a more specific selector
             const row = document.querySelector(`tr[data-id="${rowId}"]`);

             // Check if the row is found before proceeding
             if (row) {
                 const isEditMode = row.classList.toggle('edit-mode');

                 if (isEditMode) {
                     const cells = row.querySelectorAll('td:not(:last-child)');
                     cells.forEach(cell => {
                         const value = cell.textContent;
                         cell.innerHTML = `<input type="text" value="${value}">`;
                     });

                     const updateButton = row.querySelector('button');
                     updateButton.textContent = 'Save';
                     updateButton.onclick = () => {
                         this.saveRow(rowId);
                     };
                 } else {
                     const cells = row.querySelectorAll('td:not(:last-child)');
                     cells.forEach(cell => {
                         const value = cell.querySelector('input').value;
                         cell.textContent = value;
                     });

                     const updateButton = row.querySelector('button');
                     updateButton.textContent = 'Update';
                     updateButton.onclick = () => {
                         this.updateRow(rowId);
                     };
                 }


             } else {
                 // Handle the case where the row is not found
                 alert(`Row with data-id "${rowId}" not found.`);
             }
         }
     }

    async saveRow(rowId){


                const row = document.querySelector(`tr[data-id="${rowId}"]`);
                const cells = row.querySelectorAll('td:not(:last-child) input');

               // Collect the updated values from the input fields
                const updatedValues = Array.from(cells).map(input => input.value);



                //  logic to save the updated values
                const errorMessageDisplay = document.getElementById('error-message');
                errorMessageDisplay.innerText = '';
                errorMessageDisplay.classList.add('hidden');


                const patientId = rowId;
                const firstName = updatedValues[1];
                const lastName = updatedValues[2];
                const dob = updatedValues[3];
                const contactNumber = updatedValues[4];
                const emailAddress = updatedValues[5];
                const address = updatedValues[6];



                try {
                       const result = await this.client.updatePatient(patientId, firstName, lastName, dob, contactNumber, emailAddress, address);
                       this.dataStore.set('patients', result);

                       const results = await this.client.search(firstName,lastName, (error)=>{
                                   errorMessageDisplay.innerText = `Error: ${error.message}`;
                                   errorMessageDisplay.classList.remove('hidden');
                                   } );


                       this.dataStore.setState({
                           [SEARCH_CRITERIA_KEY]: firstName,
                           [SEARCH_CRITERIA_KEY1]: lastName,
                           [SEARCH_RESULTS_KEY]: results,
                       });




                       //Restore original content
                       cells.forEach((cell, index) => {
                           cell.textContent = updatedValues[index];
                       });


                     // Update button text and event handler
                        const updateButton = row.querySelector('button.update-button');
                        updateButton.textContent = 'Update';
                        updateButton.onclick = (event) => this.updatePatientRow(this);

                    // Display the updated results
                     this.displaySearchResults();


                   } catch (error) {
                       errorMessageDisplay.innerText = `Error: ${error.message}`;
                       errorMessageDisplay.classList.remove('hidden');
                   }



    }
    async deletePatientRow(rowId,eleId) {


        // const target = event.target;
        // const rowId = target.dataset.id; // Get the rowId from the clicked button's dataset

        const confirmation = confirm("Are you sure you want to delete this record?");
        if (!confirmation) {
            return;
        }



        const errorMessageDisplay = document.getElementById('error-message');
        errorMessageDisplay.innerText = '';
        errorMessageDisplay.classList.add('hidden');

        try {

                const result = await this.client. deletePatient(rowId, (error) => {
                    errorMessageDisplay.innerText = `Error: ${error.message}`;
                    errorMessageDisplay.classList.remove('hidden');
                });

                this.dataStore.set('deletePatient/{rowId}', result);


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
     const searchPatient = new SearchPatient();
     window.searchPatient = searchPatient;
     searchPatient.mount();
     //searchPatient.checkUserLogin();

};

window.addEventListener('DOMContentLoaded', main);
