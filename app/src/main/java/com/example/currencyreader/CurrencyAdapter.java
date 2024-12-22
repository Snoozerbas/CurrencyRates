package com.example.currencyreader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CurrencyAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Currency> currencyList;

    // Constructor to initialize the adapter with context and data
    public CurrencyAdapter(Context context, ArrayList<Currency> currencyList) {
        this.context = context;
        this.currencyList = currencyList;
    }

    @Override
    public int getCount() {
        return currencyList.size();
    }

    @Override
    public Object getItem(int position) {
        return currencyList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.currency_list_item, parent, false);
        }

        TextView codeTextView = convertView.findViewById(R.id.currencyCodeTextView);
        TextView rateTextView = convertView.findViewById(R.id.currencyRateTextView);

        // Get the currency object at the current position
        Currency currency = currencyList.get(position);

        // Set the currency code and rate on the TextViews
        codeTextView.setText(currency.getCode()); // Set the currency code
        rateTextView.setText(String.valueOf(currency.getRate())); // Set the exchange rate

        return convertView;
    }

    // Method to update the adapter's data
    public void updateData(ArrayList<Currency> newData) {
        this.currencyList = newData;
        notifyDataSetChanged();
    }
}
