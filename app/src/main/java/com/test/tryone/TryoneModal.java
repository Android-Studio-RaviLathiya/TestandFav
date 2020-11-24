package com.test.tryone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TryoneModal implements Serializable {

    @SerializedName("jsonapi")
    @Expose
    public jsonapi jsonapi;

    public class jsonapi implements Serializable {

        @SerializedName("version")
        @Expose
        public String version;

    }

    @SerializedName("errors")
    @Expose
    public List<errors> errors;

    public class errors implements Serializable {


        @SerializedName("code")
        @Expose
        public String code;

        @SerializedName("source")
        @Expose
        public source source;

        public class source implements Serializable {

            @SerializedName("pointer")
            @Expose
            public String pointer;
        }

        @SerializedName("title")
        @Expose
        public String title;

        @SerializedName("detail")
        @Expose
        public String detail;

        @SerializedName("image")
        @Expose
        public String image;


        @SerializedName("video")
        @Expose
        public String video;


    }
}






