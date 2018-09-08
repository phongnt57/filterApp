package com.pntstudio.buzz.filterapp.filter_open_cv.impl;


import com.pntstudio.buzz.filterapp.filter_open_cv.Filter;
import com.pntstudio.buzz.filterapp.filter_open_cv.FilterType;
import com.pntstudio.buzz.filterapp.filter_open_cv.Native;

import org.opencv.core.Mat;

/**
 * Pencil-Sketch Filter implementation
 */
public class PencilSketchFilter extends Filter {

    public PencilSketchFilter(FilterType filterType) {
        super(filterType);

        mFilterConfigs.add(new FilterConfig("Blend"));
        mFilterConfigs.add(new FilterConfig("Scale"));
        mDefaultScaleFactor = 0.8;

        resetConfig();
    }

    @Override
    public void process(Mat src, Mat dst) {
        Native.pencilSketchFilter(src.getNativeObjAddr(), dst.getNativeObjAddr(), mFilterConfigs.get(0).value, mFilterConfigs.get(1).value);
    }

    @Override
    public void resetConfig() {
        mFilterConfigs.get(0).value = 70;
        mFilterConfigs.get(1).value = 40;
    }
}
