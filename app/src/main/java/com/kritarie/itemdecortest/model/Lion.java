package com.kritarie.itemdecortest.model;

/**
 * Created by Sean on 10/17/2015.
 */
public class Lion extends SampleModel {

    public static final int VIEW_TYPE = 2;

    @Override
    public String getName() {
        return "I'm a lion";
    }

    @Override
    public int getViewType() {
        return VIEW_TYPE;
    }
}
