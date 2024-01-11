package com.expeditors.householddataprocessing;

import com.expeditors.householddataprocessing.processors.ConsoleBasedProcessor;
import com.expeditors.householddataprocessing.processors.FileBasedProcessor;
import com.expeditors.householddataprocessing.processors.HouseholdProcessor;
import com.expeditors.householddataprocessing.utils.PersonFilter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The driver class.
 */
public class HouseholdProcessorMain {

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    private static final int AGE_18 = 18;

    /**
     * The main method.
     *
     * @param args the arguments needed to run the program
     * @throws IOException if error occurs when reading from the input file or writing to the output file
     */
    public static void main(String[] args) throws IOException {
        if (args.length != 3 && args.length != 4) {
            System.err.println("Usage: java com.expeditors.householddataprocessing.HouseholdProcessorMain -inputFile "
                + "<input file name and path> -outputFile <output file name and path> or "
                + "java com.expeditors.householddataprocessing.HouseholdProcessorMain -inputFile <input file name and path> "
                + "-outputConsole");
            System.exit(1);
        }

        System.out.println(TIME_FORMATTER.format(LocalDateTime.now()) + " started to process" + System.lineSeparator());
        var startTime = System.currentTimeMillis();
        var inputFile = args[1];
        var personFilter = new PersonFilter(AGE_18);
        var householdProcessor = new HouseholdProcessor(personFilter);
        if (args.length == 3) {
            var consoleBasedProcessor = new ConsoleBasedProcessor(householdProcessor);
            consoleBasedProcessor.process(Files.readAllLines(new File(inputFile).toPath()));
        }
        if (args.length == 4) {
            var outputFile = args[3];
            var fileBasedProcessor = new FileBasedProcessor(householdProcessor);
            fileBasedProcessor.process(inputFile, outputFile);
        }
        var msTaken = System.currentTimeMillis() - startTime;
        System.out.println(
            String.format("%s%s finished processing, time taken %d milliseconds",
                System.lineSeparator(),
                TIME_FORMATTER.format(LocalDateTime.now()),
                msTaken));
    }
}
