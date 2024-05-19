package com.nashss.se.hms.lambda;
import com.nashss.se.hms.activity.requests.UpdateMedicationRequest;
import com.nashss.se.hms.activity.results.UpdateMedicationResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;


/**
 * The UpdateMedicationLambda class is responsible for handling requests to update medication details.
 */
public class UpdateMedicationLambda extends LambdaActivityRunner<UpdateMedicationRequest, UpdateMedicationResult>
        implements RequestHandler<AuthenticatedLambdaRequest<UpdateMedicationRequest>, LambdaResponse> {
    /**
     * @param input   The Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return
     */
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<UpdateMedicationRequest> input, Context context) {
        return super.runActivity(
            () -> {
                UpdateMedicationRequest unauthenticatedRequest = input.fromBody(UpdateMedicationRequest.class);

                return input.fromUserClaims(claims ->
                        UpdateMedicationRequest.builder()
                                .withPatientId(unauthenticatedRequest.getPatientId())
                                .withMedicationId(unauthenticatedRequest.getMedicationId())
                                .withMedicationName(unauthenticatedRequest.getMedicationName())
                                .withStartDate(unauthenticatedRequest.getStartDate())
                                .withEndDated(unauthenticatedRequest.getEndDate())
                                .withDosage(unauthenticatedRequest.getDosage())
                                .withInstructions(unauthenticatedRequest.getInstructions())
                                .build());
            },
            (request, serviceComponent) ->
                    serviceComponent.provideUpdateMedicationActivity().handleRequest(request));
    }
}
