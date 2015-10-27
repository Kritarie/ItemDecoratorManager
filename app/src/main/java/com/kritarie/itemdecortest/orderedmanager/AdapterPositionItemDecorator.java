package com.kritarie.itemdecortest.orderedmanager;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import java.util.Collection;
import java.util.HashSet;

/**
 * Abstract implementation of {@link ItemDecorator} which decorates
 * views associated with specific positions in the {@link RecyclerView.Adapter}
 */
public abstract class AdapterPositionItemDecorator extends BaseItemDecorator {

    private HashSet<Integer> mAdapterPositions;

    public AdapterPositionItemDecorator(Collection<Integer> adapterPositions) {
        mAdapterPositions = new HashSet<>(adapterPositions);
    }

    @Override
    public boolean decoratesView(@NonNull RecyclerView.ViewHolder viewHolder) {
        return mAdapterPositions.contains(viewHolder.getAdapterPosition());
    }

}
