package com.expeditors.householddataprocessing.processors;

import com.expeditors.householddataprocessing.models.Person;

import java.util.*;

/**
 * The household processor which prints the output to console.
 */
public class ConsoleBasedProcessor {

    private final HouseholdProcessor householdProcessor;

    /**
     * Constructs a console based household processor.
     *
     * @param householdProcessor the household processor
     */
    public ConsoleBasedProcessor(HouseholdProcessor householdProcessor) {
        this.householdProcessor = householdProcessor;
    }

    /**
     * Processes the string inputs and print the output to console.
     *
     * @param inputs the list of input strings.
     */
    public void process(List<String> inputs) {
        var persons = this.generatePersonsFromInputs(inputs);
        var outputs = this.householdProcessor.aggregateHouseholdData(persons);
        for (var output : outputs) {
            System.out.println(output);
        }
    }

    /**
     * Generates a list of persons from a list of strings.
     *
     * @param inputs the list of input strings.
     */
    public List<Person> generatePersonsFromInputs(List<String> inputs) {
        List<Person> persons = new ArrayList<>();
        for (var line : inputs) {
            persons.add(this.householdProcessor.deserializePersonFromCsvString(line));
        }
        return persons;
    }
}
