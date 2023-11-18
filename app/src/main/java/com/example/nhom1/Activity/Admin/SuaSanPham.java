package com.example.nhom1.Activity.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.nhom1.Adapter.LoaiSPAdapter;
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

public class SuaSanPham extends AppCompatActivity {
    private SanPham sanPham;

    private TextInputEditText ed_tenSP, ed_motaSP, ed_Gia;
    private ImageView img_anhSP,btn_exit;
    private Button btn;
    private Spinner spn_loaiSanPham;
    private SanPhamDAO sanPhamDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_san_pham);
        ed_tenSP = findViewById(R.id.ed_tenSP);
        ed_motaSP = findViewById(R.id.ed_Mota);
        ed_Gia = findViewById(R.id.ed_giaSP);
        img_anhSP = findViewById(R.id.img_themAnhSP);
        btn_exit = findViewById(R.id.btn_exit);
        btn = findViewById(R.id.btn_themSpham);
        spn_loaiSanPham = findViewById(R.id.spn_loaiSanPham);
        sanPhamDAO = new SanPhamDAO(this);
        Bundle bundle = getIntent().getExtras();
        int idSanPham = bundle.getInt("id_sp");
        sanPham = sanPhamDAO.getOneSanPham(idSanPham);
        LoaiSPDAO loaiSPDAO = new LoaiSPDAO(this);
        List<LoaiSP> list = loaiSPDAO.getAllLoaiSP();
        LoaiSPAdapter adapter = new LoaiSPAdapter(this, list);
        spn_loaiSanPham.setAdapter(adapter);


//              Hiển thị dữ liệu cũ lên dialog
        for (int i = 0; i < list.size(); i++) {
            LoaiSP loaiSP = list.get(i);
            if (loaiSP.getId_loaisp() == (sanPham.getId_loaisp())) {
                spn_loaiSanPham.setSelection(i);
                break;
            }
        }
        img_anhSP.setImageBitmap(BitmapFactory.decodeByteArray(sanPham.getAnh_sp(), 0, sanPham.getAnh_sp().length));
        ed_tenSP.setText(sanPham.getTen_sp());
        ed_motaSP.setText(sanPham.getMota_sp());
        ed_Gia.setText(sanPham.getGiatien_sp() + "");
        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
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
//                Set giá trị vào đối tượng SanPham
                sanPham.setGiatien_sp(Double.parseDouble(gia));
                sanPham.setMota_sp(moTa);
                sanPham.setTen_sp(tenSp);
                sanPham.setId_loaisp(loaiSP.getId_loaisp());
                SanPhamDAO dao = new SanPhamDAO(SuaSanPham.this);
                if (dao.updateSanPham(sanPham)){
                    Toast.makeText(SuaSanPham.this, "Sửa thành công!", Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(SuaSanPham.this, "Sửa thất bại!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            img_anhSP.setImageURI(imageUri);
            try {
                InputStream inputStream = getContentResolver().openInputStream(imageUri);
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
}
