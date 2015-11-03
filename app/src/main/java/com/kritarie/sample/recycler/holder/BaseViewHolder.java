package com.kritarie.sample.recycler.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.kritarie.sample.recycler.model.Viewable;

/**
 * Created by Sean on 10/25/2015.
 */
public abstract class BaseViewHolder<T extends Viewable> extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void setContent(T data);
}
