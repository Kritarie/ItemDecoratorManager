package com.kritarie.itemdecortest;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sean on 10/17/2015.
 */
public class ItemDecoratorManager extends RecyclerView.ItemDecoration {

    /**
     * ItemDecorators associated with specific ViewTypes
     */
    private SparseArray<List<ItemDecorator>> mViewTypeItemDecorators;

    /**
     * ItemDecorators associated with specific positions
     */
    private SparseArray<List<ItemDecorator>> mPositionItemDecorators;

    /**
     * ItemDecorators that are applied to all items
     */
    private List<ItemDecorator> mGeneralItemDecorators;

    /**
     * Contains the cumulative changes of each decorator call,
     * used as an internal buffer for item offsets
     */
    private Rect mOffsetBufferRect = new Rect();

    /**
     * Contains the coordinates for each edge of the views taking into
     * account offsets from decorations
     */
    private Rect mViewBufferRect = new Rect();

    protected ItemDecoratorManager(Builder builder) {
        mViewTypeItemDecorators = builder.getViewTypeItemDecorators();
        mPositionItemDecorators = builder.getPositionItemDecorators();
        mGeneralItemDecorators = builder.getGeneralItemDecorators();
    }

    static class Builder {

        private SparseArray<List<ItemDecorator>> mViewTypeItemDecorators = new SparseArray<>();
        private SparseArray<List<ItemDecorator>> mPositionItemDecorators = new SparseArray<>();
        private List<ItemDecorator> mGeneralItemDecorators = new ArrayList<>();

        public Builder(){}

        /**
         * Add an item decorator to be used for the specified view types
         *
         * @param decorator to be added
         * @param viewTypes to receive the decoration
         */
        public Builder withViewTypeDecorator(ItemDecorator decorator, int... viewTypes) {
            for (int viewType : viewTypes) {
                List<ItemDecorator> decorators = mViewTypeItemDecorators.get(viewType);
                if (decorators == null) decorators = new ArrayList<>(1);
                decorators.add(decorator);
                mViewTypeItemDecorators.append(viewType, decorators);
            }
            return this;
        }

        /**
         * Add an item decorator to be used for the specified
         *  {@link RecyclerView.ViewHolder} adapter positions
         *
         * @param decorator to be added
         * @param positions where the decorator should apply
         */
        public Builder withPositionDecorator(ItemDecorator decorator, int... positions) {
            for (int position : positions) {
                List<ItemDecorator> decorators = mPositionItemDecorators.get(position);
                if (decorators == null) decorators = new ArrayList<>(1);
                decorators.add(decorator);
                mPositionItemDecorators.append(position, decorators);
            }
            return this;
        }

        /**
         * Add an item decorator to be used for all items
         *
         * @param decorator to be added
         */
        public Builder withGeneralDecorator(ItemDecorator decorator) {
            mGeneralItemDecorators.add(decorator);
            return this;
        }

        private SparseArray<List<ItemDecorator>> getViewTypeItemDecorators() {
            return mViewTypeItemDecorators;
        }

        private SparseArray<List<ItemDecorator>> getPositionItemDecorators() {
            return mPositionItemDecorators;
        }

        private List<ItemDecorator> getGeneralItemDecorators() {
            return mGeneralItemDecorators;
        }

        public ItemDecoratorManager build() {
            return new ItemDecoratorManager(this);
        }
    }

    /** Apply item offsets to a child {@link View} of the {@link RecyclerView} in the
     * following order: all view type decorators in the order they were added, all position
     * decorators in the order they were added, and all general decorators in the order they
     * were added
     *
     * @param outRect bounds of the child {@link View}
     * @param view the child {@link View} that the offsets will be applied to
     * @param parent the parent {@link RecyclerView} that contains the item being decorated
     * @param state the {@link RecyclerView.State} of the parent
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        RecyclerView.ViewHolder viewHolder = parent.getChildViewHolder(view);

        List<ItemDecorator> viewTypeDecorators = mViewTypeItemDecorators.get(viewHolder.getItemViewType());
        if (viewTypeDecorators != null) {
            for (int i = 0; i < viewTypeDecorators.size(); i++) {
                resetRect();
                viewTypeDecorators.get(i).getItemOffsets(mOffsetBufferRect, view, parent, state);
                dispatchRectOffset(outRect);
            }
        }

        List<ItemDecorator> positionDecorators = mPositionItemDecorators.get(viewHolder.getAdapterPosition());
        if (positionDecorators != null) {
            for (int i = 0; i < positionDecorators.size(); i++) {
                resetRect();
                positionDecorators.get(i).getItemOffsets(mOffsetBufferRect, view, parent, state);
                dispatchRectOffset(outRect);
            }
        }

        for (int i = 0; i < mGeneralItemDecorators.size(); i++) {
            resetRect();
            mGeneralItemDecorators.get(i).getItemOffsets(mOffsetBufferRect, view, parent, state);
            dispatchRectOffset(outRect);
        }
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
     *
     *
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        for (int viewIndex = 0; viewIndex < parent.getChildCount(); viewIndex++) {

            View view = parent.getChildAt(viewIndex);
            RecyclerView.ViewHolder viewHolder = parent.getChildViewHolder(view);

            List<ItemDecorator> viewTypeDecorators = mViewTypeItemDecorators.get(viewHolder.getItemViewType());
            if (viewTypeDecorators != null && !viewTypeDecorators.isEmpty()) {
                for (int i = 0; i < viewTypeDecorators.size(); i++) {
                    updateDecoratedRectForView(layoutManager, view);
                    viewTypeDecorators.get(i).onDrawUnder(c, mViewBufferRect, view, parent, state);
                }
            }

            List<ItemDecorator> positionDecorators = mPositionItemDecorators.get(viewHolder.getAdapterPosition());
            if (positionDecorators != null && !positionDecorators.isEmpty()) {
                for (int i = 0; i < positionDecorators.size(); i++) {
                    updateDecoratedRectForView(layoutManager, view);
                    positionDecorators.get(i).onDrawUnder(c, mViewBufferRect, view, parent, state);
                }
            }

            for (int i = 0; i < mGeneralItemDecorators.size(); i++) {
                updateDecoratedRectForView(layoutManager, view);
                mGeneralItemDecorators.get(i).onDrawUnder(c, mViewBufferRect, view, parent, state);
            }
        }
    }

    /**
     *
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        for (int viewIndex = 0; viewIndex < parent.getChildCount(); viewIndex++) {

            View view = parent.getChildAt(viewIndex);
            RecyclerView.ViewHolder viewHolder = parent.getChildViewHolder(view);

            List<ItemDecorator> viewTypeDecorators = mViewTypeItemDecorators.get(viewHolder.getItemViewType());
            if (viewTypeDecorators != null && !viewTypeDecorators.isEmpty()) {
                for (int i = 0; i < viewTypeDecorators.size(); i++) {
                    updateDecoratedRectForView(layoutManager, view);
                    viewTypeDecorators.get(i).onDrawOver(c, mViewBufferRect, view, parent, state);
                }
            }

            List<ItemDecorator> positionDecorators = mPositionItemDecorators.get(viewHolder.getAdapterPosition());
            if (positionDecorators != null && !positionDecorators.isEmpty()) {
                for (int i = 0; i < positionDecorators.size(); i++) {
                    updateDecoratedRectForView(layoutManager, view);
                    positionDecorators.get(i).onDrawOver(c, mViewBufferRect, view, parent, state);
                }
            }

            for (int i = 0; i < mGeneralItemDecorators.size(); i++) {
                updateDecoratedRectForView(layoutManager, view);
                mGeneralItemDecorators.get(i).onDrawOver(c, mViewBufferRect, view, parent, state);
            }
        }
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
