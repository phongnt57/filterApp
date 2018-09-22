package com.pntstudio.buzz.filterapp;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.HashMap;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int SELECT_PICTURE = 1;
    private static final String TAG = MenuActivity.class.getSimpleName();

    private AdView mAdView;
    private PagerIndicator mPageIndicator;
    private SliderLayout mSliderLayout;
    private ImageView mCameraImg;
    private LinearLayout mSelectPictureLl;
    // link to save image category
    private LinearLayout mCategoryLl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        mAdView = findViewById(R.id.adView);
        mPageIndicator = findViewById(R.id.custom_indicator);
        mSliderLayout = findViewById(R.id.slider_layout);
        mCameraImg = findViewById(R.id.camera_img);
        mSelectPictureLl = findViewById(R.id.select_picture_ll);
        mCategoryLl = findViewById(R.id.category_ll);

        Window w = getWindow();
        w.setFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);


        initAd();
        initPageIndicator();
        mCameraImg.setOnClickListener(this);
        mSelectPictureLl.setOnClickListener(this);
        mCategoryLl.setOnClickListener(this);


    }

    private void initPageIndicator() {
        HashMap<String, Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("Oil Paint", R.drawable.slider_001);
        file_maps.put("Pencil Sketch", R.drawable.slider_002);
        file_maps.put("Oil Sketch", R.drawable.slider_003);
        file_maps.put("Color Sketch", R.drawable.slider_004);
        file_maps.put("Carton Color ", R.drawable.slider_005);
        for (String name : file_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.CenterCrop);
//                    .setOnSliderClickListener(this);

            mSliderLayout.addSlider(textSliderView);
        }
        mSliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mSliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
//        mSliderLayout.setCustomAnimation(new DescriptionAnimation());
        mSliderLayout.setDuration(4000);

    }

    private void initAd() {
        MobileAds.initialize(this, getString(R.string.admob_app_id));
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
        mAdView.setAdSize(AdSize.BANNER);
        mAdView.loadAd(adRequest);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main3, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.camera_img:
                Intent intent =new Intent(this,CameraActivity.class);
                startActivity(intent);
                break;
            case R.id.select_picture_ll:
                Intent picutreIntent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                picutreIntent.setType("image/*");
                startActivityForResult(Intent.createChooser(picutreIntent, "Select Picture"), SELECT_PICTURE);
                break;
            case R.id.category_ll:
                Intent saveCatrgoryIntent  = new Intent(this,CategoryActivity.class);
                startActivity(saveCatrgoryIntent);
                break;

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };

                // Get the cursor
                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                // Get selected picture filepath
                String pictureFilePath = cursor.getString(columnIndex);
                cursor.close();
                Log.d(TAG, "Picture picked- " + pictureFilePath);

                // Switch to picture view mode, loading the selected picture
                openPictureFilterViewer(pictureFilePath);
            }
        }
    }

    private void openPictureFilterViewer(String pictureFilePath){
        Intent intent  = new Intent(this,ImageActivity.class);
        intent.putExtra(ImageActivity.INTENT_DATA,pictureFilePath);
        startActivity(intent);

    }
}
