package com.natashatherobot.app;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by Natasha Murashev on 1/25/14.
 */
public class ImageResultArrayAdapter extends ArrayAdapter<ImageResult> {
    public ImageResultArrayAdapter(Context context, List<ImageResult> images) {
        super(context, android.R.layout.simple_list_item_1, images);
    }
}
