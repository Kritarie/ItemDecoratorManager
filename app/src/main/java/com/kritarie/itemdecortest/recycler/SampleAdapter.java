package com.kritarie.itemdecortest.recycler;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.kritarie.itemdecortest.R;
import com.kritarie.itemdecortest.model.Cat;
import com.kritarie.itemdecortest.model.Dog;
import com.kritarie.itemdecortest.model.Lion;
import com.kritarie.itemdecortest.model.SampleModel;
import com.kritarie.itemdecortest.recycler.viewholder.CatViewHolder;
import com.kritarie.itemdecortest.recycler.viewholder.DogViewHolder;
import com.kritarie.itemdecortest.recycler.viewholder.LionViewHolder;
import com.kritarie.itemdecortest.recycler.viewholder.SampleViewHolder;

import java.util.Collections;
import java.util.List;

/**
 * Created by Sean on 10/17/2015.
 */
public class SampleAdapter extends RecyclerView.Adapter<SampleViewHolder> {

    private LayoutInflater mLayoutInflater;
    private List<SampleModel> mData = Collections.emptyList();

    public SampleAdapter(Activity activity) {
        mLayoutInflater = activity.getLayoutInflater();
    }

    @Override
    public SampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case Cat.VIEW_TYPE:
                return new CatViewHolder(mLayoutInflater.inflate(R.layout.item_sample, parent, false));
            case Dog.VIEW_TYPE:
                return new DogViewHolder(mLayoutInflater.inflate(R.layout.item_sample, parent, false));
            case Lion.VIEW_TYPE:
                return new LionViewHolder(mLayoutInflater.inflate(R.layout.item_sample, parent, false));
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(SampleViewHolder holder, int position) {
        holder.setContent(mData.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        return mData.get(position).getViewType();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(List<SampleModel> data) {
        mData = data;
        notifyDataSetChanged();
    }
}
