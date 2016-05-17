package com.diptika.babajob.babajobassignment;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.diptika.babajob.babajobassignment.model.UserInfo;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.List;

/**
 * Created by diptika.s on 15/05/16.
 */

public class ParseApplication extends Application {
    public final String APPLICATION_ID = "APPLICATION_ID";
    public final String CLIENT_KEY = "***REDACTED***";
    public final String parseServerIP = "http://localhost:1337/parse/";

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this);
        Parse.initialize(new Parse.Configuration.Builder(getBaseContext())
                .applicationId(APPLICATION_ID)
                .clientKey(CLIENT_KEY)
                .server(parseServerIP)
                .build());
    }
}
