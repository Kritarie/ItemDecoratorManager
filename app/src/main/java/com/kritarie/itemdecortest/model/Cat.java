package com.kritarie.itemdecortest.model;

/**
 * Created by Sean on 10/17/2015.
 */
public class Cat extends SampleModel {

    public static final int VIEW_TYPE = 0;

    @Override
    public String getName() {
        return "I'm a cat";
    }

    @Override
    public int getViewType() {
        return VIEW_TYPE;
    }
}
