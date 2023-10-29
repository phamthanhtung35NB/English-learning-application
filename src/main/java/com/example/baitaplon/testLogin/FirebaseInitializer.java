package com.example.baitaplon.testLogin;


import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

public class FirebaseInitializer {
    public static void initialize() {
        try {
            // Load the service account key file
            GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream("path/to/your/serviceAccountKey.json"));

            // Initialize Firebase with the service account credentials
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(credentials)
                    .setDatabaseUrl("https://your-database-name.firebaseio.com/")
                    .build();
            FirebaseApp.initializeApp(options);
        } catch (Exception e) {
            e.printStackTrace(); // Handle or log the exception
        }
    }
}

