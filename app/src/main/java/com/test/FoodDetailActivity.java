package com.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.io.Serializable;
import java.util.List;

public class FoodDetailActivity extends AppCompatActivity {

    RecyclerView rv;
    FoodAdapter foodAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        rv = findViewById(R.id.rv);

        Bundle bundle = getIntent().getExtras();
        Serializable s = bundle.getSerializable("q");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(linearLayoutManager);

        foodAdapter = new FoodAdapter(this);
        foodAdapter.addAll((List<DataModel.data.food_detail>) s);
        rv.setAdapter(foodAdapter);


    }
}
