package com.pntstudio.buzz.filterapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pntstudio.buzz.filterapp.R;
import com.pntstudio.buzz.filterapp.model.ImageModel;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    private ArrayList<ImageModel> galleryList;
    private Context context;
    private OnClickImageModel onClickImageModel;

    public ImageAdapter(Context context, ArrayList<ImageModel> galleryList,OnClickImageModel onClickImageModel) {
        this.galleryList = galleryList;
        this.context = context;
        this.onClickImageModel = onClickImageModel;
    }

    @NonNull
    @Override
    public ImageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_image_category, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageAdapter.ViewHolder viewHolder, int i) {
        final ImageModel imageModel  = galleryList.get(i);

        viewHolder.title.setText(imageModel.getTitle());
        viewHolder.img.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        viewHolder.img.setImageResource((galleryList.get(i).getUrl()));
        Picasso.with(context).load(new File(imageModel.getUrl()))
                .placeholder(R.drawable.ic_placeholder)
                .fit()
                .centerCrop()
                .into(viewHolder.img);
        viewHolder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickImageModel.onClick(imageModel);
            }
        });

        viewHolder.img.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                onClickImageModel.onLongClick(imageModel);
                return false;
            }
        });
        Log.e("--",galleryList.get(i).getUrl());
    }

    @Override
    public int getItemCount() {
        return galleryList.size();
    }

    public interface OnClickImageModel{
        void  onClick(ImageModel imageModel);
        void  onLongClick(ImageModel imageModel);
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private ImageView img;
        public ViewHolder(View view) {
            super(view);

            title = (TextView)view.findViewById(R.id.title);
            img = (ImageView) view.findViewById(R.id.img);
        }
    }
}