package com.kritarie.sample.recycler.holder;

import android.view.View;
import android.widget.TextView;

import com.kritarie.sample.R;
import com.kritarie.sample.recycler.model.CardModel;

/**
 * Created by Sean on 10/25/2015.
 */
public class CardViewHolder extends BaseViewHolder<CardModel> {

    private View.OnClickListener mOnClickListener;
    private TextView mTextView;

    public CardViewHolder(View itemView, View.OnClickListener listener) {
        super(itemView);
        mOnClickListener = listener;
        mTextView = (TextView) itemView.findViewById(R.id.card_text);
    }

    @Override
    public void setContent(CardModel data) {
        mTextView.setText(data.cardText);
        itemView.setOnClickListener(mOnClickListener);
    }
}
