package com.pntstudio.buzz.filterapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.pntstudio.buzz.filterapp.adapter.ImageAdapter;
import com.pntstudio.buzz.filterapp.model.ImageModel;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        RecyclerView recyclerView = findViewById(R.id.imagegallery_rv);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(layoutManager);
        ArrayList<ImageModel> createLists = prepareData();
        ImageAdapter adapter = new ImageAdapter(getApplicationContext(), createLists);
        recyclerView.setAdapter(adapter);

    }
    private ArrayList<ImageModel> prepareData(){
        ArrayList<ImageModel> arrayList = new ArrayList<>();
        return arrayList;
    }
}
