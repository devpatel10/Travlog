package com.example.travlog;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.travlog.ui.gallery.GalleryViewModel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.travlog.MainActivity.prefConfig;

public class AddHotelFragment extends Fragment {

    private HotelViewModel galleryViewModel;


    private Spinner dropdown;
    private EditText Departure;
    private EditText Arrival;
    private EditText Cost;
    DatePickerDialog picker;
    private Button addhBtn;

    private ArrayList<ArrayList<String>>[] t = new ArrayList[]{new ArrayList<ArrayList<String>>(1)};
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(HotelViewModel.class);
        View view = inflater.inflate(R.layout.fragment_hotel, container, false);

        Departure=(EditText)view.findViewById(R.id.checkinEditText);
        Arrival=(EditText)view.findViewById(R.id.checkoutEditText);
        Cost=(EditText)view.findViewById(R.id.hotelcostEditText);

        dropdown = view.findViewById(R.id.spinner2);
        final ArrayList<String> items = new ArrayList<>();
        Call<User> call= MainActivity.apiInterface.hotelList();
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.body().getResponse().equals("ok")) {
                   t[0] =response.body().trips;
                   for(int i=0;i<t[0].size();i++)
                   {
                       String s= t[0].get(i).get(0) + " "+ t[0].get(i).get(1);
                       items.add(s);
                   }
                    prefConfig.displayToast(""+t[0]);
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(AddHotelFragment.super.getContext(), android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.
                    dropdown.setAdapter(adapter);
                }
                else
                {
                    prefConfig.displayToast(""+response.body().getResponse());
                }
            }


            @Override
            public void onFailure(Call<User> call, Throwable t) {
                prefConfig.displayToast(""+t);
            }
        });
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.

        Departure.setInputType(InputType.TYPE_NULL);
        final Calendar[] date = new Calendar[1];
        Departure.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                final Calendar currentDate = Calendar.getInstance();
                date[0] =  Calendar.getInstance();
                new DatePickerDialog(AddHotelFragment.super.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        date[0].set(year, monthOfYear, dayOfMonth);
                        new TimePickerDialog(AddHotelFragment.super.getContext(), new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                date[0].set(Calendar.HOUR_OF_DAY, hourOfDay);
                                date[0].set(Calendar.MINUTE, minute);
                                android.text.format.DateFormat df = new android.text.format.DateFormat();
                                SimpleDateFormat dateFormat = new SimpleDateFormat(  "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                                Departure.setText( dateFormat.format(date[0]));

                            }
                        }, currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), false).show();
                    }
                }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE)).show();
            }

        });
        Arrival.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                final Calendar currentDate = Calendar.getInstance();
                date[0] = Calendar.getInstance();
                new DatePickerDialog(AddHotelFragment.super.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        date[0].set(year, monthOfYear, dayOfMonth);
                        new TimePickerDialog(AddHotelFragment.super.getContext(), new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                date[0].set(Calendar.HOUR_OF_DAY, hourOfDay);
                                date[0].set(Calendar.MINUTE, minute);
                                SimpleDateFormat dateFormat = new SimpleDateFormat(  "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                                Arrival.setText( dateFormat.format(date[0]));
                            }
                        }, currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), false).show();
                    }
                }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE)).show();
            }

        });

        addhBtn=view.findViewById(R.id.addhotelBtn);

        addhBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performAddHotel();
            }
        });

        return view;
    }
    public void performAddHotel(){


        String departure=Departure.getText().toString();
        String arrival=Arrival.getText().toString();
        String cost=Cost.getText().toString();
        String name = dropdown.getSelectedItem().toString();
        //  MainActivity.prefConfig.displayToast(type.getText().toString());
        String[] arr = name.split(" ");
        prefConfig.displayToast(arr[0]);
        Call<User> call= MainActivity.apiInterface.addHotel(prefConfig.readTripid(),arr[0],departure,arrival,cost);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.body().getResponse().equals("ok")) {
                    prefConfig.displayToast("Successfully added...");
                    Intent intent = new Intent(AddHotelFragment.super.getContext(), HomeActivity.class);
                    startActivity(intent);
                }
                else
                {
                    prefConfig.displayToast(""+response.body().getResponse());
                }
            }


            @Override
            public void onFailure(Call<User> call, Throwable t) {
                prefConfig.displayToast(""+t);
            }
        });
    }
}




