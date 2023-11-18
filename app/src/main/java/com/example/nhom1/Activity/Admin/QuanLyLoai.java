package com.example.nhom1.Activity.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nhom1.Adapter.LoaiSanPhamAdapter;
import com.example.nhom1.DAO.LoaiSPDAO;
import com.example.nhom1.Fragment.Admin.Fragment_quanLySanPham;
import com.example.nhom1.R;
import com.example.nhom1.model.LoaiSP;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class QuanLyLoai extends AppCompatActivity {
    FloatingActionButton add;
    RecyclerView recyclerView;
    LoaiSanPhamAdapter adapter;
    ImageView btn_exitLoai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_loai);
        add = findViewById(R.id.add_lsp);
        recyclerView = findViewById(R.id.rcv_loai);
        btn_exitLoai=findViewById(R.id.btn_exitlsp);
        btn_exitLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(QuanLyLoai.this, Fragment_quanLySanPham.class));
            }
        });
        loadData();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(QuanLyLoai.this);
                dialog.setContentView(R.layout.dialog_themloai);
                TextInputEditText inputEditText = dialog.findViewById(R.id.ed_them_tenloai);
                TextInputLayout inputLayout = dialog.findViewById(R.id.edL_them_tenloai);
                Button btn_them = dialog.findViewById(R.id.btn_them_loai);
                Button btn_huy = dialog.findViewById(R.id.btn_huy_loai);
                btn_huy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        finish();
                    }
                });
                btn_them.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String ten = inputEditText.getText().toString().trim();
                        LoaiSPDAO dao = new LoaiSPDAO(QuanLyLoai.this);
                        LoaiSP loaiSP = new LoaiSP();
                        loaiSP.setTen_loaisp(ten);
                        if (dao.insertLoaiSP(loaiSP)){
                            Toast.makeText(QuanLyLoai.this, "Thêm thành công!", Toast.LENGTH_SHORT).show();
                            loadData();
                            dialog.dismiss();
                        }else {
                            Toast.makeText(QuanLyLoai.this, "Thêm thất bại!", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                dialog.show();
            }
        });
    }

    private void loadData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(QuanLyLoai.this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        LoaiSPDAO dao = new LoaiSPDAO(QuanLyLoai.this);
        List<LoaiSP> list = dao.getAllLoaiSP();
        adapter = new LoaiSanPhamAdapter(list,QuanLyLoai.this);
        recyclerView.setAdapter(adapter);
    }
    public void Sua(LoaiSP loaiSP){
        Dialog dialog = new Dialog(QuanLyLoai.this);
        dialog.setContentView(R.layout.dialog_themloai);
        TextView tv_them_loai = dialog.findViewById(R.id.tv_them_loai);
        TextInputEditText inputEditText = dialog.findViewById(R.id.ed_them_tenloai);
        TextInputLayout inputLayout = dialog.findViewById(R.id.edL_them_tenloai);
        Button btn_them = dialog.findViewById(R.id.btn_them_loai);
        Button btn_huy = dialog.findViewById(R.id.btn_huy_loai);
        tv_them_loai.setText("Sửa loại sản phẩm");
        inputEditText.setText(loaiSP.getTen_loaisp());
        btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btn_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ten = inputEditText.getText().toString().trim();
                LoaiSPDAO dao = new LoaiSPDAO(QuanLyLoai.this);
                loaiSP.setTen_loaisp(ten);
                if (dao.updateLoaiSP(loaiSP)){
                    Toast.makeText(QuanLyLoai.this, "Sửa thành công!", Toast.LENGTH_SHORT).show();
                    loadData();
                    dialog.dismiss();
                }else {
                    Toast.makeText(QuanLyLoai.this, "Sửa thất bại!", Toast.LENGTH_SHORT).show();
                }

            }
        });
        dialog.show();
    }
}