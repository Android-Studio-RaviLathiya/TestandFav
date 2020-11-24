package com.test.tryone;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.test.Alldetail;
import com.test.DataModel;
import com.test.ImgActivity;
import com.test.R;

import java.util.ArrayList;
import java.util.List;

public class TryoneAdapter extends RecyclerView.Adapter<TryoneAdapter.TRYONE> {

    public List<TryoneModal.errors> tryone = new ArrayList<TryoneModal.errors>();
    public Activity mContext;

    public TryoneAdapter(Activity mContext) {
        this.mContext = mContext;
    }

    public void addAll(List<TryoneModal.errors> files) {

        try {
            this.tryone.clear();
            this.tryone.addAll(files);

        } catch (Exception e) {
            e.printStackTrace();
        }
        notifyDataSetChanged();
    }


    public void appendAll(List<TryoneModal.errors> files) {

        try {
            this.tryone.addAll(files);

        } catch (Exception e) {
            e.printStackTrace();
        }

        notifyDataSetChanged();
    }

    public void clearAll() {
        this.tryone.clear();
        notifyDataSetChanged();
    }

    public void updateList(List<TryoneModal.errors> list) {
        this.tryone = list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public TRYONE onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.try_one_item, parent, false);

        return new TRYONE(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TRYONE holder, int position) {

        holder.tv.setText(tryone.get(position).source.pointer + "");

        MediaController mediaController = new MediaController(mContext);
        mediaController.setAnchorView(holder.video);
        Uri uri = Uri.parse("https://web.law.duke.edu/cspd/contest/videos/Framed-Contest_Documentaries-and-You.mp4");
        holder.video.setMediaController(mediaController);
        holder.video.setVideoURI(uri);
        holder.video.requestFocus();
        holder.video.start();


        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.ic_autorenew_black_24dp);
        Glide.with(mContext)
                .load(tryone.get(position).image)
                .apply(requestOptions)
                .into(holder.img);


    }

    @Override
    public int getItemCount() {
        return tryone.size();
    }

    public class TRYONE extends RecyclerView.ViewHolder {

        TextView tv, name;
        ImageView img;
        VideoView video;


        public TRYONE(@NonNull View itemView) {
            super(itemView);

            tv = itemView.findViewById(R.id.tv);
            name = itemView.findViewById(R.id.name);
            img = itemView.findViewById(R.id.img);
            video = itemView.findViewById(R.id.video);


//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(mContext, ""+tryone.get(getAdapterPosition()).type, Toast.LENGTH_SHORT).show();
//                }
//            });

        }
    }
}
