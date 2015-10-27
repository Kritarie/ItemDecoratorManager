package com.kritarie.itemdecortest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kritarie.itemdecortest.model.Cat;
import com.kritarie.itemdecortest.model.Dog;
import com.kritarie.itemdecortest.model.Lion;
import com.kritarie.itemdecortest.model.SampleModel;
import com.kritarie.itemdecortest.recycler.SampleAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(
                new ItemDecoratorManager.Builder()
                        .withPositionDecorator(new SamplePositionDecorator(20, getResources()), 2)
                        .withViewTypeDecorator(new SampleViewTypeDecorator(30, getResources()), Lion.VIEW_TYPE)
                        .withGeneralDecorator(new SampleGeneralDecorator())
                        .build());
        mRecyclerView.setAdapter(new SampleAdapter(this));

        List<SampleModel> models = new ArrayList<>(10);
        models.add(new Cat());
        models.add(new Dog());
        models.add(new Lion());
        models.add(new Cat());
        models.add(new Dog());
        models.add(new Lion());
        models.add(new Cat());
        models.add(new Dog());
        models.add(new Lion());
        ((SampleAdapter) mRecyclerView.getAdapter()).setData(models);
    }
}
