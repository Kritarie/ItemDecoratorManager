package com.kritarie.itemdecortest;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Sean on 10/20/2015.
 */
public class SampleViewTypeDecorator implements ItemDecorator {

    private int mSpacing;
    private Paint mPaint;

    public SampleViewTypeDecorator(int spacing, Resources res) {
        mSpacing = spacing;
        mPaint = new Paint();
        mPaint.setStrokeWidth(25);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(res.getColor(R.color.colorAccent));
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.set(0, 0, mSpacing, 0);
    }

    @Override
    public void onDrawUnder(@NonNull Canvas canvas, @NonNull Rect decoratedRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        canvas.drawRect(decoratedRect, mPaint);
    }

    @Override
    public void onDrawOver(@NonNull Canvas canvas, @NonNull Rect decoratedRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        canvas.drawLine(decoratedRect.left, decoratedRect.top, decoratedRect.right, decoratedRect.bottom, mPaint);
    }
}
