package com.pntstudio.buzz.filterapp.filter_open_cv.impl;


import com.pntstudio.buzz.filterapp.filter_open_cv.Filter;
import com.pntstudio.buzz.filterapp.filter_open_cv.FilterType;
import com.pntstudio.buzz.filterapp.filter_open_cv.Native;

import org.opencv.core.Mat;

/**
 * Pixel-Art filter implementation
 */
public class PixelArtFilter extends Filter {

    public PixelArtFilter(FilterType filterType) {
        super(filterType);

        mFilterConfigs.add(new Filter.FilterConfig("Size"));
        mFilterConfigs.add(new FilterConfig("Colors"));
        mDefaultScaleFactor = 0.8;

        resetConfig();
    }

    @Override
    public void process(Mat src, Mat dst) {
        Native.pixelArtFilter(src.getNativeObjAddr(), dst.getNativeObjAddr(), mFilterConfigs.get(0).value, mFilterConfigs.get(1).value);
    }

    @Override
    public void resetConfig() {
        mFilterConfigs.get(0).value = 30;
        mFilterConfigs.get(1).value = 50;
    }
}
