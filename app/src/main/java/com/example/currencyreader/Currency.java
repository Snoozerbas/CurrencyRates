package com.example.currencyreader;

public class Currency {

    private String code;  // Currency code (e.g. "USD", "EUR")
    private double rate;  // Exchange rate for the currency

    // Constructor to initialize the Currency object
    public Currency(String code, double rate) {
        this.code = code;
        this.rate = rate;
    }

    // Getter method for currency code
    public String getCode() {
        return code;
    }

    // Setter method for currency code
    public void setCode(String code) {
        this.code = code;
    }

    // Getter method for exchange rate
    public double getRate() {
        return rate;
    }

    // Setter method for exchange rate
    public void setRate(double rate) {
        this.rate = rate;
    }
}
