package com.kritarie.itemdecortest.orderedmanager.sample;

/**
 * Created by Sean on 10/25/2015.
 */
public class CardModel implements Viewable {

    public static final int VIEW_TYPE = 1;

    public CharSequence cardText;

    public CardModel(CharSequence text) {
        cardText = text;
    }

    @Override
    public int getItemViewType() {
        return VIEW_TYPE;
    }
}
