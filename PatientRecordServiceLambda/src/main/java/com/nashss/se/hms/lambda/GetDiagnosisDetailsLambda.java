package com.nashss.se.hms.lambda;
import com.nashss.se.hms.activity.requests.GetDiagnosisDetailsRequest;
import com.nashss.se.hms.activity.results.GetDiagnosisDetailsResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;


/**
 * The GetDiagnosisDetailsLambda class is a Lambda Function that handles the request to get diagnosis details
 * for a patient. It implements the RequestHandler interface and extends the LambdaActivityRunner class.
 */
public class GetDiagnosisDetailsLambda extends LambdaActivityRunner<GetDiagnosisDetailsRequest,
        GetDiagnosisDetailsResult> implements RequestHandler<AuthenticatedLambdaRequest<GetDiagnosisDetailsRequest>,
        LambdaResponse> {


    /**
     * @param input   The Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return
     */
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetDiagnosisDetailsRequest> input, Context context) {
        return super.runActivity(
            () -> input.fromPath(path ->
                    GetDiagnosisDetailsRequest.builder()
                            .withPatientId(path.get("patientId"))
                            .build()),
            (request, serviceComponent) ->
                    serviceComponent.provideGetDiagnosisDetailsActivity().handleRequest(request)
        );
    }
}
