# Patient Record Service

## Overview

Welcome to the Patient Record Service, a comprehensive solution for managing patient records, diagnoses, and medications in a healthcare environment.
This project utilizes various AWS services to create a scalable and secure system, including AWS Cognito for user management, AWS Lambda for serverless functions,
AWS DynamoDB for database storage, and AWS CloudFront for content delivery.

## Getting Started

Before deploying the PatientRecord Service, ensure that you have:
   - An AWS account with the necessary permissions to create resources.
   - AWS CLI installed and configured.
   - SAM CLI (Serverless Application Model) installed.


## Deployment

1. Clone the repository:
   
   `git clone <repository_url>`
   
2.  Navigate to the project directory:

     `cd <project_directory>`

3.  Deploy the Lambda service (aka the backend):
      - Build the Java code: `sam build`
      - Deploy it: `sam deploy --s3-bucket __BUCKET__NAME__ --parameter-overrides S3Bucket=__BUCKET__NAME__ CognitoDomain=__COGNITO_DOMAIN_`
     
         **Take note of the "Outputs" produced by the deploy command. You will be using these soon.**
4.  Configure the frontend application:
     - CD into the web directory: `cd web`
     - Copy the `sample.env` file to `.env`: `cp sample.env .env`
     - Open the `.env` file in IntelliJ or Visual Studio Code and update the value for these environment variables using the data from the "Ouptuts" of the `sam deploy` in the previous section.
        - `API_BASE_URL`
        - `COGNITO_DOMAIN`
        - `COGNITO_USER_POOL_ID`
        - `COGNITO_USER_POOL_CLIENT_ID`
        - `COGNITO_REDIRECT_SIGNIN`
        - `COGNITO_REDIRECT_SIGNOUT`

   > **NOTE:** The two _redirect_ URLs should probably be set to the URL of your CloudFront distribution - this information should be included in the output of the `sam deploy` command - but
   >  you may wish to have different URLs for each. The _signin_ URL is where a user will be redirected after logging in and the _signout_ URL is where a user will be redirected after logging out.

5. Build and deploy your frontend code
    - CD into the web directory: `cd web`
    - `npm run build`

      This will perform a _production build_ of your frontend application.

    - Copy the build artifacts to the S3 bucket from which your CloudFront distribution is configured to pull.

      ```shell
      aws s3 cp \
        build \
        s3://__BUCKET_NAME__/static/ \
        --recursive
      ```
6. Checkout your application.
    - Visit the CloudFront link displayed in the output of `sam deploy` to see your application in action.

# Project Structure

## Cognito Configuration

 The project uses AWS Cognito for user management. The CloudFormation template includes the configuration for a user pool, user pool client, and a custom domain.
 - UserPool: Defines the AWS Cognito User Pool with necessary attributes.
 - UserPoolClient: Configures the user pool client, including OAuth flows and allowed origins.
 - UserPoolDomain: Sets up a custom domain for the Cognito user pool.

# CloudFront Configuration

CloudFront is employed for content delivery, serving as a CDN to improve website performance.
- CloudFrontOriginAccessIdentity: Configures CloudFront origin access identity.
- CloudfrontDistribution: Defines the CloudFront distribution with S3 as the origin.

## Role/Permissions/Policy Configuration

IAM roles, policies, and permissions are specified for AWS Lambda functions to interact with other services securely.
- AccessRole: IAM role for Lambda functions with policies for CloudWatch logs and DynamoDB access.

## Lambda Functions Configuration

AWS Lambda functions handle various operations related to patient records, diagnoses, and medications.

- Functions include adding, searching, updating, and deleting patient records, diagnoses, and medications.

## DynamoDB Configuration

DynamoDB tables are used for storing patient records, diagnoses, and medications.
- PatientTable: DynamoDB table for patient records with a global secondary index for searching by name.
- DiagnosisTable: DynamoDB table for diagnoses with a global secondary index for searching by patient ID.
- MedicationTable: DynamoDB table for medications with a global secondary index for searching by patient ID.

## Outputs
After a successful deployment, the CloudFormation stack provides essential outputs:

- CognitoUserPoolId: The Cognito User Pool ID.
- CognitoUserPoolClientId: The Cognito User Pool Client ID.
- CognitoDomain: The Cognito domain for authentication.
- ApiBaseUrl: The API Gateway endpoint base URL for the Prod stage.

## Accessing the Application
 1. Access the deployed front-end application at the provided CloudFront URL.
 2. Use the Cognito user pool ID, user pool client ID, and domain for user authentication.
 3. Explore the API through the generated API Gateway endpoint.

## Conclusion

The Patient Record Service provides a foundation for managing healthcare data securely and efficiently.
Feel free to customize and extend the project based on your specific requirements. 
For more details on the functionalities and APIs, refer to the Lambda function code and API Gateway configurations. 
If you encounter any issues during deployment or have questions, refer to the AWS documentation or community forums for assistance.





    
