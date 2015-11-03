package com.kritarie.itemdecoratormanager.decorator;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import java.util.Collection;
import java.util.HashSet;

/**
 * Abstract implementation of {@link ItemDecorator} which decorates
 * views associated with specific view types in the {@link RecyclerView.Adapter}
 */
public abstract class ViewTypeItemDecorator extends BaseItemDecorator {

    private HashSet<Integer> mViewTypes;

    public ViewTypeItemDecorator(Collection<Integer> viewTypes) {
        mViewTypes = new HashSet<>(viewTypes);
    }

    @Override
    public boolean decoratesView(@NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView parent) {
        return mViewTypes.contains(viewHolder.getItemViewType());
    }
}
