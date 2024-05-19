package com.nashss.se.hms.dynamodb.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Class representing tests for the Diagnosis class.
 * Each test method is structured to independently create a Diagnosis instance, set it up with test data,
 * call the method to be tested, and assert the result.
 */
public class DiagnosisTest {

    /**
     * Test the getDescription method of the Diagnosis class.
     */
    @Test
    public void testGetDescription() {
        // Setup: Create a Diagnosis instance and set a test description.
        Diagnosis diagnosis = new Diagnosis();
        String testDescription = "Test diagnosis description";
        diagnosis.setDescription(testDescription);

        // Exercise: Call getDescription.
        String result = diagnosis.getDescription();

        // Verify: Check that the returned description matches our test description.
        Assertions.assertEquals(testDescription, result, "getDescription did not return the correct value.");
    }

    /**
     * Test the getDescription method of the Diagnosis class with a null description.
     */
    @Test
    public void testGetDescriptionNullValue() {
        // Setup: Create a Diagnosis instance without setting a description.
        Diagnosis diagnosis = new Diagnosis();

        // Exercise: Call getDescription.
        String result = diagnosis.getDescription();

        // Verify: Check that the returned description is null.
        Assertions.assertNull(result, "getDescription did not return null for a null description.");
    }
}