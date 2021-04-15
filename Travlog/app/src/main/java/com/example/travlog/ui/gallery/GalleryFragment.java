package com.example.travlog.ui.gallery;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.travlog.HomeActivity;
import com.example.travlog.MainActivity;
import com.example.travlog.R;
import com.example.travlog.RegisterFragment;
import com.example.travlog.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;

    private EditText Title;
    private EditText Description;
    private EditText DriveLink;
    private EditText StartDate;
    private EditText EndDate;
    private Button AddTrip;
    DatePickerDialog picker;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View view = inflater.inflate(R.layout.fragment_addtrip, container, false);
        Title = view.findViewById(R.id.titleEditText);
        Description=view.findViewById(R.id.descriptionEditText);
        DriveLink=view.findViewById(R.id.driveEditText);
        StartDate=(EditText)view.findViewById(R.id.sdateEditText);
        EndDate=(EditText)view.findViewById(R.id.edateEditText);


        StartDate.setInputType(InputType.TYPE_NULL);
        StartDate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                final int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(GalleryFragment.super.getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                StartDate.setText(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);
                            }
                        }, year, month, day);
                picker.show();
            }
        });
        EndDate.setInputType(InputType.TYPE_NULL);
        EndDate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                final int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(GalleryFragment.super.getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                EndDate.setText(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        AddTrip=view.findViewById(R.id.addtripBtn);

        AddTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performAdd();
            }
        });

        return view;
    }
    public void performAdd(){
        String title = Title.getText().toString();
        String description = Description.getText().toString();
        String drivelink=DriveLink.getText().toString();
        String sdate=StartDate.getText().toString();
        String edate=EndDate.getText().toString();
        //  MainActivity.prefConfig.displayToast(type.getText().toString());

        Call<User> call= MainActivity.apiInterface.addTrip(MainActivity.prefConfig.readUsername(),title,description,drivelink,sdate,edate);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.body().getResponse().equals("ok")) {
                    MainActivity.prefConfig.displayToast("Registration Success...");
                    Intent intent = new Intent(GalleryFragment.super.getContext(), HomeActivity.class);
                    startActivity(intent);

                }
                else
                {
                    MainActivity.prefConfig.displayToast(""+response.body().getResponse());
                }
            }


            @Override
            public void onFailure(Call<User> call, Throwable t) {
                MainActivity.prefConfig.displayToast(""+t);
            }
        });
    }
}




