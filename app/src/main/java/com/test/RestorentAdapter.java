package com.test;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;
import com.test.Fav.FavItem;
import com.test.Fav.FavrAdepter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

public class RestorentAdapter extends RecyclerView.Adapter<RestorentAdapter.RESTORENT> {


    public List<DataModel.data> restorent = new ArrayList<DataModel.data>();
    public Activity mContext;
    ArrayList<DataModel.data> templist;
    int clickcounts = 0;
    SQLiteDatabase db;
    String simg, suname, srestname;
    ArrayList<FavItem> favItems = new ArrayList<>();


    public RestorentAdapter(List<DataModel.data> restorent, Activity mContext) {
        this.restorent = restorent;
        this.mContext = mContext;
        templist = new ArrayList<>();
        templist.addAll(restorent);

    }

    public void addAll(List<DataModel.data> files) {

        try {
            this.restorent.clear();
            this.restorent.addAll(files);

        } catch (Exception e) {
            e.printStackTrace();
        }
        notifyDataSetChanged();
    }

    public void appendAll(List<DataModel.data> files) {

        try {
            this.restorent.addAll(files);

        } catch (Exception e) {
            e.printStackTrace();
        }

        notifyDataSetChanged();
    }

    public void clearAll() {
        this.restorent.clear();
        notifyDataSetChanged();
    }

    public void updateList(List<DataModel.data> list) {
        this.restorent = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RESTORENT onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.restorent_item, parent, false);
        db = mContext.openOrCreateDatabase("Data.db", MODE_PRIVATE, null);
        return new RESTORENT(view);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull RESTORENT holder, int position) {


        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.ic_autorenew_black_24dp);
//        requestOptions.error(R.drawable.ic_error);

        holder.time.setText(restorent.get(position).start_time + "");
        holder.name.setText(restorent.get(position).restaurant_name + "");
//        Glide.with(mContext).load(restorent.get(position).restaurant_image).into(holder.img);

        Glide.with(mContext)
                .load(restorent.get(position).restaurant_image)
                .apply(requestOptions)
                .into(holder.img);

        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ImgActivity.class);
                intent.putExtra("img", restorent.get(position).restaurant_image);
                mContext.startActivity(intent);

            }
        });
        holder.click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(mContext, FoodDetailActivity.class);
                intent.putExtra("q", (Serializable) restorent.get(position).food_detail);
                mContext.startActivity(intent);
//                mContext.startActivityForResult(intent, 426);

            }
        });
//        holder.favorite_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                simg = restorent.get(position).restaurant_image;
//                suname = restorent.get(position).start_time;
//                srestname = restorent.get(position).restaurant_name;
//
//
//                if (holder.favorite_button.isChecked()) {
//
//                    Toast.makeText(mContext, "Favourite", Toast.LENGTH_SHORT).show();
//                    holder.favorite_button.setButtonDrawable(R.drawable.ic_baseline_star_rate_24);
//                    db.execSQL("insert into newuser(username ,restaurantname) values('" + suname + "','" + srestname + "')");
//
//                } else {
//                    holder.favorite_button.setButtonDrawable(R.drawable.ic_baseline_star_border_24);
//
//                    db.execSQL("delete from newuser where id = '" + restorent.size() + "'");
////                    restorent.remove(position);
//                    notifyDataSetChanged();
//                    Toast.makeText(mContext, "Unfavourite", Toast.LENGTH_SHORT).show();
//
//
//                }
//
//
//
//
//
//
//
////                clickcounts++;
//
////                if (clickcounts == 1) {
////
////                    holder.fv.setVisibility(View.VISIBLE);
////                    holder.nofv.setVisibility(View.GONE);
////
////                } else {
////                    holder.nofv.setVisibility(View.VISIBLE);
////                    holder.fv.setVisibility(View.GONE);
////                    clickcounts = 0;
////                }
//
//
//            }
//        });
        holder.favorite_button.setOnFavoriteAnimationEndListener(
                new MaterialFavoriteButton.OnFavoriteAnimationEndListener() {
                    @Override
                    public void onAnimationEnd(MaterialFavoriteButton buttonView, boolean favorite) {


                        simg = restorent.get(position).restaurant_image;
                        suname = restorent.get(position).start_time;
                        srestname = restorent.get(position).restaurant_name;

                        if (favorite) {
                            shareprefrence.setStatus(true);
                                
                            db.execSQL("insert into newuser(username ,restaurantname) values('" + suname + "','" + srestname + "')");
                            Cursor c = db.rawQuery("select id from newuser username", null);

                            Toast.makeText(mContext, "Fav", Toast.LENGTH_SHORT).show();
                        } else {


//                            FavrAdepter.deletitem(mContext,favItems.get(position).getId(),restorent);

                            Toast.makeText(mContext, "unfavc" , Toast.LENGTH_SHORT).show();
                            shareprefrence.setStatus(false);

                        }
                    }
                });







    }

    @Override
    public int getItemCount() {
        return restorent.size();
    }

    public class RESTORENT extends RecyclerView.ViewHolder {

        TextView name, time;
        LinearLayout click;
        ImageView img;
        MaterialFavoriteButton favorite_button;

        public RESTORENT(@NonNull View itemView) {
            super(itemView);

            time = itemView.findViewById(R.id.time);
            name = itemView.findViewById(R.id.name);
            img = itemView.findViewById(R.id.img);
            click = itemView.findViewById(R.id.click);
            favorite_button = itemView.findViewById(R.id.favorite_button);


        }
    }


    public void filter(String newText) {

        newText = newText.toLowerCase(Locale.getDefault());

        restorent.clear();

        if (newText.length() == 0) {
            restorent.addAll(templist);

        } else {
            for (DataModel.data item : templist) {

                if (item.restaurant_name.toLowerCase(Locale.getDefault()).contains(newText)) {
                    restorent.add(item);
                }
            }

        }
        notifyDataSetChanged();

    }


}
