package com.pntstudio.buzz.filterapp.fragment.interfaces;

import android.graphics.Bitmap;

/**
 * Camera/Gallery picture capture listener interface
 */
public interface FilterPictureCallback {

    /**
     * Callback method on capturing camera/gallery picture displayed on screen
     * @param bitmap
     */
    void onPictureCaptured(Bitmap bitmap);

}
