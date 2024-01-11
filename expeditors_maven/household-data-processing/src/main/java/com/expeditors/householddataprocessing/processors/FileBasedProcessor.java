package com.expeditors.householddataprocessing.processors;

import com.expeditors.householddataprocessing.models.Person;

import java.io.*;
import java.util.*;

/**
 * The household processor which reads input from file and writes output to file.
 */
public class FileBasedProcessor {

    private final HouseholdProcessor householdProcessor;

    /**
     * Constructs a file based household processor.
     *
     * @param householdProcessor the household processor
     */
    public FileBasedProcessor(HouseholdProcessor householdProcessor) {
        this.householdProcessor = householdProcessor;
    }

    /**
     * Processes the input and writes the output to file.
     *
     * @param inputFile the input file
     * @param outputFile the output file
     */
    public void process(String inputFile, String outputFile) {
        var persons = this.generatePersonsFromFile(inputFile);
        if (persons.isEmpty()) {
            this.householdProcessor.printErrorAndThrowRuntimeException("Read empty data from input file.");
        }

        try (var writer = new BufferedWriter(new FileWriter(outputFile))) {
            var households = this.householdProcessor.aggregateHouseholdData(persons);
            for (var line : households) {
                writer.write(line + System.lineSeparator());
            }
        } catch (IOException e) {
            this.householdProcessor.printErrorAndThrowRuntimeException("Failed to write result to file: " + e.getMessage());
        }
    }

    private List<Person> generatePersonsFromFile(String inputFile) {
        List<Person> persons = new ArrayList<>();
        try (var reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                persons.add(this.householdProcessor.deserializePersonFromCsvString(line));
            }
        } catch (IOException e) {
            this.householdProcessor.printErrorAndThrowRuntimeException("Failed to read from file: " + e.getMessage());
        }
        return persons;
    }
}
