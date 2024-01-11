package com.expeditors.householddataprocessing;

import com.expeditors.householddataprocessing.processors.ConsoleBasedProcessor;
import com.expeditors.householddataprocessing.processors.HouseholdProcessor;
import com.expeditors.householddataprocessing.utils.PersonFilter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * The unit test of HouseholdProcessor.
 */
public class HouseholdProcessorUnitTest {
    private PersonFilter personFilter;
    private HouseholdProcessor householdProcessor;
    private ConsoleBasedProcessor consoleBasedProcessor;

    /**
     * Initializes the instance variables.
     */
    @Before
    public void setUp() {
        personFilter = new PersonFilter(18);
        householdProcessor = new HouseholdProcessor(personFilter);
        consoleBasedProcessor = new ConsoleBasedProcessor(householdProcessor);
    }

    /**
     * Tests the process method.
     */
    @Test
    public void testProcess() {
        var inputs = List.of(
            "\"Dave\",\"Smith\",\"123 main st.\",\"seattle\",\"wa\",\"43\"",
            "\"Alice\",\"Smith\",\"123 Main St.\",\"Seattle\",\"WA\",\"45\"",
            "\"Bob\",\"Williams\",\"234 2nd Ave.\",\"Tacoma\",\"WA\",\"26\"",
            "\"Carol\",\"Johnson\",\"234 2nd Ave\",\"Seattle\",\"WA\",\"67\"",
            "\"Eve\",\"Smith\",\"234 2nd Ave.\",\"Tacoma\",\"WA\",\"25\"",
            "\"Frank\",\"Jones\",\"234 2nd Ave.\",\"Tacoma\",\"FL\",\"23\"",
            "\"George\",\"Brown\",\"345 3rd Blvd., Apt. 200\",\"Seattle\",\"WA\",\"18\"",
            "\"Helen\",\"Brown\",\"345 3rd Blvd. Apt. 200\",\"Seattle\",\"WA\",\"18\"",
            "\"Ian\",\"Smith\",\"123 main st \",\"Seattle\",\"Wa\",\"18\"",
            "\"Jane\",\"Smith\",\"123 Main St.\",\"Seattle\",\"WA\",\"13\"");
        var expected = List.of(
            "\"123 main st,seattle,wa\",\"4\"",
            "\"Alice\",\"Smith\",\"123 Main St,Seattle,WA\",\"45\"",
            "\"Dave\",\"Smith\",\"123 main st,seattle,wa\",\"43\"",
            "\"234 2nd ave,seattle,wa\",\"1\"",
            "\"Carol\",\"Johnson\",\"234 2nd Ave,Seattle,WA\",\"67\"",
            "\"234 2nd ave,tacoma,fl\",\"1\"",
            "\"Frank\",\"Jones\",\"234 2nd Ave,Tacoma,FL\",\"23\"",
            "\"234 2nd ave,tacoma,wa\",\"2\"",
            "\"Eve\",\"Smith\",\"234 2nd Ave,Tacoma,WA\",\"25\"",
            "\"Bob\",\"Williams\",\"234 2nd Ave,Tacoma,WA\",\"26\"",
            "\"345 3rd blvd apt 200,seattle,wa\",\"2\""
        );

        var persons = consoleBasedProcessor.generatePersonsFromInputs(inputs);
        var outputs = householdProcessor.aggregateHouseholdData(persons);

        Assert.assertEquals(expected, outputs);
    }

    /**
     * Tests the process method which throws exception.
     */
    @Test
    public void testProcessThrowException() {
        var inputs = List.of(
                "\"Dave\",\"Smith\",\"123 main st.\",\"seattle\",\"wa\",\"invalid age\"");
        try {
            consoleBasedProcessor.generatePersonsFromInputs(inputs);
        } catch (RuntimeException e) {
            Assert.assertEquals("Age in the input data is invalid", e.getMessage());
        }
    }
}
