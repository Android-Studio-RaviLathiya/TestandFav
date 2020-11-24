package com.test.box;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.test.R;

import java.util.ArrayList;

public class BoxActivity extends AppCompatActivity {

    EditText et;
    Button btn;
    String inputnumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_box);

        et = findViewById(R.id.et);
        btn = findViewById(R.id.btn);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputnumber = et.getText().toString();

                Intent intent = new Intent(BoxActivity.this,ShowBoxActivity.class);
                intent.putExtra("Q",Integer.parseInt(inputnumber));
                startActivity(intent);

            }
        });
//        boxItems.add(new BoxItem("ok"));
//        boxItems.add(new BoxItem("ok"));
//        boxItems.add(new BoxItem("ok"));
//        boxItems.add(new BoxItem("ok"));


    }

    public void ok(View view) {
//        startActivity(new Intent(BoxActivity.this,ShowBoxActivity.class));



        Intent intent = new Intent(this, ShowBoxActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_right);
    }
}
