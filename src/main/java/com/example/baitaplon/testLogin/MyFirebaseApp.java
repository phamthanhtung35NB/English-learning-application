//package com.example.baitaplon.testLogin;
//
//import com.google.firebase.database.*;
//
//public class MyFirebaseApp {
//    public static void main(String[] args) {
//        FirebaseInitializer.initialize();
//
//        // Get a reference to your database
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference ref = database.getReference("your-data-path");
//
//        // Write data
//        ref.child("key").setValue("value");
//
//        // Read data
//        ref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Object data = dataSnapshot.getValue();
//                System.out.println("Data: " + data);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                System.out.println("Error: " + databaseError.getMessage());
//            }
//        });
//    }
//}
