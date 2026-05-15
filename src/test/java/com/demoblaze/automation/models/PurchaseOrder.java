package com.demoblaze.automation.models;

import java.util.Map;

public class PurchaseOrder {
    private final String name;
    private final String country;
    private final String city;
    private final String creditCard;
    private final String month;
    private final String year;

    public PurchaseOrder(String name, String country, String city,
                         String creditCard, String month, String year) {
        this.name = name;
        this.country = country;
        this.city = city;
        this.creditCard = creditCard;
        this.month = month;
        this.year = year;
    }

    public static PurchaseOrder fromMap(Map<String, String> data) {
        return new PurchaseOrder(
            data.getOrDefault("name", ""),
            data.getOrDefault("country", ""),
            data.getOrDefault("city", ""),
            data.getOrDefault("creditCard", ""),
            data.getOrDefault("month", ""),
            data.getOrDefault("year", "")
        );
    }

    public String getName()       { return name; }
    public String getCountry()    { return country; }
    public String getCity()       { return city; }
    public String getCreditCard() { return creditCard; }
    public String getMonth()      { return month; }
    public String getYear()       { return year; }
}
