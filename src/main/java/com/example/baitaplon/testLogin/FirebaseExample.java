package com.example.baitaplon.testLogin;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutures;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.api.core.ApiFutureCallback;
import com.google.common.util.concurrent.MoreExecutors;


public class FirebaseExample {
    public static void main(String[] args) {
        FirebaseInitializer.initialize();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("your-data-path");

        // Set a String value asynchronously
        ApiFuture<Void> future = ref.setValueAsync("YourStringData");

        ApiFutures.addCallback(future, new ApiFutureCallback<Void>() {
            @Override
            public void onFailure(Throwable t) {
                System.out.println("Error setting value: " + t.getMessage());
            }

            @Override
            public void onSuccess(Void result) {
                System.out.println("Value set successfully");
            }
        }, MoreExecutors.directExecutor());
    }
}
