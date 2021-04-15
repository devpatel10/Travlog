package com.example.travlog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import android.view.LayoutInflater;
import androidx.appcompat.app.AppCompatActivity;

import com.example.travlog.ui.home.HomeFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements LoginFragment.OnLoginFormActivityListener {
    public static PrefConfig prefConfig;
    public static ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState )
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefConfig= new PrefConfig(this);
        apiInterface=ApiClient.getApiClient().create(ApiInterface.class);

        if(findViewById(R.id.fragment_container)!=null)
        {
            if(savedInstanceState!=null)
            {
                return ;
            }
            if(prefConfig.readLoginStatus())
            {
                //prefConfig.writeLoginStatus(false);
                prefConfig.displayToast("Log In Successful");
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
            }
            else
            {
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,new LoginFragment()).commit();
            }
        }
    }

    @Override
    public void performRegister() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new RegisterFragment()).addToBackStack(null).commit();
    }

    @Override
    public void performLogIn(String name,String username) {
        prefConfig.writeName(name);
        prefConfig.writeUsername(username);
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}