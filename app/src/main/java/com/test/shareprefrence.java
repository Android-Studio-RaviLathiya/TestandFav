package com.test;

import android.app.Application;
import android.app.LauncherActivity;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class shareprefrence extends Application {
    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;

    @Override
    public void onCreate() {
        super.onCreate();

        sharedPreferences = getSharedPreferences("My", MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }


    public static void setrestorentlist(ArrayList<DataModel.data> listItems) {
        Gson gson = new Gson();
        String data = gson.toJson(listItems);
        editor.putString("list", data).commit();
    }

    public static ArrayList<DataModel.data> getrestorentlist() {
        Gson gson = new Gson();
        String data = sharedPreferences.getString("list", null);
        ArrayList<DataModel.data> list = gson.fromJson(data, new TypeToken<List<DataModel.data>>() {
        }.getType());
        return list;
    }

    public static void setStatus(boolean status) {
        editor.putBoolean("status", status).commit();
    }

    public static boolean getStatus() {
        boolean b = sharedPreferences.getBoolean("status", false);
        return b;
    }


}
