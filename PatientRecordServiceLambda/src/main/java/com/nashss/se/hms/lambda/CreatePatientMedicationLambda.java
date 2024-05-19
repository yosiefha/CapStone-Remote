package com.nashss.se.hms.lambda;
import com.nashss.se.hms.activity.requests.CreatePatientMedicationRequest;
import com.nashss.se.hms.activity.results.CreatePatientMedicationResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.UUID;


/**
 * The CreatePatientMedicationLambda class is an implementation of the RequestHandler
 * interface that handles requests to create a patient medication.
 * It extends the LambdaActivityRunner class, which is
 */
public class CreatePatientMedicationLambda extends LambdaActivityRunner<CreatePatientMedicationRequest,
        CreatePatientMedicationResult> implements RequestHandler<AuthenticatedLambdaRequest
        <CreatePatientMedicationRequest>, LambdaResponse> {


    /**
     * Handles the Lambda function request to create a patient medication.
     *
     * @param input   The Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return A LambdaResponse object representing the APIGateway response from the lambda function.
     */
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<CreatePatientMedicationRequest>
                                                    input, Context context) {
        return super.runActivity(
            () -> {
                CreatePatientMedicationRequest unauthenticatedRequest =
                        input.fromBody(CreatePatientMedicationRequest.class);
                return input.fromUserClaims(claims ->
                        CreatePatientMedicationRequest.builder()
                                .withMedicationId(UUID.randomUUID().toString())
                                .withMedicationName(unauthenticatedRequest.getMedicationName())
                                .withDosage(unauthenticatedRequest.getDosage())
                                .withStartDate(unauthenticatedRequest.getStartDate())
                                .withEndDate(unauthenticatedRequest.getEndDate())
                                .withInstructions(unauthenticatedRequest.getInstructions())
                                .withPatientId(unauthenticatedRequest.getPatientId())
                                .build());
            },
            (request, serviceComponent) ->
                    serviceComponent.provideCreatePatientMedicationActivity().handleRequest(request)
        );
    }
}
