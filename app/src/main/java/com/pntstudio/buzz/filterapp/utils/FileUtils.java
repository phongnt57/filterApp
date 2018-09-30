package com.pntstudio.buzz.filterapp.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.hardware.camera2.CameraCharacteristics;
import android.media.ExifInterface;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by admin on 9/1/18.
 */

public class FileUtils {

    private static String genSaveFileName(String prefix) {
        Date date = new Date();
        SimpleDateFormat dateformat1 = new SimpleDateFormat("yyyyMMdd_hhmmss");
        String timeString = dateformat1.format(date);
        String externalPath = Environment.getExternalStorageDirectory().toString() + "/" + "uSketch";
        File myDir = new File(Environment.getExternalStorageDirectory(), "uSketch" );
        if (!myDir.exists())
        {
            if (!myDir.mkdirs())
            {
                Log.e("Error","Can not create find");
            }
        }

        return externalPath + "/" + prefix + timeString + ".jpg";
    }


    public static String getPathTempFile(Context context) {
//        Date currentDate = new Date(System.currentTimeMillis());
//        return getAppImagePath(context) + "/" + currentDate.getTime() + ".png";
        return getAppImagePath(context) + "/" + "temp" + ".png";
    }

    private static String getAppImagePath(Context context) {
        return context.getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath();
    }

    public static String saveImage(Bitmap bitmap, Context context,int currentTypeCamera) {
        String mPath = getPathTempFile(context);
        File imageFile = new File(mPath);
        if (imageFile.exists()) {
            imageFile.delete();
        }
        if(currentTypeCamera== CameraCharacteristics.LENS_FACING_FRONT){
            bitmap = FileUtils.rotateBitmap(bitmap,180);
        }

        // create bitmap screen capture
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, outputStream);
            outputStream.flush();
            outputStream.close();
//            Toast.makeText(context,mPath,Toast.LENGTH_SHORT).show();
            return mPath;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    public static String saveStyleImage(Bitmap bitmap, Context context, String fullPath) {

        String mPath;
        if (fullPath.equals(""))
            mPath = genSaveFileName("sketch");
        else mPath = fullPath;
            File imageFile = new File(mPath);
        if (imageFile.exists()) {
            imageFile.delete();
        }

        // create bitmap screen capture
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream);
            outputStream.flush();
            outputStream.close();
            Toast.makeText(context, mPath, Toast.LENGTH_SHORT).show();
            return mPath;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    public static Bitmap modifyOrientation(Bitmap bitmap, String image_absolute_path) throws IOException {
        ExifInterface ei = new ExifInterface(image_absolute_path);
        int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

        switch (orientation) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                return rotate(bitmap, 90);

            case ExifInterface.ORIENTATION_ROTATE_180:
                return rotate(bitmap, 180);

            case ExifInterface.ORIENTATION_ROTATE_270:
                return rotate(bitmap, 270);

            case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
                return flip(bitmap, true, false);

            case ExifInterface.ORIENTATION_FLIP_VERTICAL:
                return flip(bitmap, false, true);

            default:
                return bitmap;
        }
    }

    private static Bitmap rotate(Bitmap bitmap, float degrees) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degrees);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    private static Bitmap flip(Bitmap bitmap, boolean horizontal, boolean vertical) {
        Matrix matrix = new Matrix();
        matrix.preScale(horizontal ? -1 : 1, vertical ? -1 : 1);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public static Bitmap rotateBitmap(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }
}
