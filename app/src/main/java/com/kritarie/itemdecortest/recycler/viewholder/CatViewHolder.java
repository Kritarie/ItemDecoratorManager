package com.kritarie.itemdecortest.recycler.viewholder;

import android.view.View;
import android.widget.TextView;

import com.kritarie.itemdecortest.model.Cat;

/**
 * Created by Sean on 10/17/2015.
 */
public class CatViewHolder extends SampleViewHolder<Cat> {

    public CatViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setContent(Cat content) {
        ((TextView) itemView).setText(content.getName());
    }

}
