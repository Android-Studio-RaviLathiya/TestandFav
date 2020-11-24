package com.test.box;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.test.R;

import java.util.ArrayList;

public class BoxAdapter extends RecyclerView.Adapter<BoxAdapter.MEMBER> {

    ArrayList<BoxItem> memberItems;
    Context context;
    int box;

    public BoxAdapter(ArrayList<BoxItem> memberItems, Context context) {
        this.memberItems = memberItems;
        this.context = context;
    }

    @NonNull
    @Override
    public MEMBER onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.box_item, parent, false);
        return new MEMBER(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MEMBER holder, int position) {

        holder.tv.setText(memberItems.get(position).getLayout());


        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//
//                if (memberItems.size() == )
//
//                String size = "" + memberItems.size();
//                Toast.makeText(context, "" + size, Toast.LENGTH_SHORT).show();

                box = position;
                notifyDataSetChanged();





            }
        });



    }

    @Override
    public int getItemCount() {
        return memberItems.size();
    }


    public class MEMBER extends RecyclerView.ViewHolder {

        TextView tv;
        LinearLayout ll;

        public MEMBER(@NonNull View itemView) {
            super(itemView);

            tv = itemView.findViewById(R.id.tv);
            ll = itemView.findViewById(R.id.ll);
        }
    }
}

