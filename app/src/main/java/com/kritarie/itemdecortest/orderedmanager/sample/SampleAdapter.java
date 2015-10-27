package com.kritarie.itemdecortest.orderedmanager.sample;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.kritarie.itemdecortest.R;

import java.util.List;

/**
 * Created by Sean on 10/25/2015.
 */
public class SampleAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private LayoutInflater mLayoutInflater;
    private List<Viewable> mListItems;

    public SampleAdapter(Activity activity, List<Viewable> items) {
        mLayoutInflater = activity.getLayoutInflater();
        mListItems = items;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case HeaderModel.VIEW_TYPE:
                return new HeaderViewHolder(mLayoutInflater.inflate(R.layout.item_header, parent, false));
            case CardModel.VIEW_TYPE:
                return new CardViewHolder(mLayoutInflater.inflate(R.layout.item_card, parent, false));
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.setContent(mListItems.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        return mListItems.get(position).getItemViewType();
    }

    @Override
    public int getItemCount() {
        return mListItems.size();
    }
}
