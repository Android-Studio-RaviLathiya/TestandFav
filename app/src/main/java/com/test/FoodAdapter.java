package com.test;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FOOD> {

    public List<DataModel.data.food_detail> restorent = new ArrayList<DataModel.data.food_detail>();
    public Activity mContext;

    public FoodAdapter(Activity mContext) {
        this.mContext = mContext;
    }

    public void addAll(List<DataModel.data.food_detail> files) {

        try {
            this.restorent.clear();
            this.restorent.addAll(files);

        } catch (Exception e) {
            e.printStackTrace();
        }
        notifyDataSetChanged();
    }


    public void appendAll(List<DataModel.data.food_detail> files) {

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

//    public void updateList(List<DataModel.data.food_detail> list) {
//        this.restorent = list;
//        notifyDataSetChanged();
//    }


    @NonNull
    @Override
    public FOOD onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.restorent_item, parent, false);

        return new FOOD(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FOOD holder, int position) {

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.ic_autorenew_black_24dp);

        holder.time.setText(restorent.get(position).food_name + "");
        holder.name.setText(restorent.get(position).description + "");
//        Glide.with(mContext).load(restorent.get(position).food_image).into(holder.img);
        Glide.with(mContext)
                .load(restorent.get(position).food_image)
                .apply(requestOptions)
                .into(holder.img);

        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent = new Intent(mContext, ImgActivity.class);
                intent.putExtra("img",restorent.get(position).food_image);
                mContext.startActivity(intent);

            }
        });

        holder.click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(mContext, Alldetail.class);
                intent.putExtra("q",  restorent.get(position).description);
                mContext.startActivity(intent);
//                mContext.startActivityForResult(intent, 426);

            }
        });


    }

    @Override
    public int getItemCount() {
        return restorent.size();
    }

    public class FOOD extends RecyclerView.ViewHolder {

        TextView name, time;
        LinearLayout click;
        ImageView img;

        public FOOD(@NonNull View itemView) {
            super(itemView);

            time = itemView.findViewById(R.id.time);
            name = itemView.findViewById(R.id.name);
            img = itemView.findViewById(R.id.img);
            click = itemView.findViewById(R.id.click);
        }
    }
}
