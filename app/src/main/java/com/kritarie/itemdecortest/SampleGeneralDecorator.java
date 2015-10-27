package com.kritarie.itemdecortest;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Sean on 10/21/2015.
 */
public class SampleGeneralDecorator implements ItemDecorator {

    private static final float ALPHA_SCALAR = 0.5f;

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {

    }

    @Override
    public void onDrawUnder(@NonNull Canvas canvas, @NonNull Rect decoratedRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        float rectY = decoratedRect.centerY();
        float parentBottom = parent.getBottom();
        float distance = Math.abs(rectY - parentBottom);
        float totalHeight = parent.getHeight();
        view.setAlpha(1 - (distance/totalHeight)*ALPHA_SCALAR);
    }

    @Override
    public void onDrawOver(@NonNull Canvas canvas, @NonNull Rect decoratedRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {

    }
}
