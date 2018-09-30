package com.pntstudio.buzz.filterapp.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;

import com.pntstudio.buzz.filterapp.CategoryActivity;
import com.pntstudio.buzz.filterapp.model.ImageModel;

/**
 * Created by admin on 9/30/18.
 */

public class DialogUtils {
    public static void showDeleteDialog(Context context, final CategoryActivity categoryActivity){
         {
            AlertDialog.Builder builder;
            builder = new AlertDialog.Builder(categoryActivity);
            builder
                    .setMessage("Are you sure want to delete this image?")
                    .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            categoryActivity.deleteImageModel();
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                            dialog.dismiss();
                        }
                    })

                    .show();
        }
    }
}
