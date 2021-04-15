package com.example.travlog;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
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

import com.example.travlog.ui.gallery.GalleryFragment;
import com.example.travlog.ui.gallery.GalleryViewModel;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddTransportFragment extends Fragment {

    private TransportViewModel galleryViewModel;

    private EditText TransName;
    private Spinner dropdown;
    private EditText From  ;
    private EditText To;
    private EditText Departure;
    private EditText Arrival;
    private EditText Cost;
    DatePickerDialog picker;
    private Button AddTBtn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                (TransportViewModel) ViewModelProviders.of(this).get(TransportViewModel.class);
        View view = inflater.inflate(R.layout.fragment_transport, container, false);
        TransName = view.findViewById(R.id.transnameEditText);
        From=view.findViewById(R.id.fromEditText);
        To=view.findViewById(R.id.toEditText);
        Departure=(EditText)view.findViewById(R.id.sdateEditText);
        Arrival=(EditText)view.findViewById(R.id.edateEditText);
        Cost=(EditText)view.findViewById(R.id.transportcostEditText);

         dropdown = view.findViewById(R.id.spinner1);
        String[] items = new String[]{"Bus", "Train", "Flight"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(super.getContext(), android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);
        Departure.setInputType(InputType.TYPE_NULL);
        final Calendar[] date = new Calendar[1];
        Departure.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                final Calendar currentDate = Calendar.getInstance();
                date[0] =  Calendar.getInstance();
                new DatePickerDialog(AddTransportFragment.super.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        date[0].set(year, monthOfYear, dayOfMonth);
                        new TimePickerDialog(AddTransportFragment.super.getContext(), new TimePickerDialog.OnTimeSetListener() {
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
                new DatePickerDialog(AddTransportFragment.super.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        date[0].set(year, monthOfYear, dayOfMonth);
                        new TimePickerDialog(AddTransportFragment.super.getContext(), new TimePickerDialog.OnTimeSetListener() {
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

        AddTBtn=view.findViewById(R.id.addtransportBtn);

        AddTBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performAddTransport();
            }
        });

        return view;
    }
    public void performAddTransport(){
        String tname = TransName.getText().toString();
        String from = From.getText().toString();
        String to=To.getText().toString();
        String departure=Departure.getText().toString();
        String arrival=Arrival.getText().toString();
        String cost=Cost.getText().toString();
        String type = dropdown.getSelectedItem().toString();
        //  MainActivity.prefConfig.displayToast(type.getText().toString());

        Call<User> call= MainActivity.apiInterface.addTransport(MainActivity.prefConfig.readTripid().toString(),type,tname,from,to,departure,arrival,cost);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.body().getResponse().equals("ok")) {

                    Intent intent = new Intent(AddTransportFragment.super.getContext(), HomeActivity.class);
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




