package com.nashss.se.hms.lambda;
import com.nashss.se.hms.activity.requests.CreatePatientDiagnosisRequest;
import com.nashss.se.hms.activity.results.CreatePatientDiagnosisResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Represents a lambda function for creating a patient diagnosis.
 */
public class CreatePatientDiagnosisLambda extends LambdaActivityRunner<CreatePatientDiagnosisRequest,
        CreatePatientDiagnosisResult> implements RequestHandler<AuthenticatedLambdaRequest
        <CreatePatientDiagnosisRequest>, LambdaResponse> {


    /**
     * Handles the Lambda function request for creating a patient diagnosis.
     *
     * @param input   The Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return A LambdaResponse object representing the APIGateway response from the lambda function.
     */
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<CreatePatientDiagnosisRequest> input,
                                        Context context) {
        return super.runActivity(
            () -> {
                CreatePatientDiagnosisRequest unauthenticatedRequest =
                        input.fromBody(CreatePatientDiagnosisRequest.class);
                return input.fromUserClaims(claims ->
                        CreatePatientDiagnosisRequest.builder()
                                .withDiagnosisId(UUID.randomUUID().toString())
                                .withDateCreated(LocalDate.now().toString())
                                .withDescription(unauthenticatedRequest.getDescription())
                                .withPatientId(unauthenticatedRequest.getPatientId())
                                .withHealthcareProfessionalId(claims.get("email"))
                                .build());
            },
            (request, serviceComponent) ->
                    serviceComponent.provideCreatePatientDiagnosisActivity().handleRequest(request)
        );
    }
}
