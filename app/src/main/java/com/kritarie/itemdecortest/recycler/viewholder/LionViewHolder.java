package com.kritarie.itemdecortest.recycler.viewholder;

import android.view.View;
import android.widget.TextView;

import com.kritarie.itemdecortest.model.Lion;

/**
 * Created by Sean on 10/17/2015.
 */
public class LionViewHolder extends SampleViewHolder<Lion> {

    public LionViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setContent(Lion content) {
        ((TextView) itemView).setText(content.getName());
    }
}
