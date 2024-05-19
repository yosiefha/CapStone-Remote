package com.nashss.se.hms.lambda;
import com.nashss.se.hms.activity.requests.UpdateDiagnosisRequest;
import com.nashss.se.hms.activity.results.UpdateDiagnosisResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;



/**
 * Represents a class that handles updating a diagnosis lambda function.
 */
public class UpdateDiagnosisLambda extends LambdaActivityRunner<UpdateDiagnosisRequest, UpdateDiagnosisResult>
        implements RequestHandler<AuthenticatedLambdaRequest<UpdateDiagnosisRequest>, LambdaResponse> {
    /**
     * @param input   The Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return
     */
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<UpdateDiagnosisRequest> input, Context context) {
        return super.runActivity(
            () -> {
                UpdateDiagnosisRequest unauthenticatedRequest = input.fromBody(UpdateDiagnosisRequest.class);

                return input.fromUserClaims(claims ->
                        UpdateDiagnosisRequest.builder()
                                .withDiagnosisId(unauthenticatedRequest.getDiagnosisId())
                                .withPatientId(unauthenticatedRequest.getPatientId())
                                .withDateCreated(unauthenticatedRequest.getDateCreated())
                                .withDescription(unauthenticatedRequest.getDescription())
                                .withHealthcareProfessionalId(claims.get("email"))
                                .build());
            },
            (request, serviceComponent) ->
                    serviceComponent.provideUpdateDiagnosisActivity().handleRequest(request));
    }
}
