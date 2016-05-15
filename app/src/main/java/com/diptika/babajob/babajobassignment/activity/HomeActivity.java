package com.diptika.babajob.babajobassignment.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.diptika.babajob.babajobassignment.R;
import com.diptika.babajob.babajobassignment.db.UserDataBaseHandler;
import com.diptika.babajob.babajobassignment.model.UserInfo;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by diptika.s on 14/05/16.
 */
public class HomeActivity extends AppCompatActivity {
    private LinearLayout day1Layout,day2Layout,day3Layout;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

            day1Layout=(LinearLayout)findViewById(R.id.day1_layout);
            day2Layout=(LinearLayout)findViewById(R.id.day2_layout);
            day3Layout=(LinearLayout)findViewById(R.id.day3_layout);

            day1Layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent userListIntent=new Intent(HomeActivity.this,UserListActivity.class);
                    userListIntent.putExtra("day", "day1");
                    startActivity(userListIntent);
                }
            });

            day2Layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent userListIntent=new Intent(HomeActivity.this,UserListActivity.class);
                    userListIntent.putExtra("day", "day2");
                    startActivity(userListIntent);
                }
            });

            day3Layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent userListIntent=new Intent(HomeActivity.this,UserListActivity.class);
                    userListIntent.putExtra("day", "day3");
                    startActivity(userListIntent);
                }
            });



        }
    }


