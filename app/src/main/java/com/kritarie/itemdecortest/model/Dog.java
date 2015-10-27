package com.kritarie.itemdecortest.model;

/**
 * Created by Sean on 10/17/2015.
 */
public class Dog extends SampleModel {

    public static final int VIEW_TYPE = 1;

    @Override
    public String getName() {
        return "I'm a dog";
    }

    @Override
    public int getViewType() {
        return VIEW_TYPE;
    }
}
