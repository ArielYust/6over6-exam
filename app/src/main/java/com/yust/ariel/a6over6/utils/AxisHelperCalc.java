package com.yust.ariel.a6over6.utils;

/**
 * Created by Ariel Yust on 11-Mar-17.
 */

public class AxisHelperCalc {
    final float full;
    final float half;
    final float textFix;
    final float usual;

    public AxisHelperCalc(float full, float textFix) {
        this.full = full;
        this.half = full / 2f;
        this.textFix = textFix;
        this.usual = half - textFix;
    }

    public float calc(int value) {
        final float x = ((float) value / 360f);
        if (value == 0) return usual;
        else if (value > 180 && value <= 360) return (usual + (full * (1f - x)));
        else if (value > 0 && value <= 180) return (usual - (full * x));

        return usual;
    }
}
