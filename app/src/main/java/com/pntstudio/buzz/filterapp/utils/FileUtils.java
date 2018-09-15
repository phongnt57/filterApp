package com.pntstudio.buzz.filterapp.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by admin on 9/1/18.
 */

public class FileUtils {

    private static String genSaveFileName(String prefix, String suffix) {
        Date date = new Date();
        SimpleDateFormat dateformat1 = new SimpleDateFormat("yyyyMMdd_hhmmss");
        String timeString = dateformat1.format(date);
        String externalPath = Environment.getExternalStorageDirectory().toString();
        return externalPath + "/" + prefix + timeString + suffix;
    }


    public static String getPathTempFile(Context context) {
//        Date currentDate = new Date(System.currentTimeMillis());
//        return getAppImagePath(context) + "/" + currentDate.getTime() + ".png";
        return getAppImagePath(context) + "/" + "xxx" + ".png";
    }
    private static String getAppImagePath(Context context) {
        return context.getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath();
    }
    public static String saveImage(Bitmap bitmap, Context context) {
        String mPath = getPathTempFile(context);
        File imageFile = new File(mPath);
        if (imageFile.exists()) {
            imageFile.delete();
        }

        // create bitmap screen capture
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, outputStream);
            outputStream.flush();
            outputStream.close();
            Toast.makeText(context,mPath,Toast.LENGTH_SHORT).show();
            return  mPath;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }
}
