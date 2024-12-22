package com.example.currencyreader;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private CurrencyAdapter currencyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.currencyListView);

        // Initialize the CurrencyAdapter with an empty list
        currencyAdapter = new CurrencyAdapter(this, new ArrayList<>());
        listView.setAdapter(currencyAdapter);

        // Create an ExecutorService to run background tasks
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                // Perform background task to fetch data
                DataLoader dataLoader = new DataLoader(currencyAdapter);
                ArrayList<Currency> currencies = dataLoader.loadData();

                // Update the UI on the main thread after data is loaded
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (currencies != null) {
                            currencyAdapter.updateData(currencies);
                        } else {
                            Toast.makeText(MainActivity.this, "Error fetching data", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
