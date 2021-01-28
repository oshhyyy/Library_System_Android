package com.example.joshcortez.qr_scanner_v1;

import android.app.Application;
import com.firebase.client.Firebase;
/**
 * Created by Josh Cortez on 14/08/2018.
 */

public class FirebaseApp extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
