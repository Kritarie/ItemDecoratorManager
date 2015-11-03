package com.kritarie.itemdecoratormanager;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.kritarie.itemdecoratormanager.decorator.ItemDecorator;
import com.kritarie.itemdecoratormanager.log.DecoratorLog;

import java.util.ArrayList;

/**
 * Created by Sean on 10/21/2015.
 */
public final class ItemDecoratorManager extends RecyclerView.ItemDecoration {

    private static final String TAG = "ItemDecoratorManager";

    /**
     * Optional logging mechanism called before and after decoration work
     */
    @Nullable
    private DecoratorLog mDecoratorLog;

    /**
     * List of {@link ItemDecorator} in the order they will
     * be applied to the {@link RecyclerView} items
     */
    @NonNull
    private ArrayList<ItemDecorator> mItemDecorators;

    /**
     * Contains the cumulative changes of each decorator call,
     * used as an internal buffer for item offsets
     */
    @NonNull
    private Rect mOffsetBufferRect;

    /**
     * Contains the coordinates for each edge of the views taking into
     * account offsets from decorations
     */
    @NonNull
    private Rect mViewBufferRect;

    /**
     * Internally construct a new {@link ItemDecoratorManager}
     * from an instance of {@link Builder}
     *
     * @param builder construction source
     */
    private ItemDecoratorManager(Builder builder) {
        mDecoratorLog = builder.getDecoratorLog();
        mItemDecorators = builder.getItemDecorators();
        mOffsetBufferRect = new Rect();
        mViewBufferRect = new Rect();
    }

    /**
     * Builder class allows method chaining to construct desired ItemDecoratorManager
     */
    public static class Builder {
        private DecoratorLog mDecoratorLog;
        private ArrayList<ItemDecorator> mItemDecorators;

        public Builder() {
            mItemDecorators = new ArrayList<>(1);
        }

        public Builder setLog(DecoratorLog log) {
            mDecoratorLog = log;
            return this;
        }

        public Builder withItemDecorator(ItemDecorator decorator) {
            mItemDecorators.add(decorator);
            return this;
        }

        public Builder withItemDecorator(int index, ItemDecorator decorator) {
            mItemDecorators.add(index, decorator);
            return this;
        }

        public ItemDecoratorManager build() {
            return new ItemDecoratorManager(this);
        }

        private DecoratorLog getDecoratorLog() {
            return mDecoratorLog;
        }

        private ArrayList<ItemDecorator> getItemDecorators() {
            return mItemDecorators;
        }
    }

    /**
     * Apply item offsets to a child {@link View} of the {@link RecyclerView} in the
     * order that the item decorators appear in the internal list (specified via the {@link Builder})
     *
     * @param outRect bounds of the child {@link View}
     * @param view the child {@link View} that the offsets will be applied to
     * @param parent the parent {@link RecyclerView} that contains the item being decorated
     * @param state the {@link RecyclerView.State} of the parent
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (mDecoratorLog != null) mDecoratorLog.logItemOffsetStart();
        RecyclerView.ViewHolder viewHolder = parent.getChildViewHolder(view);
        for (int decoratorIndex = 0; decoratorIndex < mItemDecorators.size(); decoratorIndex++) {
            ItemDecorator decorator = mItemDecorators.get(decoratorIndex);
            if (decorator.decoratesView(viewHolder, parent)) {
                resetRect();
                decorator.getItemOffsets(mOffsetBufferRect, viewHolder, parent, state);
                dispatchRectOffset(outRect);
            }
        }
        if (mDecoratorLog != null) mDecoratorLog.logItemOffsetEnd();
    }

    /**
     * Reset the temporary rect in order to receive
     * new changes from a decorator
     */
    private void resetRect() {
        mOffsetBufferRect.set(0, 0, 0, 0);
    }

    /**
     * Update the outgoing {@link Rect} with the
     * cumulative decorator changes
     *
     * @param rect to update
     */
    private void dispatchRectOffset(Rect rect) {
        rect.left += mOffsetBufferRect.left;
        rect.right += mOffsetBufferRect.right;
        rect.bottom += mOffsetBufferRect.bottom;
        rect.top += mOffsetBufferRect.top;
    }

    /**
     * Called by the {@link RecyclerView} before items are laid out. Any drawing to the canvas
     * will be drawn behind the item. This implementation iterates over all child views of the
     * parent {@link RecyclerView}, and for each {@link View}, apply all decorators to it
     *
     * @param c Canvas to draw
     * @param parent the parent {@link RecyclerView}
     * @param state state of the given {@link RecyclerView}
     */
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (mDecoratorLog != null) mDecoratorLog.logDrawOverStart();
        int childCount = parent.getChildCount();
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        for (int childIndex = 0; childIndex < childCount; childIndex++) {
            View childView = parent.getChildAt(childIndex);
            RecyclerView.ViewHolder viewHolder = parent.getChildViewHolder(childView);
            updateDecoratedRectForView(layoutManager, viewHolder.itemView);
            for (int decoratorIndex = 0; decoratorIndex < mItemDecorators.size(); decoratorIndex++) {
                ItemDecorator decorator = mItemDecorators.get(decoratorIndex);
                if (decorator.decoratesView(viewHolder, parent)) {
                    decorator.onDrawUnder(c, mViewBufferRect, viewHolder, parent, state);
                }
            }
        }
        if (mDecoratorLog != null) mDecoratorLog.logDrawOverEnd();
    }

    /**
     * Called by the {@link RecyclerView} after items are laid out. Any drawing to the canvas
     * will be drawn on top of the item. This implementation iterates over all child views of the
     * parent {@link RecyclerView}, and for each {@link View}, apply all decorators to it
     *
     * @param c Canvas to draw
     * @param parent the parent {@link RecyclerView}
     * @param state state of the given {@link RecyclerView}
     */
    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (mDecoratorLog != null) mDecoratorLog.logDrawOverStart();
        int childCount = parent.getChildCount();
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        for (int childIndex = 0; childIndex < childCount; childIndex++) {
            View childView = parent.getChildAt(childIndex);
            RecyclerView.ViewHolder viewHolder = parent.getChildViewHolder(childView);
            updateDecoratedRectForView(layoutManager, viewHolder.itemView);
            for (int decoratorIndex = 0; decoratorIndex < mItemDecorators.size(); decoratorIndex++) {
                ItemDecorator decorator = mItemDecorators.get(decoratorIndex);
                if (decorator.decoratesView(viewHolder, parent)) {
                    decorator.onDrawOver(c, mViewBufferRect, viewHolder, parent, state);
                }
            }
        }
        if (mDecoratorLog != null) mDecoratorLog.logDrawOverEnd();
    }

    /**
     * Stash the decorated view edges in the buffer rect
     *
     * @param layoutManager for retrieving the decorated edges of the given view
     * @param view child view whose edges are to be stashed
     */
    private void updateDecoratedRectForView(RecyclerView.LayoutManager layoutManager, View view) {
        mViewBufferRect.left = layoutManager.getDecoratedLeft(view);
        mViewBufferRect.right = layoutManager.getDecoratedRight(view);
        mViewBufferRect.bottom = layoutManager.getDecoratedBottom(view);
        mViewBufferRect.top = layoutManager.getDecoratedTop(view);
    }
}
