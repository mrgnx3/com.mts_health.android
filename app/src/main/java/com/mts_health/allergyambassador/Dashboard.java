package com.mts_health.allergyambassador;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.HashMap;
import java.util.Map;


public class Dashboard extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        createSpinner();
        addNavigationListenersToDashboardButtons();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Boolean handled = true;

        int id = item.getItemId();
        switch (id) {
            case R.id.action_settings:
                return true;
            default:
                handled = super.onOptionsItemSelected(item);
        }
        return handled;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        String selectedCountry = parent.getItemAtPosition(pos).toString();
        loadContextForSelectedCountry(selectedCountry);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void addNavigationListenersToDashboardButtons() {
        Map<Object, Integer> navigatablePages = new HashMap<>();
        navigatablePages.put(AllergenLawsPage.class, R.id.allergyLawsButton);
        navigatablePages.put(CompareCountryLawsPage.class, R.id.compareButton);
        navigatablePages.put(PhrasebookPage.class, R.id.allergenPhrasebookButton);
        navigatablePages.put(PersonalReactionJournalPage.class, R.id.personalRiskLogbookButton);

        for (final Map.Entry<Object, Integer> activityPage : navigatablePages.entrySet()) {
            final Button button = (Button) findViewById(activityPage.getValue());
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(Dashboard.this, (Class<?>) activityPage.getKey());
                    startActivity(intent);
                }
            });
        }
    }

    private void loadContextForSelectedCountry(String country) {
        //todo Create controller to load contexts for each region/country
        String breakpoint;
    }

    private void createSpinner() {
        Spinner spinner = (Spinner) findViewById(R.id.listOfCountries);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.list_of_countries, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }
}
