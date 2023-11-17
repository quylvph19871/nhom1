package com.example.nhom1.Fragment;

import static android.app.Activity.RESULT_OK;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhom1.Adapter.SanPhamAdapter;
import com.example.nhom1.DAO.SanPhamDAO;
import com.example.nhom1.R;
import com.example.nhom1.database.DbHelper;
import com.example.nhom1.model.SanPham;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Fragment_sanPham extends Fragment {
    RecyclerView rcv_dsSp;
    ImageView btn_add_SanPham;
   SanPhamDAO dao;
   List<SanPham >list;
   SanPhamAdapter adapter;
    private ImageView img_anhSP;
    private Bitmap selectedImageBitmap;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sanpham,container,false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcv_dsSp=view.findViewById(R.id.rcv_dsSanPham);
        btn_add_SanPham=view.findViewById(R.id.btn_addSP);
        list=new ArrayList<>();
        dao=new SanPhamDAO(getContext());
        SanPhamAdapter adapter = new SanPhamAdapter(getContext(), list);
        rcv_dsSp.setAdapter(adapter);

        rcv_dsSp.setLayoutManager(new LinearLayoutManager(getContext()));
        btn_add_SanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog=new Dialog(getActivity());
                dialog.setContentView(R.layout.dialog_them_sanpham);
                TextInputEditText ed_tenSP=dialog.findViewById(R.id.ed_tenSP);
                TextInputEditText ed_motaSP= dialog.findViewById(R.id.ed_Mota);
                TextInputEditText ed_Gia=dialog.findViewById(R.id.ed_giaSP);
                ImageView img_anhSP=dialog.findViewById(R.id.img_themAnhSP);
                img_anhSP.setOnClickListener(new View.OnClickListener() {
                       @Override
                           public void onClick(View view) {
                           Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                           startActivityForResult(intent, 1);
                       }
                });
                Button btn=dialog.findViewById(R.id.btn_themSpham);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String tenSP=ed_tenSP.getText().toString();
                        String moTaSP=ed_motaSP.getText().toString();
                        String giaSP=ed_Gia.getText().toString();

                        SanPham obj=new SanPham();
                        obj.setTen_sp(tenSP);
                        obj.setMota_sp(moTaSP);
                        obj.setGiatien_sp(Double.parseDouble(giaSP));
                        obj.setAnh_sp(getBytesFromBitmap(selectedImageBitmap));
                       long SanPham =dao.insertSanPham(obj);
                        if(SanPham >0 ){
                            list.clear();
                            list.addAll(dao.getAllSanPham());
                            adapter.notifyDataSetChanged();
                            dialog.dismiss();
                            Toast.makeText(getContext(),"Thêm thành công",Toast.LENGTH_SHORT).show();;
                        }else{
                            Toast.makeText(getContext(), "Lỗi, xem log đi", Toast.LENGTH_SHORT).show();
                        }
                    }

                });
                dialog.show();
            }
        });

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == getActivity().RESULT_OK && data != null) {
            try {
                selectedImageBitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), data.getData());
                img_anhSP.setImageBitmap(selectedImageBitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Convert Bitmap to byte array
    private byte[] getBytesFromBitmap(Bitmap bitmap) {
        if (bitmap != null) {
            int size = bitmap.getRowBytes() * bitmap.getHeight();
            byte[] stream = new byte[size];
            bitmap.copyPixelsToBuffer(java.nio.ByteBuffer.wrap(stream));
            return stream;
        }
        return null;
    }

}
