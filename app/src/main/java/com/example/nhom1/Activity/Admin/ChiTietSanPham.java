package com.example.nhom1.Activity.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nhom1.DAO.LoaiSPDAO;
import com.example.nhom1.DAO.SanPhamDAO;
import com.example.nhom1.R;
import com.example.nhom1.model.LoaiSP;
import com.example.nhom1.model.SanPham;

public class ChiTietSanPham extends AppCompatActivity {
    private SanPham sanPham;
    private SanPhamDAO sanPhamDAO;
    private TextView ten,mota,gia,loai;
    private ImageView exit,anh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);
        ten = findViewById(R.id.tv_tensp);
        mota = findViewById(R.id.tv_moTa);
        loai = findViewById(R.id.tv_loaisp);
        gia = findViewById(R.id.tv_gia);
        anh = findViewById(R.id.anh_sp);
        exit = findViewById(R.id.btn_exitct);

        sanPhamDAO = new SanPhamDAO(this);
        Bundle bundle = getIntent().getExtras();
        int idSanPham = bundle.getInt("id_sp");
        sanPham = sanPhamDAO.getOneSanPham(idSanPham);
        LoaiSPDAO loaiSPDAO = new LoaiSPDAO(this);
        LoaiSP loaiSP = loaiSPDAO.getOneLoaiSP(sanPham.getId_loaisp());
        ten.setText(sanPham.getTen_sp());
        loai.setText(loaiSP.getTen_loaisp());
        mota.setText(sanPham.getMota_sp());
        gia.setText(sanPham.getGiatien_sp()+"");
        anh.setImageBitmap(BitmapFactory.decodeByteArray(sanPham.getAnh_sp(), 0, sanPham.getAnh_sp().length));

    }
}