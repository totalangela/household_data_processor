package com.expeditors.householddataprocessing.models;

import com.expeditors.householddataprocessing.utils.CommonUtils;

/**
 * The data model class to represent a person in the household.
 */
public class Person {
    private String firstName;
    private String lastName;
    private String streetAddress;
    private String city;
    private String state;
    private int age;

    /**
     * Constructs a household person.
     *
     * @param firstName the first name
     * @param lastName the last name
     * @param streetAddress the street address
     * @param city the city
     * @param state the state
     * @param age the age
     */
    public Person(String firstName, String lastName, String streetAddress, String city, String state, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.age = age;
    }

    /**
     * Gets the first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name.
     *
     * @param firstName the first name to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name.
     *
     * @param lastName the last name to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the street address.
     *
     * @return the street address
     */
    public String getStreetAddress() {
        return streetAddress;
    }

    /**
     * Sets the street address.
     *
     * @param streetAddress the street address to set
     */
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    /**
     * Gets the city.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the city.
     *
     * @param city the city to set.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets the state.
     *
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the state.
     *
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Gets the age.
     *
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age.
     *
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Gets the full address which includes street address, city and state and is delimited with comma.
     *
     * @return the full address
     */
    public String getAddress() {
        return String.join(",", this.streetAddress, this.city, this.state);
    }

    /**
     * Returns the string representation of the object with each field quoted in double quotes and delimited by comma.
     *
     * @return the string representation of the object
     */
    public String toQuotedString() {
        return String.join(
            ",",
            CommonUtils.quote(this.getFirstName()),
            CommonUtils.quote(this.getLastName()),
            CommonUtils.quote(this.getAddress()),
            CommonUtils.quote(this.getAge()));
    }
}
