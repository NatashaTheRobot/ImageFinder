package com.natashatherobot.app;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

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

        sprColorFilter = (Spinner) findViewById(R.id.sprColorFilter);
        colorAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, filter.getColors());
        colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sprColorFilter.setAdapter(colorAdapter);

        sprImageType = (Spinner) findViewById(R.id.sprImageType);
        typeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, filter.getTypes());
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sprImageType.setAdapter(typeAdapter);

        sprImageSize = (Spinner) findViewById(R.id.sprImageSize);
        sizeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, filter.getSizes());
        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sprImageSize.setAdapter(sizeAdapter);

        etSiteFilter = (EditText) findViewById(R.id.etSiteFilter);
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

}
