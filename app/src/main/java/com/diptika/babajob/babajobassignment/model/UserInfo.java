package com.diptika.babajob.babajobassignment.model;

import java.io.Serializable;

/**
 * Created by diptika.s on 15/05/16.
 */
public class UserInfo implements Serializable{
   public int userID;
   public String userName;
   public int userAge;
   public String userBloodGroup;
   public String userContactNum;
   public String userLocation;
   public int day1,day2,day3;


    public UserInfo() {}

    public UserInfo(int userID, String userName, int userAge, String userBloodGroup, String userContactNum, String userLocation, int day1, int day2, int day3) {
        this.userID = userID;
        this.userName = userName;
        this.userAge = userAge;
        this.userBloodGroup = userBloodGroup;
        this.userContactNum = userContactNum;
        this.userLocation = userLocation;
        this.day1 = day1;
        this.day2 = day2;
        this.day3 = day3;
    }



    public UserInfo( String userName, int userAge, String userBloodGroup, String userContactNum, String userLocation, int day1, int day2, int day3) {

        this.userName = userName;
        this.userAge = userAge;
        this.userBloodGroup = userBloodGroup;
        this.userContactNum = userContactNum;
        this.userLocation = userLocation;
        this.day1 = day1;
        this.day2 = day2;
        this.day3 = day3;
    }

    @Override
    public String toString(){
        return "Id - "+userID+" Name - "+userName+" Age - "+userAge+" BloodGroup - "+userBloodGroup+" ContactNumber - "+userContactNum+" Location - "+userLocation;
    }


    public int getDay1() {
        return day1;
    }

    public void setDay1(int day1) {
        this.day1 = day1;
    }

    public int getDay2() {
        return day2;
    }

    public void setDay2(int day2) {
        this.day2 = day2;
    }

    public int getDay3() {
        return day3;
    }

    public void setDay3(int day3) {
        this.day3 = day3;
    }


    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public String getUserBloodGroup() {
        return userBloodGroup;
    }

    public void setUserBloodGroup(String userBloodGroup) {
        this.userBloodGroup = userBloodGroup;
    }

    public String getUserContactNum() {
        return userContactNum;
    }

    public void setUserContactNum(String userContactNum) {
        this.userContactNum = userContactNum;
    }

    public String getUserLocation() {
        return userLocation;
    }

    public void setUserLocation(String userLocation) {
        this.userLocation = userLocation;
    }
}
