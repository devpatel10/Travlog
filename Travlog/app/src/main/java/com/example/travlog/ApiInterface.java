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
    @GET("addtrips.php")
    Call<User> addTrip(@Query("username") String username, @Query("title") String title, @Query("description") String description, @Query("drive_link") String drivelink, @Query("start_date") String sdate, @Query("end_date") String edate);
    @GET("addtransport.php")
    Call<User> addTransport(@Query("trip_id") String tripid, @Query("type") String type, @Query("trans_name") String tname, @Query("from") String from, @Query("to") String to, @Query("cost") String cost,@Query("departure") String sdate, @Query("arrival") String edate);
    @GET("hotellist.php")
    Call<User> hotelList();
    @GET("addhotel.php")
    Call<User> addHotel(@Query("trip_id") String tripid, @Query("hotel_name") String hotelname, @Query("check_in") String tname, @Query("check_out") String from, @Query("cost") String cost);

    @GET("deletetrip.php")
    Call<User> deleteTrip(@Query("tripid") int tripid);
    @GET("dissociate.php")
    Call<User> deleteRelation(@Query("username") String username);
    @GET("getType.php")
    Call<User> getType(@Query("username") String username);
    @GET("getLatLng.php")
    Call<User> getLatLng(@Query("username") String username);
    @GET("updateLatLng.php")
    Call<User> updateLatLng(@Query("username") String username, @Query("latLng") String latLng);
    @GET("showtrip.php")
    Call<User> showTrip(@Query("username") String username);
    @GET("details.php")
    Call<User> showDetails(@Query("trip_id") String tripid);
}
