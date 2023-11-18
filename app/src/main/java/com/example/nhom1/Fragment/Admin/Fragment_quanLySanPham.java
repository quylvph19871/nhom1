package com.example.nhom1.Fragment.Admin;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhom1.Activity.Admin.QuanLyLoai;
import com.example.nhom1.Adapter.LoaiSPAdapter;
import com.example.nhom1.Adapter.SanPhamAdapter;
import com.example.nhom1.DAO.LoaiSPDAO;
import com.example.nhom1.DAO.SanPhamDAO;
import com.example.nhom1.R;
import com.example.nhom1.model.LoaiSP;
import com.example.nhom1.model.SanPham;
import com.google.android.material.textfield.TextInputEditText;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Fragment_quanLySanPham extends Fragment {
    private ImageView btn_addSP, img_anhSP;
    private SanPham sanPham;
    private RecyclerView rcv_dsSanPhamAD;
    private SanPhamAdapter adapter;
    private List<SanPham> list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_quan_ly_sp, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn_addSP = view.findViewById(R.id.btn_addSP);
        rcv_dsSanPhamAD = view.findViewById(R.id.rcv_dsSanPhamAD);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        rcv_dsSanPhamAD.setLayoutManager(layoutManager);
        loadData();
        btn_addSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialogMenu = new Dialog(getContext());
                dialogMenu.setContentView(R.layout.menu_add);
                TextView them_sp = dialogMenu.findViewById(R.id.menu_them_sp);
                TextView qll = dialogMenu.findViewById(R.id.menu_qll);
                qll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogMenu.dismiss();
                        startActivity(new Intent(getContext(), QuanLyLoai.class));
                    }
                });
                them_sp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogMenu.dismiss();
                        final Dialog dialog = new Dialog(getContext());
                        dialog.setContentView(R.layout.dialog_them_sanpham);
                        sanPham = new SanPham();
                        TextInputEditText ed_tenSP = dialog.findViewById(R.id.ed_tenSP);
                        TextInputEditText ed_motaSP = dialog.findViewById(R.id.ed_Mota);
                        TextInputEditText ed_Gia = dialog.findViewById(R.id.ed_giaSP);
                        img_anhSP = dialog.findViewById(R.id.img_themAnhSP);
                        Spinner spn_loaiSanPham = dialog.findViewById(R.id.spn_loaiSanPham);
                        Button btn = dialog.findViewById(R.id.btn_themSpham);
                        LoaiSPDAO loaiSPDAO = new LoaiSPDAO(getContext());
                        List<LoaiSP> list = loaiSPDAO.getAllLoaiSP();
                        LoaiSPAdapter adapter = new LoaiSPAdapter(getContext(), list);
                        spn_loaiSanPham.setAdapter(adapter);

                        img_anhSP.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                                intent.setType("image/*");
                                startActivityForResult(intent, 1);
                            }
                        });
                        btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
//                      Lấy ra loại sản phẩm đc chọn ở spinner
                                LoaiSP loaiSP = (LoaiSP) spn_loaiSanPham.getSelectedItem();
//                      Lấy ra giá trị của các thuộc tính đã nhập
                                String tenSp = ed_tenSP.getText().toString().trim();
                                String moTa = ed_motaSP.getText().toString().trim();
                                String gia = ed_Gia.getText().toString().trim();
//                      Set giá trị vào đối tượng SanPham
                                sanPham.setGiatien_sp(Double.parseDouble(gia));
                                sanPham.setMota_sp(moTa);
                                sanPham.setTen_sp(tenSp);
                                sanPham.setId_loaisp(loaiSP.getId_loaisp());
//                      Thêm sản phẩm
                                SanPhamDAO dao = new SanPhamDAO(getContext());
                                if (dao.insertSanPham(sanPham)) {
                                    Toast.makeText(getContext(), "Thêm thành công!", Toast.LENGTH_SHORT).show();
                                    loadData();
                                    dialog.dismiss();
                                } else {
                                    Toast.makeText(getContext(), "Thêm thất bại!", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
                        dialog.show();

                    }
                });
                dialogMenu.show();
            }
        });

    }

    private void loadData() {
        SanPhamDAO dao = new SanPhamDAO(getContext());
        list = dao.getAllSanPham();
        adapter = new SanPhamAdapter(getContext(), list);
        rcv_dsSanPhamAD.setAdapter(adapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            img_anhSP.setImageURI(imageUri);
            try {
                InputStream inputStream = getActivity().getContentResolver().openInputStream(imageUri);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    byteArrayOutputStream.write(buffer, 0, bytesRead);
                }
                byte[] bytes = byteArrayOutputStream.toByteArray();
                sanPham.setAnh_sp(bytes);
                inputStream.close();
                byteArrayOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }
}
