package com.example.travlog;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travlog.ui.activity.DetailsViewModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.travlog.MainActivity.prefConfig;

public class LogoutFragment extends Fragment {
    public View  onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {





                    //prefConfig.displayToast("ok");
                    com.example.travlog.MainActivity.prefConfig.writeLoginStatus(false);

        Intent intent = new Intent(super.getContext(), MainActivity.class);
        startActivity(intent);
        View view = inflater.inflate(R.layout.fragment_logout, container, false);


        return view;



    }

}
