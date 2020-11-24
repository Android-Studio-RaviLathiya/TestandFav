package com.test.Fav;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.test.R;

import java.util.ArrayList;

public class FaveActivity extends AppCompatActivity {

    private RecyclerView rv;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fave);

        db = openOrCreateDatabase("Data.db", MODE_PRIVATE, null);


        initView();
        allresposnceinshareprefrence();
        recyclview();


    }

    ArrayList<FavItem> favItems = new ArrayList<>();


    private void recyclview() {

        rv.setLayoutManager(new LinearLayoutManager(this));
        favItems.clear();
        Cursor c = db.rawQuery("select * from newuser", null);

        if (c != null) {

            while (c.moveToNext()) {

                FavItem favItem = new FavItem(c.getString(0), c.getString(1),c.getString(2));
                favItems.add(favItem);
            }

            if (favItems.size() > 0) {

                FavrAdepter favrAdepter = new FavrAdepter(favItems, FaveActivity.this);
                rv.setAdapter(favrAdepter);

            } else {
                Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
            }


        }

    }

    private void allresposnceinshareprefrence() {
//        String s = String.valueOf(shareprefrence.getrestorentlist());
//        Toast.makeText(this, " " + s, Toast.LENGTH_SHORT).show();
    }

    private void initView() {
        rv = findViewById(R.id.rv);
    }
}