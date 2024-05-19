package com.nashss.se.hms.lambda;
import com.nashss.se.hms.activity.requests.DeleteMedicationRequest;
import com.nashss.se.hms.activity.results.DeleteMedicationResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;


/**
 * Represents a lambda function that handles the deletion of a medication.
 */
public class DeleteMedicationLambda extends LambdaActivityRunner<DeleteMedicationRequest,
        DeleteMedicationResult> implements RequestHandler<AuthenticatedLambdaRequest<DeleteMedicationRequest>,
        LambdaResponse> {


    /**
     * @param input   The Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return
     */
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<DeleteMedicationRequest> input, Context context) {
        return super.runActivity(
            () -> {
                DeleteMedicationRequest unauthenticatedRequest = input.fromPath(path ->
                        DeleteMedicationRequest.builder()
                        .withMedicationId(path.get("medicationId"))
                        .build());
                return input.fromUserClaims(claims ->
                        DeleteMedicationRequest.builder()
                                .withMedicationId(unauthenticatedRequest.getMedicationId())
                                .build());
            },
            (request, serviceComponent) ->
                    serviceComponent.provideDeleteMedicationActivity().handleRequest(request)
        );
    }
}
