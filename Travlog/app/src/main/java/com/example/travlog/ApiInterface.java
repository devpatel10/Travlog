package com.example.travlog;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("register.php")
    Call<User> performRegistration(@Query("fname") String fname,@Query("lname") String lname,@Query("gender") String gender, @Query("phone") String phone,@Query("address") String address, @Query("email") String email, @Query("password") String password, @Query("confpassword") String confpassword,@Query("dob") String dob);

    @GET("login.php")
    Call<User> performUserLogin(@Query("username") String username, @Query("password") String password);

    @GET("associate.php")
    Call<User> performUpdate(@Query("username") String username, @Query("relate") String relate);

    @GET("dissociate.php")
    Call<User> deleteRelation(@Query("username") String username);
    @GET("getType.php")
    Call<User> getType(@Query("username") String username);
    @GET("getLatLng.php")
    Call<User> getLatLng(@Query("username") String username);
    @GET("updateLatLng.php")
    Call<User> updateLatLng(@Query("username") String username, @Query("latLng") String latLng);

}
