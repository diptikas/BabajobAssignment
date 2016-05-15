package com.diptika.babajob.babajobassignment;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseInstallation;

/**
 * Created by diptika.s on 15/05/16.
 */

public class ParseApplication extends Application {
    public static final String YOUR_APPLICATION_ID = "AERqqIXGvzH7Nmg45xa5T8zWRRjqT8UmbFQeeI";
    public static final String YOUR_CLIENT_KEY = "8bXPznF5eSLWq0sY9gTUrEF5BJlia7ltmLQFRh";

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, YOUR_APPLICATION_ID, YOUR_CLIENT_KEY);
        ParseInstallation.getCurrentInstallation().saveInBackground();


    }

}
