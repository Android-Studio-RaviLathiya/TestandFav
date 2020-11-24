package com.test.tryone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.test.R;
import com.test.service.RestClient;
import com.test.util.Util;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TryOneActivity extends AppCompatActivity {

    RecyclerView rv;
    TryoneAdapter tryoneAdapter;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_try_one);

        rv = findViewById(R.id.rv);
        tv = findViewById(R.id.tv);
        rv.setLayoutManager(new LinearLayoutManager(this));

        tryoneAdapter = new TryoneAdapter(this);

        apicall();

        rv.setAdapter(tryoneAdapter);

    }
Dialog  dialog;
    private void apicall() {

        dialog = Util.startLoader(this);

        new RestClient(this).getInstance().get().tryone().enqueue(new Callback<TryoneModal>() {
            @Override
            public void onResponse(Call<TryoneModal> call, Response<TryoneModal> response) {
                Util.stopLoader(dialog);
                if (response.body() != null) {

                    tryoneAdapter.addAll(response.body().errors);
//                    Toast.makeText(TryOneActivity.this, "" + response.body().included.get(0), Toast.LENGTH_SHORT).show();
                    tv.setText("ok "+response.body().jsonapi.version);

                } else {

//                    Toast.makeText(TryOneActivity.this, "Something wrong", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<TryoneModal> call, Throwable t) {
                Util.stopLoader(dialog);

//                Toast.makeText(TryOneActivity.this, "Error  "  +t, Toast.LENGTH_SHORT).show();
                tv.setText("Error "+t);
                Log.d("EQW",""+t);
            }
        });


    }
}