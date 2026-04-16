package com.company.automation.models;

import java.util.Map;

public class Customer {
    private final String firstName;
    private final String lastName;
    private final String zipCode;

    public Customer(String firstName, String lastName, String zipCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.zipCode = zipCode;
    }

    public static Customer fromMap(Map<String, String> data) {
        return new Customer(
                data.getOrDefault("firstName", ""),
                data.getOrDefault("lastName", ""),
                data.getOrDefault("zipCode", "")
        );
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getZipCode() { return zipCode; }
}
