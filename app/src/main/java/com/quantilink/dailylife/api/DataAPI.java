package com.quantilink.dailylife.api;

import com.quantilink.dailylife.models.DataPackage;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface DataAPI {
    @GET("AND/{id}")
    Call<DataPackage> getData(@Path("id") int id);

    @POST("AND")
    Call<DataPackage> saveData(@Body DataPackage data);
}
