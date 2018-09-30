package com.pntstudio.buzz.filterapp;

import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.pntstudio.buzz.filterapp.adapter.ImageAdapter;
import com.pntstudio.buzz.filterapp.model.ImageModel;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity implements  ImageAdapter.OnClickImageModel {
    private ImageModel currentImageModel;
    ArrayList<ImageModel> arrayList;

    private  ImageView mHeaderImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mHeaderImg = findViewById(R.id.header_img);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        RecyclerView recyclerView = findViewById(R.id.imagegallery_rv);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(layoutManager);
        ArrayList<ImageModel> createLists = prepareData();

        ImageAdapter adapter = new ImageAdapter(getApplicationContext(), createLists,this);
        recyclerView.setAdapter(adapter);

    }
    private ArrayList<ImageModel> prepareData(){
        arrayList = new ArrayList<>();

        File directory = new File(Environment.getExternalStorageDirectory(), "uSketch" );
        File[] files = directory.listFiles();
        for (int i = 0; i < files.length; i++)
        {
            //add file
            File item = files[i];
            arrayList.add(new ImageModel(item.getAbsolutePath(),item.getName()));

        }
        currentImageModel = arrayList.get(0);
        Picasso.with(this).load(new File(currentImageModel.getUrl())).fit().centerCrop().into(mHeaderImg);
        return arrayList;
    }

    @Override
    public void onClick(ImageModel imageModel) {
        currentImageModel  = imageModel;
        Picasso.with(this).load(new File(currentImageModel.getUrl())).fit().centerCrop().into(mHeaderImg);

    }
}
