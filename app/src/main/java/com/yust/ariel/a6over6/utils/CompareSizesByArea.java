package com.yust.ariel.a6over6.utils;

import android.util.Size;

import java.util.Comparator;

/**
 * Created by Ariel Yust on 11-Mar-17.
 *
 * Compares two {@code Size}s based on their areas.
 */
public class CompareSizesByArea implements Comparator<Size> {

    @Override
    public int compare(Size lhs, Size rhs) {
        // We cast here to ensure the multiplications won't overflow
        return Long.signum((long) lhs.getWidth() * lhs.getHeight() -
                (long) rhs.getWidth() * rhs.getHeight());
    }

}
