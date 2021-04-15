package com.example.travlog;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import retrofit2.Response;

public class User {
    @SerializedName("response")
    public String response;
    @SerializedName("name")
    public String name;
    public ArrayList<ArrayList<String>> trips;
    public String getResponse()
    {
        return response;
    }
    public String getName()
    {
        return name;
    }
}
