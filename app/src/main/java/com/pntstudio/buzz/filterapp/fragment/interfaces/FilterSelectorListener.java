package com.pntstudio.buzz.filterapp.fragment.interfaces;

import com.pntstudio.buzz.filterapp.filter_open_cv.FilterType;

/**
 * Filter Selector Listener interface
 */
public interface FilterSelectorListener {

    /**
     * Callback method on selecting new filter from selector panel. The new filter type is passed as parameter.
     * @param filterType
     */
    void onFilterSelect(FilterType filterType);
}
