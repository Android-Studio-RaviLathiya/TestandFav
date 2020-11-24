package com.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class ImgActivity extends AppCompatActivity {


    ImageView img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img);

        img = findViewById(R.id.img);

        Bundle bundle = getIntent().getExtras();
        String s = bundle.getString("img");

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.ic_autorenew_black_24dp);

        Glide.with(this)
                .load(s)
                .apply(requestOptions)
                .into(img);




    }
}