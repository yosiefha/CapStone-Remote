package com.nashss.se.hms.lambda;
import com.nashss.se.hms.activity.requests.GetPatientByPatientIdRequest;
import com.nashss.se.hms.activity.results.GetPatientByPatientIdResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;



/**
 * Represents a Lambda function that gets a patient by their ID.
 */
public class GetPatientByPatientIdLambda extends LambdaActivityRunner<GetPatientByPatientIdRequest,
        GetPatientByPatientIdResult> implements RequestHandler<AuthenticatedLambdaRequest<GetPatientByPatientIdRequest>,
        LambdaResponse> {


    /**
     * @param input   The Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return
     */
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetPatientByPatientIdRequest> input, Context context) {
        return super.runActivity(
            () -> input.fromPath(path ->
                    GetPatientByPatientIdRequest.builder()
                            .withPatientId(path.get("patientId"))
                            .build()),
            (request, serviceComponent) ->
                    serviceComponent.provideGetPatientByPatientIdActivity().handleRequest(request)
        );
    }
}
