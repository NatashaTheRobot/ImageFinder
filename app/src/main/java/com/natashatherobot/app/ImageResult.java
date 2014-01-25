package com.natashatherobot.app;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Natasha Murashev on 1/25/14.
 */
public class ImageResult {
    private String fullUrl;
    private String thumbUrl;
    private String title;

    public ImageResult(JSONObject json) {
        try {
            this.fullUrl = json.getString("url");
            this.thumbUrl = json.getString("tbUrl");
            this.title = json.getString("titleNoFormatting");
        } catch (JSONException e) {
            this.fullUrl = null;
            this.thumbUrl = null;
        }
    }

    public String getFullUrl() {
        return fullUrl;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public String getTitle() {
        return title;
    }

    public String toString() {
        return this.thumbUrl;
    }

    public static ArrayList<ImageResult> fromJSONArray(JSONArray jsonArray) {
        ArrayList<ImageResult> results = new ArrayList<ImageResult>();
        for (int i=0; i < jsonArray.length(); i++) {
            try {
                results.add(new ImageResult(jsonArray.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return results;
    }
}
