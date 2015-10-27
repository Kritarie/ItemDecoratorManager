package com.kritarie.itemdecortest.orderedmanager.sample;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.kritarie.itemdecortest.orderedmanager.BaseItemDecorator;

/**
 * Created by Sean on 10/26/2015.
 */
public class SampleDefaultItemDecorator extends BaseItemDecorator {

    private int mSpacing;

    public SampleDefaultItemDecorator(int spacing) {
        mSpacing = spacing;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull RecyclerView.ViewHolder holder, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.top = mSpacing;
        outRect.bottom = mSpacing/2;
        outRect.left = mSpacing;
        outRect.right = mSpacing;
    }
}
