package com.kritarie.sample.recycler;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.kritarie.itemdecoratormanager.decorator.AdapterPositionItemDecorator;

import java.util.Collections;

/**
 * Created by Sean on 10/22/2015.
 */
public class SampleAdapterPositionDecorator extends AdapterPositionItemDecorator {

    private Paint mPaint;

    public SampleAdapterPositionDecorator() {
        super(Collections.singletonList(1));
        mPaint = new Paint();
        mPaint.setColor(Color.rgb(150, 150, 250));
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    public void onDrawUnder(@NonNull Canvas canvas, @NonNull Rect decoratedRect, @NonNull RecyclerView.ViewHolder holder, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        View header = parent.getChildAt(holder.getAdapterPosition() - 1);
        float left = parent.getLeft();
        float top = parent.getLayoutManager().getDecoratedTop(header);
        float right = parent.getRight();
        float bottom = decoratedRect.bottom - 100;
        canvas.drawRect(left, top, right, bottom, mPaint);
    }
}
