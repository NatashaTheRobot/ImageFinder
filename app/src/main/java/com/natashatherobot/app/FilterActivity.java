package com.natashatherobot.app;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class FilterActivity extends ActionBarActivity {
    QueryFilter filter;

    EditText etSiteFilter;

    Spinner sprColorFilter;
    Spinner sprImageType;
    Spinner sprImageSize;

    ArrayAdapter<String> colorAdapter;
    ArrayAdapter<String> typeAdapter;
    ArrayAdapter<String> sizeAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        filter = (QueryFilter) getIntent().getSerializableExtra("filter");
        configureSpinners();
        configureSiteFilter();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.filter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent data = new Intent();
        data.putExtra("filter", filter);
        setResult(RESULT_OK, data);
        this.finish();
    }

    private void configureSpinners() {
        sprColorFilter = (Spinner) findViewById(R.id.sprColorFilter);
        colorAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, filter.getColors());
        colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sprColorFilter.setAdapter(colorAdapter);
        int currentColorIndex = filter.getColors().indexOf(filter.getColor());
        sprColorFilter.setSelection(currentColorIndex);
        sprColorFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedColor = filter.getColors().get(position);
                filter.setColor(selectedColor);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sprImageType = (Spinner) findViewById(R.id.sprImageType);
        typeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, filter.getTypes());
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sprImageType.setAdapter(typeAdapter);
        int currentTypeIndex = filter.getTypes().indexOf(filter.getType());
        sprImageType.setSelection(currentTypeIndex);
        sprImageType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedType = filter.getTypes().get(position);
                filter.setType(selectedType);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sprImageSize = (Spinner) findViewById(R.id.sprImageSize);
        sizeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, filter.getSizes());
        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sprImageSize.setAdapter(sizeAdapter);
        int currentSizeIndex = filter.getSizes().indexOf(filter.getSize());
        sprImageSize.setSelection(currentSizeIndex);
        sprImageSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedSize = filter.getSizes().get(position);
                filter.setSize(selectedSize);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void configureSiteFilter() {
        etSiteFilter = (EditText) findViewById(R.id.etSiteFilter);
        etSiteFilter.setText(filter.getSite());
        etSiteFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter.setSite(etSiteFilter.getText().toString());
            }
        });
    }

}
