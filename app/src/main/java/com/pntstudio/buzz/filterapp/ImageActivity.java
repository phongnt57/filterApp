package com.pntstudio.buzz.filterapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class ImageActivity extends AppCompatActivity {
    public static String INTENT_DATA = "imagw_path";
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        setContentView(R.layout.activity_image);
        mImageView  = findViewById(R.id.image_img);
        String imagePath = getIntent().getStringExtra(INTENT_DATA);
        Bitmap bmImg = BitmapFactory.decodeFile(imagePath);
        mImageView.setImageBitmap(bmImg);
    }
}
