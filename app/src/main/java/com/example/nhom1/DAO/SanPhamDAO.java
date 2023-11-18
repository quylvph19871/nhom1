package com.example.nhom1.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.nhom1.database.DbHelper;
import com.example.nhom1.model.SanPham;

import java.util.ArrayList;
import java.util.List;

public class SanPhamDAO {
     SQLiteDatabase database;
     DbHelper dbHelper;

    public SanPhamDAO(Context context) {
        dbHelper=new DbHelper(context);
        database=dbHelper.getWritableDatabase();
    }

    public boolean insertSanPham(SanPham sanPham) {
        ContentValues values = new ContentValues();
        values.put("id_loaisp", sanPham.getId_loaisp());
        values.put("anh_sp", sanPham.getAnh_sp());
        values.put("ten_sp", sanPham.getTen_sp());
        values.put("mota_sp", sanPham.getMota_sp());
        values.put("giatien_sp", sanPham.getGiatien_sp()+"");

        long result = database.insert("SANPHAM", null, values);

        return result != -1; // Nếu result khác -1 tức là insert thành công, trả về true, ngược lại trả về false
    }

    @SuppressLint("Range")
    public List<SanPham> getAllSanPham() {
        List<SanPham> sanPhamList = new ArrayList<>();
        try {
            database = dbHelper.getReadableDatabase();
            String query = "SELECT * FROM SANPHAM";
            Cursor cursor = database.rawQuery(query, null);

            while (cursor.moveToNext()) {
                SanPham sanPham = new SanPham();
                sanPham.setId_sp(cursor.getInt(cursor.getColumnIndex("id_sp")));
                sanPham.setId_loaisp(cursor.getInt(cursor.getColumnIndex("id_loaisp")));
                sanPham.setAnh_sp(cursor.getBlob(cursor.getColumnIndex("anh_sp")));
                sanPham.setTen_sp(cursor.getString(cursor.getColumnIndex("ten_sp")));
                sanPham.setMota_sp(cursor.getString(cursor.getColumnIndex("mota_sp")));
                sanPham.setGiatien_sp(cursor.getDouble(cursor.getColumnIndex("giatien_sp")));

                sanPhamList.add(sanPham);
            }

            cursor.close();
        } catch (Exception ex) {
            Log.e("READ_ERROR", ex.getMessage());
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }

        return sanPhamList;
    }
    public boolean updateSanPham(SanPham sanPham) {
        try {
            database = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("id_loaisp", sanPham.getId_loaisp());
            values.put("anh_sp", sanPham.getAnh_sp());
            values.put("ten_sp", sanPham.getTen_sp());
            values.put("mota_sp", sanPham.getMota_sp());
            values.put("giatien_sp", sanPham.getGiatien_sp()+"");

            int rowsAffected = database.update(
                    "SanPham",
                    values,
                    "id_sp = ?",
                    new String[]{String.valueOf(sanPham.getId_sp())}
            );

            return rowsAffected > 0;
        } catch (Exception ex) {
            Log.e("UPDATE_ERROR", ex.getMessage());
            return false;
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }
    }
    public int deleteSanPham(SanPham objSP){
        String [] tham_so = new String[] {objSP.getId_sp() + ""};

        return database.delete("SanPham","id_sp=?", tham_so);
    }
    @SuppressLint("Range")
    public SanPham getOneSanPham(int id) {
        SanPham sanPham = new SanPham();
        try {
            database = dbHelper.getReadableDatabase();
            String query = "SELECT * FROM SANPHAM WHERE id_sp = ?";
            Cursor cursor = database.rawQuery(query, new String[]{String.valueOf(id)});

            if (cursor.moveToFirst()) {
                sanPham.setId_sp(cursor.getInt(cursor.getColumnIndex("id_sp")));
                sanPham.setId_loaisp(cursor.getInt(cursor.getColumnIndex("id_loaisp")));
                sanPham.setAnh_sp(cursor.getBlob(cursor.getColumnIndex("anh_sp")));
                sanPham.setTen_sp(cursor.getString(cursor.getColumnIndex("ten_sp")));
                sanPham.setMota_sp(cursor.getString(cursor.getColumnIndex("mota_sp")));
                sanPham.setGiatien_sp(cursor.getDouble(cursor.getColumnIndex("giatien_sp")));
            }

            cursor.close();
        } catch (Exception ex) {
            Log.e("READ_ERROR", ex.getMessage());
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }

        return sanPham;
    }
    @SuppressLint("Range")
    public List<SanPham> getSanPhamTheoIDLoai(int id_loaisp) {
        List<SanPham> sanPhamList = new ArrayList<>();
        try {
            database = dbHelper.getReadableDatabase();
            String query = "SELECT * FROM SANPHAM WHERE id_loaisp = ?";
            Cursor cursor = database.rawQuery(query, new String[]{String.valueOf(id_loaisp)});

            while (cursor.moveToNext()) {
                SanPham sanPham = new SanPham();
                sanPham.setId_sp(cursor.getInt(cursor.getColumnIndex("id_sp")));
                sanPham.setId_loaisp(cursor.getInt(cursor.getColumnIndex("id_loaisp")));
                sanPham.setAnh_sp(cursor.getBlob(cursor.getColumnIndex("anh_sp")));
                sanPham.setTen_sp(cursor.getString(cursor.getColumnIndex("ten_sp")));
                sanPham.setMota_sp(cursor.getString(cursor.getColumnIndex("mota_sp")));
                sanPham.setGiatien_sp(cursor.getDouble(cursor.getColumnIndex("giatien_sp")));

                sanPhamList.add(sanPham);
            }

            cursor.close();
        } catch (Exception ex) {
            Log.e("READ_ERROR", ex.getMessage());
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }

        return sanPhamList;
    }

}
