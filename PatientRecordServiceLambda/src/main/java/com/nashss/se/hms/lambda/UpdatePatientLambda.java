package com.nashss.se.hms.lambda;
import com.nashss.se.hms.activity.requests.UpdatePatientRequest;
import com.nashss.se.hms.activity.results.UpdatePatientResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;


public class UpdatePatientLambda extends LambdaActivityRunner<UpdatePatientRequest, UpdatePatientResult>
        implements RequestHandler<AuthenticatedLambdaRequest<UpdatePatientRequest>, LambdaResponse> {

    /**
     * @param input   The Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return
     */
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<UpdatePatientRequest> input, Context context) {
        return super.runActivity(
            () -> {
                UpdatePatientRequest unauthenticatedRequest = input.fromBody(UpdatePatientRequest.class);


                return input.fromUserClaims(claims ->
                        UpdatePatientRequest.builder()
                                .withPatientId(unauthenticatedRequest.getPatientId())
                                .withFirstName(unauthenticatedRequest.getFirstName())
                                .withLastName(unauthenticatedRequest.getLastName())
                                .withDOB(unauthenticatedRequest.getDOB())
                                .withContactNumber(unauthenticatedRequest.getContactNumber())
                                .withEmailAddress(unauthenticatedRequest.getEmailAddress())
                                .withAddress(unauthenticatedRequest.getAddress())
                                .build());
            },
            (request, serviceComponent) -> serviceComponent.provideUpdatePatientActivity().handleRequest(request));
    }
}
