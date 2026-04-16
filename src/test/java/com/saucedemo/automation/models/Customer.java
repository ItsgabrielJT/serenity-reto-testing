package com.saucedemo.automation.models;

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
        String fName = data.get("firstName");
        String lName = data.get("lastName");
        String zCode = data.get("zipCode");

        return new Customer(
                fName != null ? fName : "",
                lName != null ? lName : "",
                zCode != null ? zCode : ""
        );
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getZipCode() { return zipCode; }
}
