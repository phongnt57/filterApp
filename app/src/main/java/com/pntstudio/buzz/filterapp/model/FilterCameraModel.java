package com.pntstudio.buzz.filterapp.model;

import com.pntstudio.buzz.filterapp.filter_opengl.CameraFilter;

/**
 * Created by admin on 9/24/18.
 */

public class FilterCameraModel {
    private String name;
    private  int id;
    private CameraFilter cameraFilter;
    private int drawable;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CameraFilter getCameraFilter() {
        return cameraFilter;
    }

    public void setCameraFilter(CameraFilter cameraFilter) {
        this.cameraFilter = cameraFilter;
    }

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }

    public FilterCameraModel(int id, String name, CameraFilter cameraFilter, int drawable) {
        this.name = name;
        this.id = id;
        this.cameraFilter = cameraFilter;
        this.drawable = drawable;

    }
}
