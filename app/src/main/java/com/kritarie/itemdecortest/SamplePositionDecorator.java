package com.kritarie.itemdecortest;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Sean on 10/17/2015.
 */
public class SamplePositionDecorator implements ItemDecorator {

    private int mSpacing;
    private Paint mPaint;

    public SamplePositionDecorator(int spacing, Resources res) {
        mSpacing = spacing;
        mPaint = new Paint();
        mPaint.setColor(res.getColor(R.color.colorPrimary));
        mPaint.setStrokeWidth(10);
        mPaint.setStyle(Paint.Style.STROKE);
    }


    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.set(mSpacing, mSpacing, mSpacing, mSpacing);
    }

    @Override
    public void onDrawUnder(@NonNull Canvas canvas, @NonNull Rect decoratedRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        canvas.drawRect(decoratedRect, mPaint);
    }

    @Override
    public void onDrawOver(@NonNull Canvas canvas, @NonNull Rect decoratedRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        canvas.drawLine(decoratedRect.left, decoratedRect.bottom, decoratedRect.right, decoratedRect.top, mPaint);
    }
}
