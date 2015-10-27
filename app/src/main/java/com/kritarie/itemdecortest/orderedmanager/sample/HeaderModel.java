package com.kritarie.itemdecortest.orderedmanager.sample;

/**
 * Created by Sean on 10/25/2015.
 */
public class HeaderModel implements Viewable {

    public static final int VIEW_TYPE = 0;

    public CharSequence headerText;

    public HeaderModel(CharSequence text) {
        headerText = text;
    }

    @Override
    public int getItemViewType() {
        return VIEW_TYPE;
    }
}
