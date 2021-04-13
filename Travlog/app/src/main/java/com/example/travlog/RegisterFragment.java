package com.example.travlog;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import java.sql.Date;
import java.text.ParseException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterFragment extends Fragment {

    private EditText DOB;
    private EditText fName;
    private EditText lName;
    private EditText Address;
    private EditText Email;
    private EditText Password;
    private EditText ConfPassword;
    private EditText Phone;
    private RadioGroup Group;
    private Button BnRegister;
    DatePickerDialog picker;
    public RegisterFragment() {
        // Required empty public constructor
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       // return inflater.inflate(R.layout.fragment_register, container, false);
       // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_register, container, false);
        fName = view.findViewById(R.id.firstnameEditText);
        lName=view.findViewById(R.id.lastnameEditText);
        Group=view.findViewById(R.id.genderBtn);
        Group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton gender=(RadioButton)group.findViewById(checkedId);
            }
        });
        Address=view.findViewById(R.id.addressEditText);
        Phone=view.findViewById(R.id.phoneEditText);
        Email=view.findViewById(R.id.emailEditText);
        Password=view.findViewById(R.id.passwordEditText);
        ConfPassword=view.findViewById(R.id.confpassEditText);

        DOB=(EditText) view.findViewById(R.id.dobEditText);
        DOB.setInputType(InputType.TYPE_NULL);
        DOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                final int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(RegisterFragment.super.getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                DOB.setText(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        BnRegister=view.findViewById(R.id.registerBtn);

        BnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performRegistration();
            }
        });

        return view;
    }
    @SuppressLint("SetTextI18n")
    public void performRegistration(){
        String fname = fName.getText().toString();
        String lname = lName.getText().toString();
        RadioButton gender=(RadioButton)Group.findViewById(Group.getCheckedRadioButtonId());
        String phone=Phone.getText().toString();
        String address=Address.getText().toString();
        String email=Email.getText().toString();
        String password=Password.getText().toString();
        String confpassword=ConfPassword.getText().toString();
        String dob=DOB.getText().toString();
      //  MainActivity.prefConfig.displayToast(type.getText().toString());

        Call<User> call=MainActivity.apiInterface.performRegistration(fname,lname,gender.getText().toString(),phone,address,email,password,confpassword,dob);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.body().getResponse().equals("ok")) {
                    MainActivity.prefConfig.displayToast("Registration Success...");
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    getActivity().startActivity(intent);
                } else if (response.body().getResponse().equals("exist")) {
                    MainActivity.prefConfig.displayToast("User Already Exists...");
                }
                else if (response.body().getResponse().equals("Passwords does not match")) {
                    MainActivity.prefConfig.displayToast("Passwords does not match");
                }
                else
                {
                    MainActivity.prefConfig.displayToast("Oops Something went wrong...Try Again");
                }
            }


            @Override
            public void onFailure(Call<User> call, Throwable t) {
                MainActivity.prefConfig.displayToast("Oops Something went wrong...Try Again111");
            }
        });
        fName.setText("");
        lName.setText("");
        Address.setText("");
        Phone.setText("");
        Email.setText("");
        Password.setText("");
        ConfPassword.setText("");
        DOB.setText("2000/01/01");
        }

    }

