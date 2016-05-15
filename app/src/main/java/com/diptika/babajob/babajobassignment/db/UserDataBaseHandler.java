package com.diptika.babajob.babajobassignment.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.diptika.babajob.babajobassignment.model.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by diptika.s on 15/05/16.
 */
public class UserDataBaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "userdataManager";
    private static volatile UserDataBaseHandler instance = null;
    private static Context mContext;

    public UserDataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // dropTables(db);
        db.execSQL("CREATE TABLE IF NOT EXISTS user (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT,  age INTEGER, bloodGroup TEXT,contactNum TEXT,location TEXT,day1 INTEGER,day2 INTEGER,day3 INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS user;");
        onCreate(db);
    }

    public void addUser(UserInfo userInfo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", userInfo.getUserName());
        values.put("age", userInfo.getUserAge());
        values.put("bloodGroup",userInfo.getUserBloodGroup());
        values.put("contactNum",userInfo.getUserContactNum());
        values.put("location",userInfo.getUserLocation());
        values.put("day1",userInfo.getDay1());
        values.put("day2",userInfo.getDay2());
        values.put("day3",userInfo.getDay3());

        db.insert("user", null, values);
        db.close();

    }


    public List<UserInfo> getUserList(String dayColumnName) {
        List<UserInfo> userInfoList = new ArrayList<UserInfo>();
        String selectQuery = "select  * from  user where "+ dayColumnName +" in (1,2)";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                UserInfo userInfo = new UserInfo();
                userInfo.setUserID(cursor.getInt(0));
                userInfo.setUserName(cursor.getString(1));
                userInfo.setUserAge(cursor.getInt(2));
                userInfo.setUserBloodGroup(cursor.getString(3));
                userInfo.setUserContactNum(cursor.getString(4));
                userInfo.setUserLocation(cursor.getString(5));
                userInfo.setDay1(cursor.getInt(6));
                userInfo.setDay2(cursor.getInt(7));
                userInfo.setDay3(cursor.getInt(8));
                userInfoList.add(userInfo);
            } while (cursor.moveToNext());
        }
        return userInfoList;

    }

    public List<UserInfo> getUserListPresentForDay(String dayColumnName) {
        List<UserInfo> userInfoList = new ArrayList<UserInfo>();
        String selectQuery = "select  * from  user where "+ dayColumnName +"=2";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                UserInfo userInfo = new UserInfo();
                userInfo.setUserID(cursor.getInt(0));
                userInfo.setUserName(cursor.getString(1));
                userInfo.setUserAge(cursor.getInt(2));
                userInfo.setUserBloodGroup(cursor.getString(3));
                userInfo.setUserContactNum(cursor.getString(4));
                userInfo.setUserLocation(cursor.getString(5));
                userInfoList.add(userInfo);
            } while (cursor.moveToNext());
        }
        return userInfoList;

    }


    public UserInfo getUserInfoFromUsrId(int userId) {
        UserInfo userInfo = new UserInfo();
        String selectQuery = "select  * from  user where _id="+ userId;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                userInfo.setUserID(cursor.getInt(0));
                userInfo.setUserName(cursor.getString(1));
                userInfo.setUserAge(cursor.getInt(2));
                userInfo.setUserBloodGroup(cursor.getString(3));
                userInfo.setUserContactNum(cursor.getString(4));
                userInfo.setUserLocation(cursor.getString(5));
                userInfo.setDay1(cursor.getInt(6));
                userInfo.setDay2(cursor.getInt(7));
                userInfo.setDay3(cursor.getInt(8));

            } while (cursor.moveToNext());
        }
        return userInfo;
    }

    public void updateUserPresent(String dayName, int userId, int count) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        if(count == 1) {
            values.put(dayName, 2);
        }else {
            values.put(dayName, 1);
        }
        sqLiteDatabase.update("user", values, "_id="+userId, null);
        return ;
    }





    public UserInfo updateUserInfoFromUsrId(UserInfo userInfo) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", userInfo.getUserName());
        values.put("age", userInfo.getUserAge());
        values.put("bloodGroup", userInfo.getUserBloodGroup());
        values.put("contactNum", userInfo.getUserContactNum());
        values.put("location", userInfo.getUserLocation());
        values.put("day1", userInfo.getDay1());
        values.put("day2", userInfo.getDay2());
        values.put("day3", userInfo.getDay3());

        sqLiteDatabase.update("user", values, "_id="+userInfo.getUserID(), null);
        return getUserInfoFromUsrId(userInfo.getUserID());
    }

    public int getNotificationCount() {
        String countQuery = "select * from user";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        // return count
        return cursor.getCount();
    }
    public void dropTables(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS user;");
    }

    public void truncateTables(SQLiteDatabase db) {
        db.execSQL("DELETE from user;");
    }
}
