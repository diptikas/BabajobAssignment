package com.diptika.babajob.babajobassignment.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.diptika.babajob.babajobassignment.R;
import com.diptika.babajob.babajobassignment.adapter.UserListAdapter;
import com.diptika.babajob.babajobassignment.db.UserDataBaseHandler;
import com.diptika.babajob.babajobassignment.model.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by diptika.s on 15/05/16.
 */
public class UserListActivity extends AppCompatActivity {
    private ListView userList;
    private UserListAdapter userListAdapter;
    private Button addNewUserBtn;
    private UserDataBaseHandler userDataBaseHandler;
    private List<UserInfo> userInfoList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userList = (ListView) findViewById(R.id.user_list);
        addNewUserBtn = (Button) findViewById(R.id.add_new);

        userDataBaseHandler = new UserDataBaseHandler(this);
        userInfoList = userDataBaseHandler.getUserList(getIntent().getExtras().getString("day"));

        addNewUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // UserListActivity.this.finish();
                Intent newUserIntent = new Intent(UserListActivity.this, AddNewUserActivity.class);
                newUserIntent.putExtra("UserId",0);
                startActivity(newUserIntent);
            }
        });

        if (userInfoList.isEmpty() && userInfoList.size() == 0) {
            Toast.makeText(getApplicationContext(), "No User", Toast.LENGTH_SHORT).show();
        } else {

            userListAdapter = new UserListAdapter(this, userInfoList,getIntent().getExtras().getString("day"));
            userList.setAdapter(userListAdapter);
            userListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onResume()
    {
        super.onResume();
        userInfoList = userDataBaseHandler.getUserList(getIntent().getExtras().getString("day"));
        userListAdapter = new UserListAdapter(this, userInfoList, getIntent().getExtras().getString("day"));
        userList.setAdapter(userListAdapter);
        userListAdapter.notifyDataSetChanged();
    }
    }
