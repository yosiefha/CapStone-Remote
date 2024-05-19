package com.nashss.se.hms.lambda;
import com.nashss.se.hms.activity.requests.GetMedicationDetailsRequest;
import com.nashss.se.hms.activity.results.GetMedicationDetailsResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;


/**
 * Represents a Lambda function for retrieving medication details for a patient.
 */
public class GetMedicationDetailsLambda extends LambdaActivityRunner<GetMedicationDetailsRequest,
        GetMedicationDetailsResult> implements RequestHandler<AuthenticatedLambdaRequest<GetMedicationDetailsRequest>,
        LambdaResponse> {
    /**
     * @param input   The Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return
     */
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetMedicationDetailsRequest> input, Context context) {
        return super.runActivity(
            () -> input.fromPath(path ->
                    GetMedicationDetailsRequest.builder()
                            .withPatientId(path.get("patientId"))
                            .build()),
            (request, serviceComponent) ->
                    serviceComponent.provideGetMedicationDetailsActivity().handleRequest(request));
    }


}
