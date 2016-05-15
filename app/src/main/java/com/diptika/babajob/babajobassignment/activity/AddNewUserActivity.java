package com.diptika.babajob.babajobassignment.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.diptika.babajob.babajobassignment.R;
import com.diptika.babajob.babajobassignment.db.UserDataBaseHandler;
import com.diptika.babajob.babajobassignment.model.UserInfo;

/**
 * Created by diptika.s on 5/14/2016.
 */
public class AddNewUserActivity extends AppCompatActivity {
    private EditText nameEt, locationEt, ageEt, bloodgroupEt, phoneNumberEt;
    private CheckBox day1Ch, day2Ch, day3Ch;
    private Button submitBtn,saveBtn;
    private int count1 = 1, count2 = 1, count3 = 1;
    private TextView nameErrText,locErrText,ageErrText,phnNoErrText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_user_activity);

        final UserDataBaseHandler userdatabaseHandler = new UserDataBaseHandler(AddNewUserActivity.this);

        init();

        final int userId = getIntent().getExtras().getInt("UserId");
        if(userId != 0) {

            submitBtn.setVisibility(View.GONE);
            saveBtn.setVisibility(View.VISIBLE);
            UserInfo userInfo=  userdatabaseHandler.getUserInfoFromUsrId(userId);
            nameEt.setText(userInfo.getUserName());
            locationEt.setText(userInfo.getUserLocation());
            ageEt.setText(userInfo.getUserAge()+"");
            bloodgroupEt.setText(userInfo.getUserBloodGroup());
            phoneNumberEt.setText(userInfo.getUserContactNum());

            if(userInfo.getDay1()>=1){
                day1Ch.setChecked(true);
            }else {
                day1Ch.setChecked(false);
            }

            if(userInfo.getDay2()>=1){
                day2Ch.setChecked(true);
            }else {
                day2Ch.setChecked(false);
            }

            if(userInfo.getDay3()>=1){
                day3Ch.setChecked(true);
            }else {
                day3Ch.setChecked(false);
            }

        }

        day1Ch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (day1Ch.isChecked()) {
                    count1 = 1;
                }else {
                    count1 = 0;
                }
            }
        });


        day2Ch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (day2Ch.isChecked()) {
                    count2 = 1;
                }else {
                    count2 = 0;
                }
            }
        });

        day3Ch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (day3Ch.isChecked()) {
                    count3 = 1;
                }else {
                    count3 = 0;
                }
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {if(nameEt.getText().toString().isEmpty()){
                nameErrText.setVisibility(View.VISIBLE);
            }else {
                nameErrText.setVisibility(View.GONE);
            }

                if(locationEt.getText().toString().isEmpty()){
                    locErrText.setVisibility(View.VISIBLE);
                }else {
                    locErrText.setVisibility(View.GONE);
                }

                if(phoneNumberEt.getText().toString().isEmpty()||phoneNumberEt.getText().toString().length()<10)
                {
                    phnNoErrText.setVisibility(View.VISIBLE);
                }else {
                    phnNoErrText.setVisibility(View.GONE);
                }

                if(ageEt.getText().toString().isEmpty()){
                    ageErrText.setVisibility(View.VISIBLE);
                }else {
                    ageErrText.setVisibility(View.GONE);
                }

                if(!nameEt.getText().toString().isEmpty()&&!locationEt.getText().toString().isEmpty()&&!phoneNumberEt.getText().toString().isEmpty()||phoneNumberEt.getText().toString().length()<10&&!ageEt.getText().toString().isEmpty()) {

                    String name = nameEt.getText().toString();
                    int age = Integer.parseInt(ageEt.getText().toString());
                    String bloodgroup = bloodgroupEt.getText().toString();
                    String contactno = phoneNumberEt.getText().toString();
                    String location = locationEt.getText().toString();
                    Log.d("UserInfo", name + "-" + age + "-" + bloodgroup + "-" + contactno + "-" + location + "-" + count1 + "-" + count2 + "-" + count3);
                    userdatabaseHandler.updateUserInfoFromUsrId(new UserInfo(userId, name, age, bloodgroup, contactno, location, count1, count2, count3));
                    Toast.makeText(getApplicationContext(), "User Successfully Saved", Toast.LENGTH_SHORT).show();
                    AddNewUserActivity.this.finish();
                }
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(nameEt.getText().toString().isEmpty()){
                    nameErrText.setVisibility(View.VISIBLE);
                }else {
                    nameErrText.setVisibility(View.GONE);
                }

                if(locationEt.getText().toString().isEmpty()){
                    locErrText.setVisibility(View.VISIBLE);
                }else {
                    locErrText.setVisibility(View.GONE);
                }

                if(phoneNumberEt.getText().toString().isEmpty()||phoneNumberEt.getText().toString().length()<10)
                {
                    phnNoErrText.setVisibility(View.VISIBLE);
                }else {
                    phnNoErrText.setVisibility(View.GONE);
                }

                if(ageEt.getText().toString().isEmpty()){
                    ageErrText.setVisibility(View.VISIBLE);
                }else {
                    ageErrText.setVisibility(View.GONE);
                }

                if(!nameEt.getText().toString().isEmpty()&&!locationEt.getText().toString().isEmpty()&&!phoneNumberEt.getText().toString().isEmpty()||phoneNumberEt.getText().toString().length()<10&&!ageEt.getText().toString().isEmpty()) {

                    String name = nameEt.getText().toString();
                    int age = Integer.parseInt(ageEt.getText().toString());
                    String bloodgroup = bloodgroupEt.getText().toString();
                    String contactno = phoneNumberEt.getText().toString();
                    String location = locationEt.getText().toString();
                    Log.d("UserInfo", name + "-" + age + "-" + bloodgroup + "-" + contactno + "-" + location + "-" + count1 + "-" + count2 + "-" + count3);
                    userdatabaseHandler.addUser(new UserInfo(name, age, bloodgroup, contactno, location, count1, count2, count3));
                    Toast.makeText(getApplicationContext(), "User Successfully Added", Toast.LENGTH_SHORT).show();
                    AddNewUserActivity.this.finish();

                }
            }
        });


    }


    private void init() {
        nameEt = (EditText) findViewById(R.id.full_name);
        locationEt = (EditText) findViewById(R.id.location);
        ageEt = (EditText) findViewById(R.id.age);
        bloodgroupEt = (EditText) findViewById(R.id.blood_group);
        phoneNumberEt = (EditText) findViewById(R.id.phone_no);
        day1Ch = (CheckBox) findViewById(R.id.checkbox_day1);
        day2Ch = (CheckBox) findViewById(R.id.checkbox_day2);
        day3Ch = (CheckBox) findViewById(R.id.checkbox_day3);
        submitBtn = (Button) findViewById(R.id.add_new);
        saveBtn = (Button) findViewById(R.id.save_user);
        nameErrText=(TextView)findViewById(R.id.error_name_text);
        phnNoErrText=(TextView)findViewById(R.id.error_phn_text);
        locErrText=(TextView)findViewById(R.id.error_add_text);
        ageErrText=(TextView)findViewById(R.id.error_pin_text);
    }


}
