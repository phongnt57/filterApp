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
import com.pntstudio.buzz.filterapp.model.FilterCameraModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FilterCameraAdapter extends RecyclerView.Adapter<FilterCameraAdapter.ViewHolder> {
    private List<FilterCameraModel> galleryList;
    private Context context;
    private OnCameraFilterSelect onCameraFilterSelect;

    public interface OnCameraFilterSelect {
        void onSelect(FilterCameraModel filterCameraModel);
    }

    public FilterCameraAdapter(Context context, List<FilterCameraModel> galleryList, OnCameraFilterSelect onCameraFilterSelect) {
        this.galleryList = galleryList;
        this.context = context;
        this.onCameraFilterSelect = onCameraFilterSelect;
    }

    @NonNull
    @Override
    public FilterCameraAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_camera_filter, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilterCameraAdapter.ViewHolder viewHolder, int i) {
        final FilterCameraModel filterCameraModel = galleryList.get(i);
        viewHolder.title.setText(galleryList.get(i).getName());
        viewHolder.img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Picasso.with(context).load(galleryList.get(i).getDrawable()).into(viewHolder.img);
        viewHolder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCameraFilterSelect.onSelect(filterCameraModel);
            }
        });
    }

    @Override
    public int getItemCount() {
        return galleryList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private ImageView img;

        public ViewHolder(View view) {
            super(view);

            title = (TextView) view.findViewById(R.id.title_tv);
            img = (ImageView) view.findViewById(R.id.colorCartoonFilterBtn);
        }
    }
}