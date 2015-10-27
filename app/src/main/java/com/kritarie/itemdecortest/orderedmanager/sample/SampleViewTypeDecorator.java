package com.kritarie.itemdecortest.orderedmanager.sample;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.kritarie.itemdecortest.orderedmanager.ViewTypeItemDecorator;

import java.util.Collections;

/**
 * Created by Sean on 10/26/2015.
 */
public class SampleViewTypeDecorator extends ViewTypeItemDecorator {

    private static final float ALPHA_SCALAR = 0.8f;

    private Paint mPaint;

    public SampleViewTypeDecorator() {
        super(Collections.singletonList(CardModel.VIEW_TYPE));
        mPaint = new Paint();
        mPaint.setColor(Color.rgb(255, 0, 0));
        mPaint.setStrokeWidth(4);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    public void onDrawUnder(@NonNull Canvas canvas, @NonNull Rect decoratedRect, @NonNull RecyclerView.ViewHolder holder, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        float rectY = decoratedRect.top;
        float totalHeight = parent.getHeight();
        holder.itemView.setAlpha(1 - (rectY / totalHeight) * ALPHA_SCALAR);
        int padding = (int) (100 * (rectY / totalHeight));
        holder.itemView.setRight(parent.getRight() - padding - 32);
        holder.itemView.setLeft(parent.getLeft() + padding + 32);
    }

    @Override
    public void onDrawOver(@NonNull Canvas canvas, @NonNull Rect decoratedRect, @NonNull RecyclerView.ViewHolder holder, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int x = holder.itemView.getLeft();
        int y = holder.itemView.getTop();
        int radius = 15;
        canvas.drawCircle(x, y, radius, mPaint);
    }
}
