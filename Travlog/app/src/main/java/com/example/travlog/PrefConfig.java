package com.example.travlog;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

public class PrefConfig
{
    private SharedPreferences sharedPreferences;
    private Context context;


    public PrefConfig(Context context)
    {
        this.context=context;
        sharedPreferences= context.getSharedPreferences(context.getString(R.string.pref_file),Context.MODE_PRIVATE);

    }
    public void writeLoginStatus(boolean status)
    {
        SharedPreferences.Editor editor =sharedPreferences.edit();
        editor.putBoolean(context.getString(R.string.pref_login_status),status);
        editor.commit();
    }
    public void writeTripid(String tripid)
    {
        SharedPreferences.Editor editor =sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_tripid), tripid);
        editor.commit();
    }
    public String readTripid()
    {
        return sharedPreferences.getString(context.getString(R.string.pref_tripid),"1");
    }
    public boolean readLoginStatus()
    {
        return sharedPreferences.getBoolean(context.getString(R.string.pref_login_status),false);
    }
    public void writeName(String name)
    {
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_name),name);
        editor.commit();
    }
    public String readName()
    {
        return sharedPreferences.getString(context.getString(R.string.pref_name),"User");
    }
    public void writeType(String type)
    {
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_type),type);
        editor.commit();
    }
    public String readType()
    {
        return sharedPreferences.getString(context.getString(R.string.pref_type),"parent");
    }

    public void writeUsername(String name)
    {
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_username),name);
        editor.commit();
    }
    public String readUsername()
    {
        return sharedPreferences.getString(context.getString(R.string.pref_username),"Username");
    }
    public void displayToast(String message)
    {
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }

}
