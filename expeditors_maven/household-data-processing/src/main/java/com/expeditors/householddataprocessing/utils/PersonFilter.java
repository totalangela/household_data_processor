package com.expeditors.householddataprocessing.utils;

import com.expeditors.householddataprocessing.models.Person;

import java.util.function.Predicate;

/**
 * The filter to filter based on attribute of {@link Person}.
 */
public class PersonFilter implements Predicate<Person> {

    private final int age;

    /**
     * Constructs a filter based on attributes of person.
     *
     * @param age the age of person
     */
    public PersonFilter(int age) {
        this.age = age;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean test(Person person) {
        return person.getAge() > this.age;
    }
}
