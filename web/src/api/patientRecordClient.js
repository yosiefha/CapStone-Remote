
import axios from "axios";
import BindingClass from "../util/bindingClass";
import Authenticator from "./authenticator";

/**
 * Client to call Momentum.
 *
 * This could be a great place to explore Mixins. Currently the client is being loaded multiple times on each page,
 * which we could avoid using inheritance or Mixins.
 * https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Classes#Mix-ins
 * https://javascript.info/mixins
  */

export default class PatientRecordClient extends BindingClass {

        constructor(props = {}) {
        super();

        const methodsToBind = ['clientLoaded', 'getIdentity', 'login', 'logout', 'addPatient','search','getPatient','addDiagnosis','addMedication'];
        this.bindClassMethods(methodsToBind, this);

        this.authenticator = new Authenticator();;
        this.props = props;

        axios.defaults.baseURL = process.env.API_BASE_URL;
        this.axiosClient = axios;
        this.clientLoaded();
        }
/**
 * Run any functions that are supposed to be called once the client has loaded successfully.
 */
        clientLoaded() {
            if (this.props.hasOwnProperty("onReady")) {
                this.props.onReady(this);
            }
        }
        async getIdentity(errorCallback) {
           try {
               const isLoggedIn = await this.authenticator.isUserLoggedIn();

               if (!isLoggedIn) {
                    return undefined;
               }

                return await this.authenticator.getCurrentUserInfo();
           } catch (error) {
               this.handleError(error)
           }
        }

        async login() {
        this.authenticator.login();
        }

        async logout() {
        this.authenticator.logout();
        }

        async getTokenOrThrow(unauthenticatedErrorMessage) {
        const isLoggedIn = await this.authenticator.isUserLoggedIn();
        if (!isLoggedIn) {
         throw new Error(unauthenticatedErrorMessage);
        }

        return await this.authenticator.getUserToken();
        }

        /**
        * Create a new playlist owned by the current user.
        * @param name The name of the playlist to create.
        * @param tags Metadata tags to associate with a playlist.
        * @param errorCallback (Optional) A function to execute if the call fails.
        * @returns The playlist that has been created.
        */
        async addPatient(firstName, lastName,contactNumber,dob,emailAddress,address, errorCallback) {
        try {
           const token = await this.getTokenOrThrow("Only authenticated users can create playlists.");
           const response = await this.axiosClient.post(`patients`, {
               firstName: firstName,
               lastName: lastName,
               contactNumber: contactNumber,
               dob: dob,
               emailAddress: emailAddress,
               address: address

           }, {
               headers: {
                   Authorization: `Bearer ${token}`
               }
           });
           return response.data.patient;
        } catch (error) {
           this.handleError(error, errorCallback)
        }
        }

        async addDiagnosis(patientId, description, errorCallback) {
          try {
              const token = await this.getTokenOrThrow("Only authenticated users can create diagnosis.");
              const response = await this.axiosClient.post(`diagnoses`, {
                  patientId: patientId,
                  description: description,
              }
              , {
                  headers: {
                      Authorization: `Bearer ${token}`
                  }
              }
              );
              return response.data.diagnosisModel;
          } catch (error) {
              this.handleError(error, errorCallback)
          }
        }

        async updatePatient(patientId,firstName,lastName, dob,contactNumber,emailAddress,address, errorCallback) {
                     try {
                         const token = await this.getTokenOrThrow("Only authenticated users can create diagnosis.");
                         const response = await this.axiosClient.put(`patients/{patientId}`, {

                             patientId : patientId,
                             firstName : firstName,
                             lastName : lastName,
                             dob : dob,
                             contactNumber : contactNumber,
                             emailAddress : emailAddress,
                             address : address,
                         }
                         , {
                             headers: {
                                 Authorization: `Bearer ${token}`
                             }
                         }
                         );
                         return response.data.patientModel;
                     } catch (error) {
                         this.handleError(error, errorCallback)
                     }
                }

        async updateDiagnosis(diagnosisId,patientId, dateCreated,description, errorCallback) {
             try {
                 const token = await this.getTokenOrThrow("Only authenticated users can create diagnosis.");
                 const response = await this.axiosClient.put(`diagnoses`, {

                     diagnosisId: diagnosisId,
                     dateCreated: dateCreated,
                     patientId: patientId,
                     description: description,
                 }
                 , {
                     headers: {
                         Authorization: `Bearer ${token}`
                     }
                 }
                 );
                 return response.data.diagnosisModel;
             } catch (error) {
                 this.handleError(error, errorCallback)
             }
        }

        async updateMedication(medicationId,medicationName, dosage,startDate,endDate,instructions,patientId, errorCallback) {
                try {
                    const token = await this.getTokenOrThrow("Only authenticated users can create diagnosis.");
                    const response = await this.axiosClient.put(`medications`, {
                        medicationId : medicationId,
                        medicationName : medicationName,
                        dosage : dosage,
                        startDate : startDate,
                        endDated : endDate,
                        instructions : instructions,
                        patientId: patientId,

                    }
                    , {
                        headers: {
                            Authorization: `Bearer ${token}`
                        }
                    }
                    );
                    return response.data.medicationModel;
                } catch (error) {
                    this.handleError(error, errorCallback)
                }
         }

        async deletePatient(patientId,  errorCallback) {
                 try {
                     const token = await this.getTokenOrThrow("Only authenticated users can delete Patient.");
                     const response = await this.axiosClient.delete(`patients/${patientId}`, {
                         headers: {
                            Authorization: `Bearer ${token}`
                     }}
                     );
                     return response.data.patientModel;
                 } catch (error) {
                     this.handleError(error, errorCallback)
                 }
               }
        async deleteDiagnosis(diagnosisId,  errorCallback) {
          try {
              const token = await this.getTokenOrThrow("Only authenticated users can delete Diagnosis.");
              const response = await this.axiosClient.delete(`diagnoses/${diagnosisId}`, {
                  headers: {
                     Authorization: `Bearer ${token}`
              }}
              );
              return response.data.diagnosisModel;
          } catch (error) {
              this.handleError(error, errorCallback)
          }
        }

        async deleteMedication(medicationId,  errorCallback) {
                try {
                    const token = await this.getTokenOrThrow("Only authenticated users can delete Diagnosis.");
                    const response = await this.axiosClient.delete(`medications/${medicationId}`, {
                        headers: {
                           Authorization: `Bearer ${token}`
                    }}
                    );
                    return response.data.medicationModel;
                } catch (error) {
                    this.handleError(error, errorCallback)
                }
            }



        async addMedication(medicationName,dosage,startDate,endDated,instructions,patientId, errorCallback) {
             try {
                 const token = await this.getTokenOrThrow("Only authenticated users can create playlists.");
                 const response = await this.axiosClient.post(`medications`, {
                     medicationName: medicationName,
                     dosage: dosage,
                     startDate: startDate,
                     endDate: endDated,
                     instructions: instructions,
                     patientId: patientId,

                 }
                 , {
                     headers: {
                         Authorization: `Bearer ${token}`
                     }
                 }
                 );
                 return response.data.medicationModel;
             } catch (error) {
                 this.handleError(error, errorCallback)
             }
        }

        async search(firstName, lastName,errorCallback) {
           try {
               const token = await this.getTokenOrThrow("Only authenticated users can delete Patient.");
              const response = await this.axiosClient.get(`patients/search/${firstName}/${lastName}`,{
                  headers: {
                      Authorization: `Bearer ${token}`
                  }});
              console.log(response);

               return response.data.patientModelList;
           } catch (error) {
               this.handleError(error, errorCallback);
           }

        }


        async getPatient(patientId,errorCallback) {
              try {
                  const token = await this.getTokenOrThrow("Only authenticated users can delete patient profile.");
                 const response = await this.axiosClient.get(`patients/${patientId}`,{
                     headers: {
                         Authorization: `Bearer ${token}`
                     }});
                 console.log(response);

                  return response.data.patientModel;
              } catch (error) {
                  this.handleError(error, errorCallback);
              }

          }

        async getPatientDiagnosis(patientId,errorCallback) {
                  try {
                      const token = await this.getTokenOrThrow("Only authenticated users can get Diagnosis.");
                     const response = await this.axiosClient.get(`/patients/${patientId}/diagnoses`,{
                         headers: {
                             Authorization: `Bearer ${token}`
                         }});
                     console.log(response);

                      return response.data.diagnosisModelList;
                  } catch (error) {
                      this.handleError(error, errorCallback);
                  }

              }
        async getPatientMedication(patientId,errorCallback) {
                       try {
                           const token = await this.getTokenOrThrow("Only authenticated users can get Patient Medication.");
                          const response = await this.axiosClient.get(`/patients/${patientId}/medications`,{
                              headers: {
                                  Authorization: `Bearer ${token}`
                              }});
                          console.log(response);

                           return response.data.medicationModelList;
                       } catch (error) {
                           this.handleError(error, errorCallback);
                       }

                   }

}
