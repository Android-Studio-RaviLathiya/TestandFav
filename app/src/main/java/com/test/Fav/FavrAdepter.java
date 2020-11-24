package com.test.Fav;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.test.DataModel;
import com.test.R;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class FavrAdepter extends RecyclerView.Adapter<FavrAdepter.ADDCONTECT> {

    ArrayList<FavItem> favitem;
    Context context;
    SQLiteDatabase db;


    public FavrAdepter(ArrayList<FavItem> favitem, Context context) {
        this.favitem = favitem;
        this.context = context;
    }

    @NonNull
    @Override
    public ADDCONTECT onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fav_item, parent, false);
        db = context.openOrCreateDatabase("Data.db", MODE_PRIVATE, null);
        return new ADDCONTECT(view);
    }

    @Override
    public  void onBindViewHolder(@NonNull ADDCONTECT holder, final int position) {


        holder.username.setText(favitem.get(position).restaurantname);
        holder.restoname.setText(favitem.get(position).username);

        holder.delet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                db.execSQL("delete from newuser where id = '" + favitem.get(position).getId() + "'");
//                favitem.remove(position);
//                notifyDataSetChanged();
                Toast.makeText(context, "Delet"  + favitem.get(position).getId(), Toast.LENGTH_SHORT).show();
            }
        });




        //        RequestOptions requestOptions = new RequestOptions();
//        requestOptions.placeholder(R.drawable.ic_autorenew_black_24dp);
//        Glide.with(context)
//                .load(favitem.get(position).restaurantimage)
//                .apply(requestOptions)
//                .into(holder.image);

    }
    public static void deletitem(Context context, String id, List<DataModel.data> favitem){
        SQLiteDatabase db;

        db = context.openOrCreateDatabase("Data.db", MODE_PRIVATE, null);

        db.execSQL("delete from newuser where id = '" + id + "'");
        favitem.remove(id);
        Toast.makeText(context, "Delet", Toast.LENGTH_SHORT).show();

    }

    @Override
    public int getItemCount() {
        return favitem.size();
    }


    public class ADDCONTECT extends RecyclerView.ViewHolder {

        private ImageView image;
        private TextView username;
        private TextView restoname;
        private Button delet;

        public ADDCONTECT(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            delet = itemView.findViewById(R.id.delet);
            username = itemView.findViewById(R.id.username);
            restoname = itemView.findViewById(R.id.restoname);


        }
    }
}
