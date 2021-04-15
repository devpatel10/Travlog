package com.example.travlog.ui.activity;

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

import com.example.travlog.DetailAdapter;
import com.example.travlog.DetailItem;
import com.example.travlog.MainActivity;
import com.example.travlog.R;
import com.example.travlog.TripItem;
import com.example.travlog.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.travlog.MainActivity.prefConfig;

public class DetailsFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private DetailAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private DetailsViewModel homeViewModel;
    private ArrayList<ArrayList<String>>[] triplist = new ArrayList[]{new ArrayList<ArrayList<String>>(1)};
    private Button LoginBn,DeleteBtn;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(DetailsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_detail, container, false);

       // final ArrayList<DetailsItem> TripList = new ArrayList<DetailItem>(0);
        mLayoutManager = new LinearLayoutManager(super.getContext());

        mLayoutManager = new LinearLayoutManager(super.getContext());

        Call<User> call=MainActivity.apiInterface.showDetails(prefConfig.readTripid());
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.body().getResponse().equals("ok"))
                {

                    //prefConfig.displayToast("ok");

                    triplist[0] =response.body().event_list;
                    //prefConfig.displayToast(""+response.body().event_list);


                    mRecyclerView=root.findViewById(R.id.recyclerview1);
                    mRecyclerView.setHasFixedSize(true);
                    mAdapter=new DetailAdapter(triplist[0]);
                    mRecyclerView.setLayoutManager(mLayoutManager);
                    mRecyclerView.setAdapter(mAdapter);

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
