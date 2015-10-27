package com.kritarie.itemdecortest.orderedmanager;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Sean on 10/26/2015.
 */
public abstract class BaseItemDecorator implements ItemDecorator {

    @Override
    public boolean decoratesView(@NonNull RecyclerView.ViewHolder viewHolder) {
        return true;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull RecyclerView.ViewHolder holder, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        //NOP
    }

    @Override
    public void onDrawUnder(@NonNull Canvas canvas, @NonNull Rect decoratedRect, @NonNull RecyclerView.ViewHolder holder, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        //NOP
    }

    @Override
    public void onDrawOver(@NonNull Canvas canvas, @NonNull Rect decoratedRect, @NonNull RecyclerView.ViewHolder holder, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        //NOP
    }
}
