package com.natashatherobot.app;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SearchActivity extends ActionBarActivity {
    EditText etQuery;
    GridView gvResults;
    Button btnSearch;
    ArrayList<ImageResult> imageResults = new ArrayList<ImageResult>();
    ImageResultArrayAdapter imageAdapter;
    String query;
    QueryFilter filter = new QueryFilter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setupViews();
        imageAdapter = new ImageResultArrayAdapter(this, imageResults);
        configureGridView();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.miFilter:
                displayFilterActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void displayFilterActivity() {
        Intent i = new Intent(SearchActivity.this, FilterActivity.class);
        i.putExtra("filter", filter);
        startActivity(i);
    }

    public void onImageSearch(View v) {
        // dismiss keyboard
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(etQuery.getWindowToken(), 0);

        query = etQuery.getText().toString();
        Toast.makeText(this, "Searching for " + query, Toast.LENGTH_SHORT)
                .show();
        imageResults.clear();
        performSearch(0);
    }

    private void setupViews() {
        etQuery = (EditText) findViewById(R.id.etQuery);
        gvResults = (GridView) findViewById(R.id.gvResults);
        btnSearch = (Button) findViewById(R.id.btnSearch);
    }

    private void configureGridView() {
        imageAdapter = new ImageResultArrayAdapter(this, imageResults);
        gvResults.setAdapter(imageAdapter);
        gvResults.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to your AdapterView
                //  customLoadMoreDataFromApi(page);
                customLoadMoreDataFromApi(totalItemsCount);
            }
        });
        gvResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), ImageDisplayActivity.class);
                ImageResult imageResult = imageResults.get(position);
                i.putExtra("result", imageResult);
                startActivity(i);
            }
        });
    }

    private void performSearch(int offset) {
        AsyncHttpClient client = new AsyncHttpClient();
        String baseUrl = "https://ajax.googleapis.com/ajax/services/search/images?";

        RequestParams parameters = new RequestParams();
        parameters.add("rsz", "8");
        parameters.add("as_filetype", "png");
        parameters.add("start", Integer.toString(offset));
        parameters.add("v", "1.0");
        parameters.add("q", Uri.encode(query));
        if (filter.getSize() != null) {
            parameters.add("imgsz", filter.getSize());
        }
        if (filter.getColor() != null) {
            parameters.add("imgcolor", filter.getColor());
        }
        if (filter.getType() != null) {
            parameters.add("imgtype", filter.getType());
        }
        if (filter.getSite() != null) {
            parameters.add("as_sitesearch", filter.getSite());
        }

        client.get(baseUrl, parameters, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(JSONObject response) {
                JSONArray imageJsonResults = null;
                try {
                    imageJsonResults = response.getJSONObject("responseData").getJSONArray("results");
                    imageResults.addAll(ImageResult.fromJSONArray(imageJsonResults));
                    imageAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        client.get("https://ajax.googleapis.com/ajax/services/search/images?rsz=8&as_filetype=png&start=" + offset + "&v=1.0&q=" + Uri.encode(query),
                new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(JSONObject response) {
                        JSONArray imageJsonResults = null;
                        try {
                            imageJsonResults = response.getJSONObject("responseData").getJSONArray("results");
                            imageResults.addAll(ImageResult.fromJSONArray(imageJsonResults));
                            imageAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    // Append more data into the adapter
    private void customLoadMoreDataFromApi(int offset) {
        // This method probably sends out a network request and appends new data items to your adapter.
        // Use the offset value and add it as a parameter to your API request to retrieve paginated data.
        // Deserialize API response and then construct new objects to append to the adapter
        performSearch(offset);
    }
}
