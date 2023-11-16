package com.example.nhom1.model;

import java.sql.Blob;

public class SanPham {
    private int id_sp;
    private int id_loaisp;
    private byte[] anh_sp;
    private String ten_sp;
    private String mota_sp;
    private double giatien_sp;

    public SanPham(int id_sp, int id_loaisp, byte[] anh_sp, String ten_sp, String mota_sp, double giatien_sp) {
        this.id_sp = id_sp;
        this.id_loaisp = id_loaisp;
        this.anh_sp = anh_sp;
        this.ten_sp = ten_sp;
        this.mota_sp = mota_sp;
        this.giatien_sp = giatien_sp;
    }

    public SanPham() {
    }

    public int getId_sp() {
        return id_sp;
    }

    public void setId_sp(int id_sp) {
        this.id_sp = id_sp;
    }

    public int getId_loaisp() {
        return id_loaisp;
    }

    public void setId_loaisp(int id_loaisp) {
        this.id_loaisp = id_loaisp;
    }

    public byte[] getAnh_sp() {
        return anh_sp;
    }

    public void setAnh_sp(byte[] anh_sp) {
        this.anh_sp = anh_sp;
    }

    public String getTen_sp() {
        return ten_sp;
    }

    public void setTen_sp(String ten_sp) {
        this.ten_sp = ten_sp;
    }

    public String getMota_sp() {
        return mota_sp;
    }

    public void setMota_sp(String mota_sp) {
        this.mota_sp = mota_sp;
    }

    public double getGiatien_sp() {
        return giatien_sp;
    }

    public void setGiatien_sp(double giatien_sp) {
        this.giatien_sp = giatien_sp;
    }
}
