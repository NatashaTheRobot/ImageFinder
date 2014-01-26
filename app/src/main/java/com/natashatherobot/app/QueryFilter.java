package com.natashatherobot.app;

import java.io.Serializable;

/**
 * Created by Natasha Murashev on 1/25/14.
 */
public class QueryFilter implements Serializable {
    private static final long serialVersionUID = 4219758740790527158L;
    private String size;
    private String color;
    private String type;
    private String site;

    public QueryFilter() {

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
}
