package com.example.travlog.ui.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travlog.Details;
import com.example.travlog.HomeActivity;
import com.example.travlog.MainActivity;
import com.example.travlog.R;
import com.example.travlog.TripAdapter;
import com.example.travlog.TripItem;
import com.example.travlog.User;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.travlog.MainActivity.prefConfig;

public class HomeFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private TripAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private HomeViewModel homeViewModel;
    private ArrayList<ArrayList<String>>[] triplist = new ArrayList[]{new ArrayList<ArrayList<String>>(1)};
    private Button LoginBn,DeleteBtn;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        final ArrayList<TripItem> TripList = new ArrayList<TripItem>(0);
        mLayoutManager = new LinearLayoutManager(super.getContext());

        mLayoutManager = new LinearLayoutManager(super.getContext());
        Call<User> call=MainActivity.apiInterface.showTrip(prefConfig.readUsername());
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.body().getResponse().equals("ok"))
                {
                    com.example.travlog.MainActivity.prefConfig.writeLoginStatus(true);
                    //prefConfig.displayToast("ok");

                    triplist[0] =response.body().trips;
                    TripList.clear();
                    for (int i = 0; i< triplist[0].size(); i++) {
                        TripItem t=new TripItem(Integer.parseInt(triplist[0].get(i).get(0)),R.drawable.ic_android, triplist[0].get(i).get(1), triplist[0].get(i).get(2));
                        TripList.add(t);
                    }
                    mRecyclerView=root.findViewById(R.id.recyclerview);
                    mRecyclerView.setHasFixedSize(true);
                    mAdapter=new TripAdapter(TripList);
                    mRecyclerView.setLayoutManager(mLayoutManager);
                    mRecyclerView.setAdapter(mAdapter);
                    mAdapter.setOnItemClickListener(new TripAdapter.OnItemClickListener() {
                        @Override
                        public void onDeleteClick(int position) {
                            int tripid= TripList.get(position).getTripId();
                            Call<User> call=MainActivity.apiInterface.deleteTrip(tripid);
                            call.enqueue(new Callback<User>() {
                                @Override
                                public void onResponse(Call<User> call, Response<User> response) {
                                    if(response.body().getResponse().equals("ok"))
                                    {
                                        Intent intent = new Intent(HomeFragment.super.getContext(), HomeActivity.class);
                                        startActivity(intent);
                                        prefConfig.displayToast("Deleted");


                                    }
                                    else if(response.body().getResponse().equals("failed"))
                                    {
                                        prefConfig.displayToast("TripId Deletion failed");
                                    }
                                }

                                @Override
                                public void onFailure(Call<User> call, Throwable t) {
                                    prefConfig.displayToast(""+ t);
                                }
                            });
                        }

                        @Override
                        public void onDetailsClick(int position) {

                            Intent intent = new Intent(HomeFragment.super.getContext(), Details.class);
                            startActivity(intent);
                        }
                    });

                }
                else if(response.body().getResponse().equals("failed"))
                {
                    prefConfig.displayToast("Lets get you started");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                prefConfig.displayToast(""+t);
            }
        });




        return root;
    }

}
