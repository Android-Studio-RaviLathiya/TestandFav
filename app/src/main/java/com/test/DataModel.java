package com.test;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DataModel implements Serializable {

    @SerializedName("ResponseMessage")
    @Expose
    public String ResponseMessage;


    @SerializedName("data")
    @Expose
    public List<data> data;

    public class data implements Serializable {

        @SerializedName("order_id")
        @Expose
        public String order_id;

        @SerializedName("user_id")
        @Expose
        public String user_id;
        @SerializedName("restaurant_id")
        @Expose
        public String restaurant_id;
        @SerializedName("user_name")
        @Expose
        public String user_name;
        @SerializedName("restaurant_name")
        @Expose
        public String restaurant_name;
        @SerializedName("contact_no")
        @Expose
        public String contact_no;

        @SerializedName("address")
        @Expose
        public String address;
        @SerializedName("restaurant_address")
        @Expose
        public String restaurant_address;
        @SerializedName("status")
        @Expose
        public String status;
        @SerializedName("product_code")
        @Expose
        public String product_code;

        @SerializedName("start_time")
        @Expose
        public String start_time;

        @SerializedName("end_time")
        @Expose
        public String end_time;

        @SerializedName("restaurant_image")
        @Expose
        public String restaurant_image;

        @SerializedName("latitude")
        @Expose
        public String latitude;

        @SerializedName("longitude")
        @Expose
        public String longitude;


        @SerializedName("food_detail")
        @Expose
        public List<food_detail> food_detail;

        public class food_detail implements Serializable {

            @SerializedName("food_name")
            @Expose
            public String food_name;

            @SerializedName("food_id")
            @Expose
            public String food_id;
            @SerializedName("description")
            @Expose
            public String description;
            @SerializedName("quantity")
            @Expose
            public String quantity;
            @SerializedName("original_price")
            @Expose
            public String original_price;
            @SerializedName("total_price")
            @Expose
            public String total_price;


            @SerializedName("food_image")
            @Expose
            public String food_image;


        }


    }

}