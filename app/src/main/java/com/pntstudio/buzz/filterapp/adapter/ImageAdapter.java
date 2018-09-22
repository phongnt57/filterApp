package com.pntstudio.buzz.filterapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pntstudio.buzz.filterapp.R;
import com.pntstudio.buzz.filterapp.model.ImageModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    private ArrayList<ImageModel> galleryList;
    private Context context;

    public ImageAdapter(Context context, ArrayList<ImageModel> galleryList) {
        this.galleryList = galleryList;
        this.context = context;
    }

    @NonNull
    @Override
    public ImageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_image_category, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageAdapter.ViewHolder viewHolder, int i) {
        viewHolder.title.setText(galleryList.get(i).getTitle());
        viewHolder.img.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        viewHolder.img.setImageResource((galleryList.get(i).getUrl()));
        Picasso.with(context).load(galleryList.get(i).getUrl()).into(viewHolder.img);
    }

    @Override
    public int getItemCount() {
        return galleryList.size();
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