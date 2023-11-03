package com.example.baitaplon;
//import android.content.Context;
//import android.util.Log;
//
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//
//public class FirebaseAuth {
//
//    private static final String TAG = "FirebaseAuthHelper";
//
//    private FirebaseAuth mAuth;
//
//    public FirebaseAuthHelper(Context context) {
//        mAuth = FirebaseAuth.getInstance();
//    }
//
//    public boolean checkCredentials(String email, String password) {
//        FirebaseUser user = mAuth.getCurrentUser();
//        if (user == null) {
//            // Người dùng chưa đăng nhập
//            return false;
//        }
//
//        // Kiểm tra email và mật khẩu
//        if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    public void signInWithCredentials(String email, String password) {
//        mAuth.signInWithEmailAndPassword(email, password)
//                .addOnCompleteListener(task -> {
//                    if (task.isSuccessful()) {
//                        // Đăng nhập thành công
//                        Log.d(TAG, "Đăng nhập thành công");
//                    } else {
//                        // Đăng nhập thất bại
//                        Log.d(TAG, "Đăng nhập thất bại");
//                    }
//                });
//    }
//}

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.FirebaseApp;

public class FirebaseAuth {
    FirebaseDatabase.getInstance().getReference().push().setValue(<object_cua_ban>);

    //    FirebaseDatabase.getInstance().getReference().child("ABC").push().setValue(<object_cua_ban>);
// Lấy ra keyID
    String keyID = FirebaseDatabase.getInstance().getReference().push().getKey();

// set key vao object
<object_cua_ban>.setKey(keyID); // lưu lại để sau này dùng chẳng hạn

// set data
FirebaseDatabase.getInstance().getReference().child(keyID).setValue(<object_cua_ban>);
}
