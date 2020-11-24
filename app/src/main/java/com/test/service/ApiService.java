package com.test.service;


import com.test.DataModel;
import com.test.tryone.TryoneModal;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface ApiService {

    @GET("user_order_detail/38")
    Call<DataModel> restorent();

    @GET("testResponse.php")
    Call<TryoneModal> tryone();

}
