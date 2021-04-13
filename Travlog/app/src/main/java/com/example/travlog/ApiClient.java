package com.example.travlog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static final String BASE_URL="http://10.0.2.2/travlog/";
    public static Retrofit retrofit=null;
    public static Retrofit getApiClient()
    {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();


            retrofit= new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();

        return retrofit;
    }

}
