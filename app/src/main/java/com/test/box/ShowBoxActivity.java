package com.test.box;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.test.R;

import java.util.ArrayList;

public class ShowBoxActivity extends AppCompatActivity {


    ImageView img;
    RecyclerView rv;
    BoxAdapter boxAdapter;
    ArrayList<BoxItem> boxItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_box);

        rv = findViewById(R.id.rv);
        img = findViewById(R.id.img);

//        Bundle bundle = getIntent().getExtras();
//        int s = bundle.getInt("Q");

//        for (int i = 0; i < s; ++i) {
//
//            boxItems.add(new BoxItem("ok" + i));
//
//        }


        if (boxItems.isEmpty()){
            img.setVisibility(View.VISIBLE);
            rv.setVisibility(View.INVISIBLE);
        }else {
            rv.setVisibility(View.VISIBLE);
            img.setVisibility(View.INVISIBLE);

        }

        boxAdapter = new BoxAdapter(boxItems, ShowBoxActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(boxAdapter);


    }
}
