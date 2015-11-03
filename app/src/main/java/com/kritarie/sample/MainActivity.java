package com.kritarie.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.kritarie.itemdecoratormanager.ItemDecoratorManager;
import com.kritarie.sample.recycler.StickyFooterDecorator;
import com.kritarie.sample.recycler.model.CardModel;
import com.kritarie.sample.recycler.model.HeaderModel;
import com.kritarie.sample.recycler.SampleAdapter;
import com.kritarie.sample.recycler.SampleAdapterPositionDecorator;
import com.kritarie.sample.recycler.SampleDefaultItemDecorator;
import com.kritarie.sample.recycler.SampleViewTypeDecorator;
import com.kritarie.sample.recycler.model.Viewable;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        ItemDecoratorManager manager = new ItemDecoratorManager.Builder()
                .withItemDecorator(new SampleDefaultItemDecorator(40))
                .withItemDecorator(new SampleAdapterPositionDecorator())
                .withItemDecorator(new SampleViewTypeDecorator())
                .withItemDecorator(new StickyFooterDecorator(this, R.drawable.bg_colorblocks))
                .build();
        mRecyclerView.addItemDecoration(manager);

        ArrayList<Viewable> list = new ArrayList<>(10);
        list.add(new HeaderModel("Hello, Sean"));
        list.add(new CardModel("Don't forget to bring a towel"));
        list.add(new CardModel("Don't forget to bring a towel"));
        list.add(new CardModel("Don't forget to bring a towel"));
        list.add(new CardModel("Don't forget to bring a towel"));
        list.add(new CardModel("Don't forget to bring a towel"));
        list.add(new CardModel("Don't forget to bring a towel"));

        mRecyclerView.setAdapter(new SampleAdapter(this, list, this));
    }

    @Override
    public void onClick(View v) {
        SampleAdapter adapter = (SampleAdapter) mRecyclerView.getAdapter();
        adapter.removeItem(mRecyclerView.getChildViewHolder(v).getAdapterPosition());
    }
}
