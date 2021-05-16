package com.quantilink.dailylife.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder().baseUrl("http://api.quantilink.com").addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static DataAPI dataAPI = retrofit.create(DataAPI.class);

    public static DataAPI getDataAPI(){
        return dataAPI;
    }
}
