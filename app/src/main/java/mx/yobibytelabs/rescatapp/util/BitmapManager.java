package mx.yobibytelabs.rescatapp.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.net.Uri;

import java.io.FileNotFoundException;

/**
 * Created by jagspage2013 on 31/07/14.
 */
public class BitmapManager {

    public static Bitmap getCircleBitmap(Bitmap bitmap) {
        Bitmap output;
        if (bitmap.getWidth() < bitmap.getHeight()) {
            output = Bitmap.createBitmap(bitmap.getWidth(),
                    bitmap.getWidth(), Bitmap.Config.ARGB_8888);
        } else {
            output = Bitmap.createBitmap(bitmap.getHeight(),
                    bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        }

        final Canvas canvas = new Canvas(output);
        final int color = Color.RED;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final Rect rect2 = new Rect(0, 0, bitmap.getHeight(), bitmap.getWidth());


        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        //canvas.drawOval(rectF, paint);
        if (bitmap.getWidth() < bitmap.getHeight()) {
            canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getWidth() / 2, bitmap.getWidth() / 2, paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            canvas.drawBitmap(bitmap, rect2, rect2, paint);
        } else {
            canvas.drawCircle(bitmap.getHeight() / 2, bitmap.getHeight() / 2, bitmap.getHeight() / 2, paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            canvas.drawBitmap(bitmap, rect2, rect2, paint);
        }
        bitmap.recycle();
        return output;
    }

    public static Bitmap rotateBitmap(final Bitmap source, int mRotation) {
        int targetWidth = source.getWidth();
        int targetHeight = source.getHeight();
        Bitmap targetBitmap = Bitmap.createBitmap(targetWidth, targetHeight, source.getConfig());
        Canvas canvas = new Canvas(targetBitmap);
        Matrix matrix = new Matrix();
        matrix.setRotate(mRotation, source.getWidth() / 2, source.getHeight() / 2);
        canvas.drawBitmap(source, matrix, new Paint());
        return targetBitmap;
    }

    public static Bitmap decodeUri(Context context, Uri selectedImage) throws FileNotFoundException {

        // Decode image size
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(context.getContentResolver().openInputStream(selectedImage), null, o);

        // The new size we want to scale to
        final int REQUIRED_SIZE = 140;

        // Find the correct scale value. It should be the power of 2.
        int width_tmp = o.outWidth, height_tmp = o.outHeight;
        int scale = 1;
        while (true) {
            if (width_tmp / 2 < REQUIRED_SIZE
                    || height_tmp / 2 < REQUIRED_SIZE) {
                break;
            }
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }

        // Decode with inSampleSize
        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        return BitmapFactory.decodeStream(context.getContentResolver().openInputStream(selectedImage), null, o2);

    }
}
