package com.kritarie.itemdecortest.orderedmanager.sample;

import android.view.View;
import android.widget.TextView;

import com.kritarie.itemdecortest.R;

/**
 * Created by Sean on 10/25/2015.
 */
public class CardViewHolder extends BaseViewHolder<CardModel> {

    private TextView mTextView;

    public CardViewHolder(View itemView) {
        super(itemView);
        mTextView = (TextView) itemView.findViewById(R.id.card_text);
    }

    @Override
    public void setContent(CardModel data) {
        mTextView.setText(data.cardText);
    }
}
