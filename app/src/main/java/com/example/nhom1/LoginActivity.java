package com.example.nhom1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class LoginActivity extends AppCompatActivity {
    private RadioButton rdoadmin,rdouser;
    private Boolean checkAdmin;
    private AppCompatButton btndangNhap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        rdoadmin = findViewById(R.id.rdoadmin);
        rdouser = findViewById(R.id.rdouser);
        btndangNhap = findViewById(R.id.btndangNhap1);
        checkAdmin = false;
        rdoadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAdmin = true;
            }
        });
        rdouser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAdmin = false;
            }
        });
//      Xử lý khi nhấn nút đăng nhập
        btndangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Check xem đăng nhập với quyền gì với checkAdmin
//                Nếu checkAdmin = true thì chuyển tới màn hình MainAdminActivity, nếu false thì MainActivity
                if(checkAdmin){
                    startActivity(new Intent(LoginActivity.this,MainAdminActivity.class));
                }else{
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                }
            }
        });
    }
}