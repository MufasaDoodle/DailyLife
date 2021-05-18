package com.quantilink.dailylife.api;

import com.quantilink.dailylife.models.DataPackage;
import com.quantilink.dailylife.models.DataPackageResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * REST API for storing and retrieving user data
 */
public interface DataAPI {
    @GET("AND/{id}")
    Call<DataPackageResponse> getData(@Path("id") String id);

    @POST("AND")
    Call<DataPackage> saveData(@Body DataPackage data);
}
