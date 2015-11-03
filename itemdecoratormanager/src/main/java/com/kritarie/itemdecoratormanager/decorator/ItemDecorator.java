package com.kritarie.itemdecoratormanager.decorator;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.kritarie.itemdecoratormanager.ItemDecoratorManager;

/**
 * Implementing classes may be used in the {@link ItemDecoratorManager} to apply
 * item decorations to qualifying {@link RecyclerView} items
 */
public interface ItemDecorator {

    /**
     * Determines whether an implementing class decorates the given {@link RecyclerView.ViewHolder}
     *
     * @param viewHolder holder than the implementing class will check against
     * @param parent parent view for further context, if needed
     * @return true if the class decorates this holder, false otherwise
     */
    boolean decoratesView(@NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView parent);

    /**
     * Called from the {@link ItemDecoratorManager} in its implementation of
     * {@link RecyclerView.ItemDecoration#getItemOffsets(Rect, View, RecyclerView, RecyclerView.State)}
     */
    void getItemOffsets(@NonNull Rect outRect, @NonNull RecyclerView.ViewHolder holder, @NonNull RecyclerView parent, @NonNull RecyclerView.State state);

    /**
     * Called from the {@link ItemDecoratorManager} in its implementation of
     * {@link RecyclerView.ItemDecoration#onDraw(Canvas, RecyclerView, RecyclerView.State)}
     */
    void onDrawUnder(@NonNull Canvas canvas, @NonNull Rect decoratedRect, @NonNull RecyclerView.ViewHolder holder, @NonNull RecyclerView parent, @NonNull RecyclerView.State state);

    /**
     * Called from the {@link ItemDecoratorManager} in its implementation of
     * {@link RecyclerView.ItemDecoration#onDrawOver(Canvas, RecyclerView, RecyclerView.State)}
     */
    void onDrawOver(@NonNull Canvas canvas, @NonNull Rect decoratedRect, @NonNull RecyclerView.ViewHolder holder, @NonNull RecyclerView parent, @NonNull RecyclerView.State state);
}
