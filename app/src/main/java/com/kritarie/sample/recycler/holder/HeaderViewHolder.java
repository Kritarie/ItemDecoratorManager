package com.kritarie.sample.recycler.holder;

import android.view.View;
import android.widget.TextView;

import com.kritarie.sample.R;
import com.kritarie.sample.recycler.model.HeaderModel;

/**
 * Created by Sean on 10/25/2015.
 */
public class HeaderViewHolder extends BaseViewHolder<HeaderModel> {

    private TextView mTextView;

    public HeaderViewHolder(View itemView) {
        super(itemView);
        mTextView = (TextView) itemView.findViewById(R.id.header_text);
    }

    @Override
    public void setContent(HeaderModel data) {
        mTextView.setText(data.headerText);
    }
}
