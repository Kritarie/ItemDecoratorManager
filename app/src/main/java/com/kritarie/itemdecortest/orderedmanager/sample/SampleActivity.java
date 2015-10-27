package com.kritarie.itemdecortest.orderedmanager.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kritarie.itemdecortest.R;
import com.kritarie.itemdecortest.orderedmanager.ItemDecoratorManager;

import java.util.ArrayList;

public class SampleActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        ItemDecoratorManager manager = new ItemDecoratorManager.Builder()
                .withItemDecorator(new SampleDefaultItemDecorator(40))
                .withItemDecorator(new SampleAdapterPositionDecorator())
                .withItemDecorator(new SampleViewTypeDecorator())
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
        list.add(new CardModel("Don't forget to bring a towel"));
        list.add(new CardModel("Don't forget to bring a towel"));
        list.add(new CardModel("Don't forget to bring a towel"));

        mRecyclerView.setAdapter(new SampleAdapter(this, list));
    }
}
