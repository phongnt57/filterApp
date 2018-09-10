package com.pntstudio.buzz.filterapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.pntstudio.buzz.filterapp.filter_open_cv.FilterManager;
import com.pntstudio.buzz.filterapp.fragment.FilterConfigFragment;
import com.pntstudio.buzz.filterapp.fragment.FilterSelectorFragment;

public class ImageActivity extends AppCompatActivity {
    private static final String TAG = "ImageActivity";

    public static String INTENT_DATA = "image_path";
    private ImageView mImageView;

    private FilterManager mFilterManager;
    private FilterSelectorFragment mFilterSelectorFragment;
    private FilterConfigFragment mFilterConfigFragment;

    // Statically Load native OpenCV and image filter implementation libraries
    static {
        System.loadLibrary("opencv_java3");
        System.loadLibrary("image_filters");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        setContentView(R.layout.activity_image);
        mImageView  = findViewById(R.id.image_img);
        String imagePath = getIntent().getStringExtra(INTENT_DATA);
        Bitmap bmImg = BitmapFactory.decodeFile(imagePath);
        mImageView.setImageBitmap(bmImg);


        mFilterManager = FilterManager.getInstance();

        mFilterSelectorFragment = new FilterSelectorFragment();
    }

    /**
     * Returns true if filter configuration panel is opened, else returns false
     * @return
     */
    private boolean isFilterConfigVisible() {
        if(mFilterConfigFragment!=null && mFilterConfigFragment.isVisible())
            return true;
        else
            return false;
    }

    /**
     * Open filter configuration panel with current filter specific settings
     */
    private void openCurrentFilterConfig() {
        if (mFilterManager.getCurrentFilter()!=null && !isFilterConfigVisible()) {

            mFilterConfigFragment = new FilterConfigFragment();
            mFilterConfigFragment.setFilter(mFilterManager.getCurrentFilter());

            mConfigFilterBtn.setImageResource(R.drawable.icon_btn_settings_on);
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.filterConfigPanel, mFilterConfigFragment)
                    .commit();
            Log.d(TAG, "filter config opened");
        }
    }

    /**
     * Open filter selector panel, to choose between different image filters
     */
    private void openFilterSelector() {
        if(!mFilterSelectorFragment.isVisible()) {
//            mSelectFilterBtn.setImageResource(R.drawable.icon_btn_filters_on);

            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.filterSelectorPanel, mFilterSelectorFragment)
                    .commit();

            Log.d(TAG, "filter selector opened");
        }
    }



}
