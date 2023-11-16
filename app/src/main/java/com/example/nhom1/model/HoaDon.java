package com.example.nhom1.model;

public class HoaDon {
    private int id_hoadon;
    private int id_user;
    private int id_giohang;
    private String tenkhachhang;
    private String ngayinhoadon;

    public HoaDon(int id_hoadon, int id_user, int id_giohang, String tenkhachhang, String ngayinhoadon) {
        this.id_hoadon = id_hoadon;
        this.id_user = id_user;
        this.id_giohang = id_giohang;
        this.tenkhachhang = tenkhachhang;
        this.ngayinhoadon = ngayinhoadon;
    }

    public HoaDon() {
    }

    public int getId_hoadon() {
        return id_hoadon;
    }

    public void setId_hoadon(int id_hoadon) {
        this.id_hoadon = id_hoadon;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_giohang() {
        return id_giohang;
    }

    public void setId_giohang(int id_giohang) {
        this.id_giohang = id_giohang;
    }

    public String getTenkhachhang() {
        return tenkhachhang;
    }

    public void setTenkhachhang(String tenkhachhang) {
        this.tenkhachhang = tenkhachhang;
    }

    public String getNgayinhoadon() {
        return ngayinhoadon;
    }

    public void setNgayinhoadon(String ngayinhoadon) {
        this.ngayinhoadon = ngayinhoadon;
    }
}
