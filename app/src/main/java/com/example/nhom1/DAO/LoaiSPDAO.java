package com.example.nhom1.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.nhom1.database.DbHelper;
import com.example.nhom1.model.LoaiSP;

import java.util.ArrayList;
import java.util.List;

public class LoaiSPDAO {
    SQLiteDatabase db;
    DbHelper dbHelper;

    public LoaiSPDAO(Context context) {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    @SuppressLint("Range")
    public LoaiSP getOneLoaiSP(int id) {
        LoaiSP loaiSP = null;
        try {
            db = dbHelper.getReadableDatabase();
            String query = "SELECT * FROM LOAISANPHAM WHERE id_loaisp = ?";
            Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(id)});

            if (cursor.moveToFirst()) {
                loaiSP = new LoaiSP();
                loaiSP.setId_loaisp(cursor.getInt(cursor.getColumnIndex("id_loaisp")));
                loaiSP.setTen_loaisp(cursor.getString(cursor.getColumnIndex("ten_loaisp")));
            }

            cursor.close();
        } catch (Exception ex) {
            Log.e("READ_ONE_ERROR", ex.getMessage());
        } finally {
            if (db != null && db.isOpen()) {
                db.close();
            }
        }

        return loaiSP;
    }

    public boolean insertLoaiSP(LoaiSP loaiSP) {


        ContentValues values = new ContentValues();
        values.put("ten_loaisp", loaiSP.getTen_loaisp());
        long result = db.insert("LOAISANPHAM", null, values);
        return result != -1;
    }

    @SuppressLint("Range")
    public List<LoaiSP> getAllLoaiSP() {
        List<LoaiSP> loaiSPList = new ArrayList<>();
        try {
            db = dbHelper.getReadableDatabase();
            String query = "SELECT * FROM LOAISANPHAM";
            Cursor cursor = db.rawQuery(query, null);

            while (cursor.moveToNext()) {
                LoaiSP loaiSP = new LoaiSP();
                loaiSP.setId_loaisp(cursor.getInt(cursor.getColumnIndex("id_loaisp")));
                loaiSP.setTen_loaisp(cursor.getString(cursor.getColumnIndex("ten_loaisp")));

                loaiSPList.add(loaiSP);
            }

            cursor.close();
        } catch (Exception ex) {
            Log.e("READ_ERROR", ex.getMessage());
        } finally {
            if (db != null && db.isOpen()) {
                db.close();
            }
        }

        return loaiSPList;
    }

    public boolean updateLoaiSP(LoaiSP loaiSP) {
        try {
            db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("ten_loaisp", loaiSP.getTen_loaisp());

            int rowsAffected = db.update("LOAISANPHAM", values, "id_loaisp = ?", new String[]{String.valueOf(loaiSP.getId_loaisp())}
            );

            return rowsAffected > 0;
        } catch (Exception ex) {
            Log.e("UPDATE_ERROR", ex.getMessage());
            return false;
        } finally {
            if (db != null && db.isOpen()) {
                db.close();
            }
        }
    }

    public boolean deleteLoaiSP(int id) {
        try {
            db = dbHelper.getWritableDatabase();
            // Xóa tất cả sản phẩm thuộc loại sản phẩm bị xóa
            db.delete("SANPHAM", "id_loaisp = ?", new String[]{String.valueOf(id)});

            // Sau đó xóa loại sản phẩm
            int rowsAffected = db.delete("LOAISANPHAM", "id_loaisp = ?", new String[]{String.valueOf(id)});
            return rowsAffected > 0;
        } catch (Exception ex) {
            Log.e("DELETE_ERROR", ex.getMessage());
            return false;
        } finally {
            if (db != null && db.isOpen()) {
                db.close();
            }
        }
    }

}
