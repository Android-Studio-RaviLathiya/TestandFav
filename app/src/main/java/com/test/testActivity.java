package com.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.test.Fav.FaveActivity;
import com.test.service.RestClient;
import com.test.util.Util;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class testActivity extends AppCompatActivity {

    RecyclerView rv;
    RestorentAdapter restorentAdapter;
    SearchView sv;
    Button sp;
    private SwipeRefreshLayout swipeContainer;
    SQLiteDatabase db;
    Dialog dialog;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        db = openOrCreateDatabase("Data.db", MODE_PRIVATE, null);
        db.execSQL("create table if not exists newuser(id integer primary key autoincrement,username text,restaurantname text)");

        init();
        serchview();
        swipeLoader();
        restorent();
        buttonrs();

    }

    private void buttonrs() {

        sp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(testActivity.this,SearchActivity.class);
//                startActivity(intent);

                startActivity(new Intent(testActivity.this, FaveActivity.class));

            }
        });
    }

    private void swipeLoader() {
        // swipe loader
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
//                fetchTimelineAsync(0);
                restorent();

            }
        });

        swipeContainer.setColorSchemeResources(android.R.color.black,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    private void serchview() {
        //searchview
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                restorentAdapter.filter(newText);
                rv.invalidate();

                return false;
            }
        });
    }

    private void init() {
        rv = findViewById(R.id.rv);
        sv = findViewById(R.id.sv);
        sp = findViewById(R.id.sp);
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        rv.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);

        MenuItem search = menu.findItem(R.id.search);
        SearchView sv = (SearchView) search.getActionView();

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                restorentAdapter.filter(newText);
                rv.invalidate();
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);

    }

    private void restorent() {

//        dialog = Util.startLoader(this);
        progressDialog = Util.pogressbar(this);
        new RestClient(this).getInstance().get().restorent().enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
//                Util.stopLoader(dialog);
                Util.stoppogress(progressDialog);


                if (response.body() != null) {


                    if (response.body().data != null) {
                        Util.toast(testActivity.this,"ok");

//                        shareprefrence.setrestorentlist((ArrayList<DataModel.data>) response.body().data);
                        restorentAdapter = new RestorentAdapter(response.body().data, testActivity.this);
                        rv.setAdapter(restorentAdapter);
                        swipeContainer.setRefreshing(false);

                    } else {


                    }

                } else {


                    Toast.makeText(testActivity.this, "Something Wrong...", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {
//                Util.stopLoader(dialog);

//                Toast.makeText(testActivity.this, "Error" + t, Toast.LENGTH_SHORT).show();
//                Log.e("qq", "" + t);

            }
        });


    }
}
