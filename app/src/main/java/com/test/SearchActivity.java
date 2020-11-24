package com.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.test.service.RestClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    RecyclerView rv;
    SearchView sv;
    RestorentAdapter restorentAdapter;
    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        rv = findViewById(R.id.rv);
        sv = findViewById(R.id.sv);
        tv = findViewById(R.id.tv);

        rv.setLayoutManager(new LinearLayoutManager(this));

        if(!sv.isFocused()) {
            sv.clearFocus();
        }

        //searchview
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query)   {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if (newText.isEmpty()){
                    tv.setVisibility(View.VISIBLE);
                    rv.setVisibility(View.GONE);
                }else {
                    tv.setVisibility(View.GONE);
                    rv.setVisibility(View.VISIBLE);
                }


                restorentAdapter.filter(newText);

                rv.invalidate();

                return false;
            }
        });

        sv.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restorent();
                tv.setVisibility(View.GONE);
                rv.setVisibility(View.VISIBLE);

            }
        });


        sv.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                restorentAdapter.clearAll();
                tv.setVisibility(View.VISIBLE);
                rv.setVisibility(View.GONE);
                return false;
            }
        });


    }

    private void restorent() {

        new RestClient(this).getInstance().get().restorent().enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {


                if (response.body() != null) {


                    if (response.body().data != null) {

                        restorentAdapter = new RestorentAdapter(response.body().data,SearchActivity.this);
                        rv.setAdapter(restorentAdapter);

                    } else {


                    }

                } else {


                    Toast.makeText(SearchActivity.this, "Something Wrong...", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {


            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search,menu);

        MenuItem search=menu.findItem(R.id.search);
        SearchView sv= (SearchView) search.getActionView();

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
}