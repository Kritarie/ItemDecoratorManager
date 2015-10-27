package com.kritarie.itemdecortest.recycler.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.kritarie.itemdecortest.model.SampleModel;

/**
 * Created by Sean on 10/17/2015.
 */
public abstract class SampleViewHolder<T extends SampleModel> extends RecyclerView.ViewHolder {

    public SampleViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void setContent(T content);
}
