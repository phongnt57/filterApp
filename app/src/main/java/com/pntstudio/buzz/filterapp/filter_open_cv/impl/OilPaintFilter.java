package com.pntstudio.buzz.filterapp.filter_open_cv.impl;


import com.pntstudio.buzz.filterapp.filter_open_cv.Filter;
import com.pntstudio.buzz.filterapp.filter_open_cv.FilterType;
import com.pntstudio.buzz.filterapp.filter_open_cv.Native;

import org.opencv.core.Mat;

/**
 * Oil-Paint Filter Implementation
 */
public class OilPaintFilter extends Filter {

    public OilPaintFilter(FilterType filterType) {
        super(filterType);

        mFilterConfigs.add(new Filter.FilterConfig("Radius"));
        mFilterConfigs.add(new FilterConfig("Levels"));
        mDefaultScaleFactor = 0.5;

        resetConfig();
    }

    @Override
    public void process(Mat src, Mat dst) {
        Native.oilPaintFilter(src.getNativeObjAddr(), dst.getNativeObjAddr(), mFilterConfigs.get(0).value, mFilterConfigs.get(1).value);
    }

    @Override
    public void resetConfig() {
        mFilterConfigs.get(0).value = 40;
        mFilterConfigs.get(1).value = 40;
    }
}
