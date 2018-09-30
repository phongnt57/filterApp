package com.pntstudio.buzz.filterapp.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;


import com.ajscape.pixatoon.lib.Filter;
import com.ajscape.pixatoon.lib.FilterManager;
import com.ajscape.pixatoon.lib.FilterType;
import com.pntstudio.buzz.filterapp.CameraActivity;
import com.pntstudio.buzz.filterapp.R;

import com.pntstudio.buzz.filterapp.adapter.FilterCameraAdapter;
import com.pntstudio.buzz.filterapp.filter_opengl.AsciiArtFilter;
import com.pntstudio.buzz.filterapp.filter_opengl.BasicDeformFilter;
import com.pntstudio.buzz.filterapp.filter_opengl.BlueorangeFilter;
import com.pntstudio.buzz.filterapp.filter_opengl.ChromaticAberrationFilter;
import com.pntstudio.buzz.filterapp.filter_opengl.ContrastFilter;
import com.pntstudio.buzz.filterapp.filter_opengl.CrackedFilter;
import com.pntstudio.buzz.filterapp.filter_opengl.CrosshatchFilter;
import com.pntstudio.buzz.filterapp.filter_opengl.EMInterferenceFilter;
import com.pntstudio.buzz.filterapp.filter_opengl.EdgeDetectionFilter;
import com.pntstudio.buzz.filterapp.filter_opengl.JFAVoronoiFilter;
import com.pntstudio.buzz.filterapp.filter_opengl.LegofiedFilter;
import com.pntstudio.buzz.filterapp.filter_opengl.LichtensteinEsqueFilter;
import com.pntstudio.buzz.filterapp.filter_opengl.MappingFilter;
import com.pntstudio.buzz.filterapp.filter_opengl.MoneyFilter;
import com.pntstudio.buzz.filterapp.filter_opengl.NoiseWarpFilter;
import com.pntstudio.buzz.filterapp.filter_opengl.OriginalFilter;
import com.pntstudio.buzz.filterapp.filter_opengl.PixelizeFilter;
import com.pntstudio.buzz.filterapp.filter_opengl.PolygonizationFilter;
import com.pntstudio.buzz.filterapp.filter_opengl.RefractionFilter;
import com.pntstudio.buzz.filterapp.filter_opengl.TileMosaicFilter;
import com.pntstudio.buzz.filterapp.filter_opengl.TrianglesMosaicFilter;
import com.pntstudio.buzz.filterapp.fragment.interfaces.FilterSelectorListener;
import com.pntstudio.buzz.filterapp.model.FilterCameraModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Filter selector panel implemented as fragment
 */
public class FilterCameraFragment extends Fragment implements  FilterCameraAdapter.OnCameraFilterSelect {

    private static final String TAG = "FilterSelectorFragment:";
    private FilterSelectorListener mCallback;
    private Map<Integer,FilterType> mFilterMap;
    private List<FilterCameraModel> mFilterCameraModels;
    private RecyclerView mHorizontalRv;
    private FilterCameraAdapter mFilterCameraAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mCallback = (FilterSelectorListener) getActivity();
//        mFilterManager = FilterManager.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_filter_camera, container, false);
        mFilterCameraModels = new ArrayList<>();
        initFilterCameraList();


//        mScrollBar = view.findViewById(R.id.scrollBar);
        mHorizontalRv = view.findViewById(R.id.horizontal_filter_rv);
        mFilterCameraAdapter = new FilterCameraAdapter(getActivity(),mFilterCameraModels, this);
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false);
        mHorizontalRv.setLayoutManager(horizontalLayoutManager);
        mHorizontalRv.setAdapter(mFilterCameraAdapter);



        return view;
    }

    private void initFilterCameraList() {
        Context context = getActivity();
        mFilterCameraModels.add(new FilterCameraModel(R.id.filter0,"Origin", new OriginalFilter(context),R.drawable.ic_color_mix));
//        mFilterCameraModels.add(new FilterCameraModel(R.id.filter1,"Edege", new EdgeDetectionFilter(context),R.drawable.ic_color_mix));
//        mFilterCameraModels.add(new FilterCameraModel(R.id.filter2,"Pixel", new PixelizeFilter(context),R.drawable.ic_color_mix));
//        mFilterCameraModels.add(new FilterCameraModel(R.id.filter3,"EMInter", new EMInterferenceFilter(context),R.drawable.ic_color_mix));
        mFilterCameraModels.add(new FilterCameraModel(R.id.filter4,"TrianglesMosaic", new TrianglesMosaicFilter(context),R.drawable.triangle_mosaic));
//        mFilterCameraModels.add(new FilterCameraModel(R.id.filter5,"Legofied", new LegofiedFilter(context),R.drawable.ic_color_mix));
        mFilterCameraModels.add(new FilterCameraModel(R.id.filter6,"TileMosaic", new TileMosaicFilter(context),R.drawable.tile_mosaic));
//        mFilterCameraModels.add(new FilterCameraModel(R.id.filter7,"Blueorange", new BlueorangeFilter(context),R.drawable.ic_color_mix));
//        mFilterCameraModels.add(new FilterCameraModel(R.id.filter8,"ChromaticAber", new ChromaticAberrationFilter(context),R.drawable.ic_color_mix));
//        mFilterCameraModels.add(new FilterCameraModel(R.id.filter9,"BasicDeform", new BasicDeformFilter(context),R.drawable.ic_color_mix));
//        mFilterCameraModels.add(new FilterCameraModel(R.id.filter10,"Contrast", new ContrastFilter(context),R.drawable.ic_color_mix));
        mFilterCameraModels.add(new FilterCameraModel(R.id.filter11,"NoiseWarp", new NoiseWarpFilter(context),R.drawable.noise_warp));
//        mFilterCameraModels.add(new FilterCameraModel(R.id.filter12,"Refraction", new RefractionFilter(context),R.drawable.ic_color_mix));
//        mFilterCameraModels.add(new FilterCameraModel(R.id.filter13,"Mapping", new MappingFilter(context),R.drawable.ic_color_mix));
        mFilterCameraModels.add(new FilterCameraModel(R.id.filter14,"Crosshatch", new CrosshatchFilter(context),R.drawable.crosshatch));
//        mFilterCameraModels.add(new FilterCameraModel(R.id.filter15,"Lichtenstein", new LichtensteinEsqueFilter(context),R.drawable.ic_color_mix));
//        mFilterCameraModels.add(new FilterCameraModel(R.id.filter16,"AsciiArt", new AsciiArtFilter(context),R.drawable.ic_color_mix));
//        mFilterCameraModels.add(new FilterCameraModel(R.id.filter17,"Money", new MoneyFilter(context),R.drawable.ic_color_mix));
//        mFilterCameraModels.add(new FilterCameraModel(R.id.filter18,"Cracked", new CrackedFilter(context),R.drawable.ic_color_mix));
//        mFilterCameraModels.add(new FilterCameraModel(R.id.filter19,"Polygonization", new PolygonizationFilter(context),R.drawable.ic_color_mix));
//        mFilterCameraModels.add(new FilterCameraModel(R.id.filter20,"JFAVoronoi", new JFAVoronoiFilter(context),R.drawable.ic_color_mix));




    }

    @Override
    public void onPause() {
        super.onPause();
//        mLastScrollPosition = mScrollBar.getScrollX();
    }

    @Override
    public void onResume() {
        super.onResume();

    }





    @Override
    public void onSelect(FilterCameraModel filterCameraModel) {
        ((CameraActivity)getActivity()).setFilterCamera(filterCameraModel);


    }
}
