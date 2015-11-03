package com.kritarie.sample.recycler;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kritarie.sample.R;
import com.kritarie.sample.recycler.holder.BaseViewHolder;
import com.kritarie.sample.recycler.holder.CardViewHolder;
import com.kritarie.sample.recycler.holder.HeaderViewHolder;
import com.kritarie.sample.recycler.model.CardModel;
import com.kritarie.sample.recycler.model.HeaderModel;
import com.kritarie.sample.recycler.model.Viewable;

import java.util.List;

/**
 * Created by Sean on 10/25/2015.
 */
public class SampleAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private LayoutInflater mLayoutInflater;
    private List<Viewable> mListItems;
    private View.OnClickListener mOnClickListener;

    public SampleAdapter(Activity activity, List<Viewable> items, View.OnClickListener listener) {
        mLayoutInflater = activity.getLayoutInflater();
        mListItems = items;
        mOnClickListener = listener;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case HeaderModel.VIEW_TYPE:
                return new HeaderViewHolder(mLayoutInflater.inflate(R.layout.item_header, parent, false));
            case CardModel.VIEW_TYPE:
                return new CardViewHolder(mLayoutInflater.inflate(R.layout.item_card, parent, false), mOnClickListener);
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

    public void removeItem(int position) {
        mListItems.remove(position);
        notifyItemRemoved(position);
    }
}
