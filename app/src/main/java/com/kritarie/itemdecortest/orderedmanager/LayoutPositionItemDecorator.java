package com.kritarie.itemdecortest.orderedmanager;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import java.util.Collection;
import java.util.HashSet;

/**
 * Abstract implementation of {@link ItemDecorator} which decorates
 * views associated with specific layout positions in the {@link RecyclerView}
 */
public abstract class LayoutPositionItemDecorator extends BaseItemDecorator {

    private HashSet<Integer> mLayoutPositions;

    public LayoutPositionItemDecorator(Collection<Integer> layoutPositions) {
        mLayoutPositions = new HashSet<>(layoutPositions);
    }

    @Override
    public boolean decoratesView(@NonNull RecyclerView.ViewHolder viewHolder) {
        return mLayoutPositions.contains(viewHolder.getLayoutPosition());
    }
}
