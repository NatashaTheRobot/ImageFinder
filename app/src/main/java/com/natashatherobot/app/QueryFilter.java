package com.natashatherobot.app;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Natasha Murashev on 1/25/14.
 */
public class QueryFilter implements Serializable {
    private static final long serialVersionUID = 4219758740790527158L;

    private String size;
    private String color;
    private String type;
    private String site;

    private ArrayList<String> sizes;
    private ArrayList<String> colors;
    private ArrayList<String> types;

    public QueryFilter() {
        size = "all";
        color = "all";
        type = "all";

        configureSizes();
        configureColors();
        configureTypes();
    }

    public String getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

    public String getType() {
        return type;
    }

    public String getSite() {
        return site;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public ArrayList<String> getColors() {
        return colors;
    }

    public ArrayList<String> getSizes() {
        return sizes;
    }

    public ArrayList<String> getTypes() {
        return types;
    }

    private void configureColors() {
        colors = new ArrayList<String>(12);
        colors.add("any");
        colors.add("black");
        colors.add("blue");
        colors.add("brown");
        colors.add("gray");
        colors.add("green");
        colors.add("orange");
        colors.add("pink");
        colors.add("red");
        colors.add("teal");
        colors.add("white");
        colors.add("yellow");
    }

    private void configureSizes() {
        sizes = new ArrayList<String>(8);
        sizes.add("any");
        sizes.add("icon");
        sizes.add("small");
        sizes.add("medium");
        sizes.add("large");
        sizes.add("xlarge");
        sizes.add("xxlarge");
        sizes.add("huge");
    }

    private void configureTypes() {
        types = new ArrayList<String>(4);
        types.add("any");
        types.add("clipart");
        types.add("face");
        types.add("lineart");
        types.add("photo");
    }
}
