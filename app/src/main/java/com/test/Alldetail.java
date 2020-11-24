package com.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class Alldetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alldetail);

        Bundle bundle = getIntent().getExtras();
        String s = bundle.getString("q");
        Toast.makeText(this, "" + s, Toast.LENGTH_SHORT).show();

    }
}
