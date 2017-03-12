package com.yust.ariel.a6over6.utils;

/**
 * Created by Ariel Yust on 12-Mar-17.
 */

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.Image;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Created by Ariel Yust on 11-Mar-17.
 *
 * Saves a JPEG {@link Image} into the specified {@link File}.
 */
public class ImageSaver implements Runnable {

    /**
     * The JPEG image
     */
    private final Image mImage;
    /**
     * The file we save the image into.
     */
    private final File mFile;

    public ImageSaver(Image image, File file) {
        mImage = image;
        mFile = file;
    }

    @Override
    public void run() {
        ByteBuffer buffer = mImage.getPlanes()[0].getBuffer();
        byte[] originalBytes = new byte[buffer.remaining()];

        /* start saving bytes into file */
        buffer.get(originalBytes);
        FileOutputStream output = null;
        try {
            output = new FileOutputStream(mFile);
            output.write(originalBytes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            mImage.close();
            if (null != output) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}