package com.example.baitaplon;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.FileInputStream;

public class FirebaseAuth {
    public static void main(String[] args) {
        // Đường dẫn đến tệp cấu hình của bạn
        try {
            String pathToServiceAccountKey = "E:/Data_Lap_Trinh/OOP/oasis/baiTapLon/src/main/java/com/example/baitaplon/dataloginapp3t.json";

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(new FileInputStream(pathToServiceAccountKey)))
                    .setDatabaseUrl("https://data-login-app-3t-default-rtdb.firebaseio.com/") // Thay đổi thành URL của bạn
                    .build();

            FirebaseApp.initializeApp(options);

            // Bây giờ bạn đã khởi tạo Firebase SDK và có thể sử dụng nó cho các hoạt động với Firebase.
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }

        // Tạo đường dẫn đến node "test" (chú ý, đừng có thêm "/test" vào đường dẫn URL)
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("test");

        // Thêm dữ liệu vào node "test"
        DatabaseReference newUserRef = databaseReference.push(); // Tạo một node con mới
//        newUserRef.child("name").setValue("John");
//        newUserRef.child("email").setValue("john@example.com");
    }
}

//public class FirebaseAuth {
//    public static void main(String[] args) {
//        try {
//            // Initialize Firebase Admin SDK with your service account credentials JSON file
//            FileInputStream serviceAccount = new FileInputStream("data/data-login-app-3t-firebase-adminsdk-dut9u-4914318df0.json");
//            FirebaseOptions options = new FirebaseOptions.Builder()
//                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//                    .setDatabaseUrl("https://data-login-app-3t-default-rtdb.firebaseio.com/")
//                    .build();
//
//            FirebaseApp.initializeApp(options);
//
//            // Get a reference to the Realtime Database
//            DatabaseReference database = FirebaseDatabase.getInstance().getReference();
//
//            // Set data in the database
//            Map<String, Object> data = new HashMap<>();
//            data.put("key", "value");
//
//            // Specify the database path where you want to set the data
//            DatabaseReference ref = database.child("/");
//            ref.setValue(data, new DatabaseReference.CompletionListener() {
//                @Override
//                public void onComplete(DatabaseError error, DatabaseReference ref) {
//                    if (error == null) {
//                        System.out.println("Data was successfully written to the database.");
//                    } else {
//                        System.err.println("Data could not be written to the database: " + error.getMessage());
//                    }
//                }
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}

