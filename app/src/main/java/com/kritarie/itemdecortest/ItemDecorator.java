package com.kritarie.itemdecortest;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Sean on 10/17/2015.
 */
public interface ItemDecorator {

    /**
     * Called from the {@link ItemDecoratorManager} in its implementation of {@link RecyclerView.ItemDecoration#getItemOffsets(Rect, View, RecyclerView, RecyclerView.State)}
     *
     * @param outRect
     * @param view
     * @param parent
     * @param state
     */
    void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state);

    /**
     * Called from the {@link ItemDecoratorManager} in its implementation of {@link RecyclerView.ItemDecoration#onDraw(Canvas, RecyclerView, RecyclerView.State)}
     *
     * @param canvas
     * @param decoratedRect
     * @param view
     * @param parent
     * @param state
     */
    void onDrawUnder(@NonNull Canvas canvas, @NonNull Rect decoratedRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state);

    /**
     * Called from the {@link ItemDecoratorManager} in its implementation of {@link RecyclerView.ItemDecoration#onDrawOver(Canvas, RecyclerView, RecyclerView.State)}
     *
     * @param canvas
     * @param decoratedRect
     * @param view
     * @param parent
     * @param state
     */
    void onDrawOver(@NonNull Canvas canvas, @NonNull Rect decoratedRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state);
}
