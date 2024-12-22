package com.example.currencyreader;

import android.util.Log;

import org.json.JSONObject;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class DataLoader {

    private static final String API_KEY = "e68200615c826c6bf57c0f43";  // Replace with your actual API key
    private static final String BASE_CURRENCY = "USD"; // Set the base currency (for example, USD)
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/" + BASE_CURRENCY;
    private CurrencyAdapter currencyAdapter;

    public DataLoader(CurrencyAdapter adapter) {
        this.currencyAdapter = adapter;
    }

    public ArrayList<Currency> loadData() {
        ArrayList<Currency> currencies = new ArrayList<>();

        try {
            // Open connection to the API
            URL url = new URL(API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            // Read the response
            InputStreamReader reader = new InputStreamReader(connection.getInputStream());
            StringBuilder response = new StringBuilder();
            int data = reader.read();
            while (data != -1) {
                response.append((char) data);
                data = reader.read();
            }

            // Parse the JSON response
            JSONObject jsonResponse = new JSONObject(response.toString());
            JSONObject rates = jsonResponse.getJSONObject("conversion_rates");

            // Example: Extract data for USD, EUR, GBP, etc.
            currencies.add(new Currency("USD", 1));  // Base currency nusisausiu, bebesuprantu kas veikia kas ne, nemeginsiu padaryti, kad dinamiskai eitu pakeisti base currency
            currencies.add(new Currency("EUR", rates.getDouble("EUR")));
            currencies.add(new Currency("GBP", rates.getDouble("GBP")));
            currencies.add(new Currency("AUD", rates.getDouble("AUD")));
            currencies.add(new Currency("JPY", rates.getDouble("JPY")));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return currencies;
    }
}
