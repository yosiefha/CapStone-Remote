package com.nashss.se.hms.lambda;
import com.nashss.se.hms.activity.requests.DeletePatientRequest;
import com.nashss.se.hms.activity.results.DeletePatientResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;



/**
 * The DeletePatientLambda class is a Lambda function that handles deleting a patient.
 * It extends the LambdaActivityRunner class and implements the RequestHandler interface.
 */
public class DeletePatientLambda extends LambdaActivityRunner<DeletePatientRequest,
        DeletePatientResult> implements RequestHandler<AuthenticatedLambdaRequest<DeletePatientRequest>,
        LambdaResponse> {
    /**
     * @param input   The Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return
     */
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<DeletePatientRequest> input, Context context) {
        return super.runActivity(
            () -> {
                DeletePatientRequest unauthenticatedRequest = input.fromPath(path -> DeletePatientRequest.builder()
                        .withPatientId(path.get("patientId"))
                        .build());
                return input.fromUserClaims(claims ->
                        DeletePatientRequest.builder()
                                .withPatientId(unauthenticatedRequest.getPatientId())
                                .build());
            },
            (request, serviceComponent) ->
                    serviceComponent.provideDeletePatientActivity().handleRequest(request)
        );

    }
}
