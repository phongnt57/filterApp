package com.pntstudio.buzz.filterapp;/*
 * Copyright 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.pntstudio.buzz.filterapp.model.FilterCameraModel;

import org.opencv.android.OpenCVLoader;

public class CameraActivity extends AppCompatActivity {
    private int filterId = R.id.filter0;
    Camera2BasicFragment camera2BasicFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        camera2BasicFragment = Camera2BasicFragment.newInstance();
        if (null == savedInstanceState) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, camera2BasicFragment)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.filter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        filterId = item.getItemId();

        // TODO: need tidy up
        if (filterId == R.id.capture) {
            camera2BasicFragment.switchCamera();

            return true;
        }

        setTitle(item.getTitle());

        if (camera2BasicFragment != null)
            camera2BasicFragment.setSelectedFilter(filterId);

        return true;
    }

    public void setFilterCamera(FilterCameraModel filterCamera) {
        camera2BasicFragment.setSelectedFilter(filterCamera);
    }

    @Override
    public void onBackPressed() {
        if(camera2BasicFragment.handleBackPress()) {
            super.onBackPressed();
        }
    }
}
