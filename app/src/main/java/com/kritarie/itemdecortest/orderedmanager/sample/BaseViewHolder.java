package com.kritarie.itemdecortest.orderedmanager.sample;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Sean on 10/25/2015.
 */
public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void setContent(T data);
}
