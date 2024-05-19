package com.nashss.se.hms.lambda;
import com.nashss.se.hms.activity.requests.AddPatientToPatientsRequest;
import com.nashss.se.hms.activity.requests.SearchPatientByNameRequest;
import com.nashss.se.hms.activity.results.SearchPatientByNameResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;




/**
 * This class represents a Lambda function that handles searching patients by name.
 */
public class SearchPatientByNameLambda
        extends LambdaActivityRunner<SearchPatientByNameRequest, SearchPatientByNameResult>
        implements RequestHandler<AuthenticatedLambdaRequest<SearchPatientByNameRequest>, LambdaResponse> {


    /**
     * Handles the Lambda function request for searching patients by name.
     *
     * @param input   The Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return A LambdaResponse object representing the response of the Lambda function.
     */
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<SearchPatientByNameRequest> input, Context context) {


        return super.runActivity(
            () -> input.fromPath(path ->
                    SearchPatientByNameRequest.builder()
                            .withFirstName(path.get("firstName"))
                            .withLastName(path.get("lastName"))
                            .build()),
            (request, serviceComponent) ->
                    serviceComponent.provideSearchPatientByNameActivity().handleRequest(request)
        );
    }
}
