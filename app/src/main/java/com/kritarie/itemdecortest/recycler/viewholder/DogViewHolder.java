package com.kritarie.itemdecortest.recycler.viewholder;

import android.view.View;
import android.widget.TextView;

import com.kritarie.itemdecortest.model.Dog;

/**
 * Created by Sean on 10/17/2015.
 */
public class DogViewHolder extends SampleViewHolder<Dog> {

    public DogViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setContent(Dog content) {
        ((TextView) itemView).setText(content.getName());
    }
}
