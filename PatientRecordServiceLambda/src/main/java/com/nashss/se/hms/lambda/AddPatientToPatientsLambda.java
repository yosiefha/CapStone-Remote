package com.nashss.se.hms.lambda;
import com.nashss.se.hms.activity.requests.AddPatientToPatientsRequest;
import com.nashss.se.hms.activity.results.AddPatientToPatientsResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

/**
 * Represents a class for adding a new patient to the patients database.
 */

public class AddPatientToPatientsLambda extends LambdaActivityRunner<AddPatientToPatientsRequest,
        AddPatientToPatientsResult> implements RequestHandler<AuthenticatedLambdaRequest
        <AddPatientToPatientsRequest>, LambdaResponse> {

    /**
     * Handles the Lambda Function request.
     *
     * @param input   The Lambda Function input, which is an AuthenticatedLambdaRequest object.
     * @param context The Lambda execution environment context object.
     * @return A LambdaResponse object.
     */
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<AddPatientToPatientsRequest> input,
                                        Context context) {
        return super.runActivity(
            () -> {
                AddPatientToPatientsRequest unauthenticatedRequest =
                        input.fromBody(AddPatientToPatientsRequest.class);
                return input.fromUserClaims(claims ->
                        AddPatientToPatientsRequest.builder()
                                .withFirstName(unauthenticatedRequest.getFirstName())
                                .withLastName(unauthenticatedRequest.getLastName())
                                .withDOB(unauthenticatedRequest.getDOB())
                                .withContactNumber(unauthenticatedRequest.getContactNumber())
                                .withEmailAddress(unauthenticatedRequest.getEmailAddress())
                                .withAddress(unauthenticatedRequest.getAddress())
                                .build());
            },
            (request, serviceComponent) ->
                    serviceComponent.provideAddPatientToPatientsActivity().handleRequest(request)
        );
    }


}
