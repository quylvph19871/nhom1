package com.example.nhom1;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.nhom1.Fragment.Admin.Fragment_quanLyDonHang;
import com.example.nhom1.Fragment.Admin.Fragment_quanLySanPham;
import com.example.nhom1.Fragment.Admin.Fragment_thongKe;
import com.example.nhom1.Fragment.TaiKhoanFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainAdminActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    public static BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);
        bottomNavigationView = findViewById(R.id.bottomnavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.quanLySP);
        load(new Fragment_quanLySanPham());

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment;
        if (item.getItemId() == R.id.quanLySP ) {
            fragment = new Fragment_quanLySanPham();
            load(fragment);
            return true;
        } else if (item.getItemId() == R.id.quanLyDH) {
            fragment = new Fragment_quanLyDonHang();
            load(fragment);
            return true;
        } else if (item.getItemId() == R.id.thongKe) {
            fragment = new Fragment_thongKe();
            load(fragment);
            return true;
        } else if (item.getItemId() == R.id.taikhoanAd) {
            fragment = new TaiKhoanFragment();
            load(fragment);
            return true;
        } else {
            return false;
        }
    }

    public void load(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_containerAD, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}