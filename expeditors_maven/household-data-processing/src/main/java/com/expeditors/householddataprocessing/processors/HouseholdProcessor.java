package com.expeditors.householddataprocessing.processors;

import com.expeditors.householddataprocessing.utils.CommonUtils;
import com.expeditors.householddataprocessing.utils.PersonFilter;
import com.expeditors.householddataprocessing.models.Person;
import com.expeditors.householddataprocessing.models.Person;
import com.expeditors.householddataprocessing.utils.CommonUtils;
import com.expeditors.householddataprocessing.utils.PersonFilter;

import java.util.*;
import java.util.stream.Collectors;

/**
 * The processor to process household data.
 */
public class HouseholdProcessor {

    private final int NUM_DATA_FIELDS = 6;

    private final int MIN_AGE = 0;

    private final int MAX_AGE = 200;

    private final PersonFilter personFilter;

    /**
     * Constructs a household processor object.
     *
     * @param personFilter the filter based on person's attributes
     */
    public HouseholdProcessor(PersonFilter personFilter) {
        this.personFilter = personFilter;
    }

    /**
     * Aggregates a list of persons based on certain criteria and returns a list of string.
     *
     * @param persons list of persons
     * @return output in the form of list of strings
     */
    public List<String> aggregateHouseholdData(List<Person> persons) {
        var res = new ArrayList<String>();
        var groupedByAddress = persons.stream().collect(Collectors.groupingBy(
                person -> person.getAddress().toLowerCase(), TreeMap::new, Collectors.toList()));
        for (var address : groupedByAddress.keySet()) {
            var households = groupedByAddress.get(address);
            var addrStr = String.join(",", CommonUtils.quote(address), CommonUtils.quote(households.size()));
            res.add(addrStr);
            var olderThan18 = households.stream().filter(this.personFilter).sorted(
                    Comparator.comparing(Person::getLastName).thenComparing(Person::getFirstName)).toList();
            for (var person : olderThan18) {
                res.add(person.toQuotedString());
            }
        }
        return res;
    }

    /**
     * Deserializes CSV string to {@link Person}.
     *
     * @param csvStr the CSV string
     * @return a person object
     */
    public Person deserializePersonFromCsvString(String csvStr) {
        var list = split(csvStr);
        if (list.size() != NUM_DATA_FIELDS) {
            printErrorAndThrowRuntimeException(
                String.format("Person data should have %d fields but it has %d fields, data string is %s",
                        NUM_DATA_FIELDS,
                    list.size(),
                    csvStr));
        }
        String firstName = null;
        String lastName = null;
        String streetAddress = null;
        String city = null;
        String state = null;
        int age = -1;
        for (int i = 0; i < list.size(); i++) {
            var str = list.get(i).trim();
            if (str.isEmpty()) {
                printErrorAndThrowRuntimeException("Person data can not be empty.");
            }
            switch (i) {
                case 0 -> firstName = str;
                case 1 -> lastName = str;
                case 2 -> streetAddress = str.replace(".", "").replace(",", "").replaceAll("\\s{2,}", " ");
                case 3 -> city = str;
                case 4 -> state = str;
                case 5 -> {
                    try {
                        age = Short.parseShort(str);
                    } catch (NumberFormatException e) {
                        printErrorAndThrowRuntimeException("Age in the input data is invalid");
                    }
                    if (age < MIN_AGE || age > MAX_AGE) {
                        printErrorAndThrowRuntimeException("Age in the input data is invalid.");
                    }
                }
            }
        }
        if (firstName == null || lastName == null || streetAddress == null || city == null || state == null || age == -1) {
            printErrorAndThrowRuntimeException("Person input data is invalid.");
        }
        return new Person(firstName, lastName, streetAddress, city, state, age);
    }

    /**
     * Print the error message and throws {@link RuntimeException}.
     *
     * @param errMsg the error message
     */
    public void printErrorAndThrowRuntimeException(String errMsg) {
        System.err.println(errMsg);
        throw new RuntimeException(errMsg);
    }

    private List<String> split(String str) {
        var res = new ArrayList<String>();
        boolean insideQuote = false;
        var builder = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (c == '"') {
                insideQuote = !insideQuote;
            } else if (c == ',' && !insideQuote) {
                res.add(builder.toString());
                builder.setLength(0);
            } else {
                builder.append(c);
            }
        }

        res.add(builder.toString());
        return res;
    }
}
