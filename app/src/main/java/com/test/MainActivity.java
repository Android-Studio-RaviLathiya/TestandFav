package com.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.test.box.BoxAdapter;

import java.io.StringWriter;

public class MainActivity extends AppCompatActivity {

    TextView output;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        output = findViewById(R.id.output);

        StringWriter stringWriter = new StringWriter();
        int start = 1;
        int end = 9;
        int pivot = 5;

        for (int i = start; i <= end; i++) {
            if (pivot <= 5 && pivot > 0) {
                stringWriter.append(String.valueOf(pivot));
                stringWriter.append(" ");
                pivot = pivot - 1;
            } else {
                stringWriter.append(String.valueOf(i));
                stringWriter.append(" ");
            }

        }
//        output.setText(stringWriter.toString());


//        extra();

//        name();

//        star();
    }

    private void star() {
        int n = 5;
        StringWriter stringWriter = new StringWriter();

        for (int i = 0; i < n; i++) {

            for (int j = n - i; j > 1; j--) {
                stringWriter.append(" ");
//                output.setText(stringWriter.toString());
            }
            for (int j = 0; j <= i; j++) {

                stringWriter.append("* ");
            }
            stringWriter.append("\n");

        }
        output.setText(stringWriter.toString());


    }

    private void name() {

        String names = "helloo yes oo";
        int count = 0;
        for (int i = 0; i < names.length(); i++) {
            char chr = names.charAt(i);
            if (String.valueOf(chr).equals("h")) {
                count++;
            }


        }
        output.setText(String.valueOf(count));


    }

    private void extra() {


        int n = 10, t1 = 0, t2 = 1;
        StringWriter sw = new StringWriter();

        for (int i = 1; i <= n; i++) {

            sw.append(t1 + "+");
            output.setText(sw.toString());


            int sum = t1 + t2;
            t1 = t2;
            t2 = sum;
        }


    }



}
