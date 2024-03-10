package com.example.obstaclerace;

import android.app.Application;
import com.google.gson.Gson;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize the MSPV3 class
        MSPV3.initHelper(this);

        // Retrieve the JSON string from SharedPreferences
        String json = MSPV3.getMe().getString("InternalDB", "");

        // Deserialize the JSON string into a MyDB object using Gson
        InternalDB internalDB = new Gson().fromJson(json, InternalDB.class);

        // Check if the deserialized object is null
        if (internalDB == null) {
            // Create a new instance of MyDB
            internalDB = new InternalDB();

            // Serialize the MyDB object into a JSON string using Gson
            String jsonString = new Gson().toJson(internalDB);
            MSPV3.getMe().putString("InternalDB", jsonString);
        }
    }
}