package com.ajscape.pixatoon.ui;


import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

import com.ajscape.pixatoon.R;

/**
 * Main Activity
 */
public class MainActivity extends Activity {

    private static final int SELECT_PICTURE = 1;
    private static final int REQUEST_PERMISSIONS = 2;
    private static final String TAG = "MainActivity";

    private static final int ORIENTATION_THRESH = 10;


    // Statically Load native OpenCV and image filter implementation libraries
    static {
        System.loadLibrary("opencv_java3");
        System.loadLibrary("image_filters");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setContentView(R.layout.activity_main);


    }
}
