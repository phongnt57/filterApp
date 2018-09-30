package com.pntstudio.buzz.filterapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.pntstudio.buzz.filterapp.adapter.ImageAdapter;
import com.pntstudio.buzz.filterapp.model.ImageModel;
import com.pntstudio.buzz.filterapp.utils.DialogUtils;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity implements  ImageAdapter.OnClickImageModel {
    private ImageModel currentImageModel;
    ArrayList<ImageModel> arrayList;
    ImageAdapter adapter;

    private  ImageView mHeaderImg;
    private ImageView mShareImg;
    private ImageView mDeleteImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mHeaderImg = findViewById(R.id.header_img);
        mShareImg = findViewById(R.id.img_share);
        mDeleteImg = findViewById(R.id.img_delete);
        mShareImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentImageModel!=null) {
                    File file = new File(currentImageModel.getUrl());
                    Uri uri = FileProvider.getUriForFile(
                            CategoryActivity.this,
                            "com.pntstudio.buzz.filterapp.provider", //(use your app signature + ".provider" )
                            file);
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("image/*");
                    shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
                    shareIntent.putExtra(Intent.EXTRA_TEXT, "Share your sketch");
                    startActivity(shareIntent);
                }

            }
        });
        mDeleteImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogUtils.showDeleteDialog(CategoryActivity.this,CategoryActivity.this);


            }
        });
        findViewById(R.id.cancel_img).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CategoryActivity.this.finish();
            }
        });
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        RecyclerView recyclerView = findViewById(R.id.imagegallery_rv);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(layoutManager);
        prepareData();

        adapter = new ImageAdapter(getApplicationContext(), arrayList,this);
        recyclerView.setAdapter(adapter);

    }

    public void deleteImageModel(){
        if(currentImageModel!=null) {
            arrayList.remove(currentImageModel);
            adapter.notifyDataSetChanged();
            File file = new File(currentImageModel.getUrl());
            file.delete();
        }
        if(arrayList.size()>0) {
            currentImageModel = arrayList.get(0);
            Picasso.with(CategoryActivity.this).load(new File(currentImageModel.getUrl())).fit().centerCrop().into(mHeaderImg);
        }

    }
    private ArrayList<ImageModel> prepareData(){
        arrayList = new ArrayList<>();

        File directory = new File(Environment.getExternalStorageDirectory(), "uSketch" );
        File[] files = directory.listFiles();
        if(files==null) {
            Toast.makeText(this,"Your gallery is empty! Let create your amazing sketch!",Toast.LENGTH_SHORT).show();
            return null;
        }
        for (int i = 0; i < files.length; i++)
        {
            //add file
            File item = files[i];
            arrayList.add(new ImageModel(item.getAbsolutePath(),item.getName()));

        }
        if(arrayList.size()>0) {
            currentImageModel = arrayList.get(0);
            Picasso.with(this).load(new File(currentImageModel.getUrl())).fit().centerCrop().into(mHeaderImg);
        }else {
            Toast.makeText(this,"Your gallery is empty! Let create your amazing sketch!",Toast.LENGTH_SHORT).show();
        }
        return arrayList;
    }

    @Override
    public void onClick(ImageModel imageModel) {
        currentImageModel  = imageModel;
        Picasso.with(this).load(new File(currentImageModel.getUrl())).fit().centerCrop().into(mHeaderImg);

    }

    @Override
    public void onLongClick(ImageModel imageModel) {

    }
}
