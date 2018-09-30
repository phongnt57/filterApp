package com.pntstudio.buzz.filterapp.model;

/**
 * Created by admin on 9/22/18.
 */

public class ImageModel {
    private String url;
    private String title;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ImageModel(String url, String title) {
        this.url = url;
        this.title = title;
    }
}
