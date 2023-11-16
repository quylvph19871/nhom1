package com.example.nhom1.model;

public class HoaDonCT {
    private int id_chitiet;
    private int id_hoadon;
    private int id_user;
    private String ten_user;
    private String tenkhachhang;
    private String ngayinhoadon;
    private int id_sp;
    private int soluong;
    private String kichco;
    private double dongia;
    private double tongtien;

    public HoaDonCT(int id_chitiet, int id_hoadon, int id_user, String ten_user, String tenkhachhang, String ngayinhoadon, int id_sp, int soluong, String kichco, double dongia, double tongtien) {
        this.id_chitiet = id_chitiet;
        this.id_hoadon = id_hoadon;
        this.id_user = id_user;
        this.ten_user = ten_user;
        this.tenkhachhang = tenkhachhang;
        this.ngayinhoadon = ngayinhoadon;
        this.id_sp = id_sp;
        this.soluong = soluong;
        this.kichco = kichco;
        this.dongia = dongia;
        this.tongtien = tongtien;
    }

    public HoaDonCT() {
    }

    public int getId_chitiet() {
        return id_chitiet;
    }

    public void setId_chitiet(int id_chitiet) {
        this.id_chitiet = id_chitiet;
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

    public String getTen_user() {
        return ten_user;
    }

    public void setTen_user(String ten_user) {
        this.ten_user = ten_user;
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

    public int getId_sp() {
        return id_sp;
    }

    public void setId_sp(int id_sp) {
        this.id_sp = id_sp;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public String getKichco() {
        return kichco;
    }

    public void setKichco(String kichco) {
        this.kichco = kichco;
    }

    public double getDongia() {
        return dongia;
    }

    public void setDongia(double dongia) {
        this.dongia = dongia;
    }

    public double getTongtien() {
        return tongtien;
    }

    public void setTongtien(double tongtien) {
        this.tongtien = tongtien;
    }
}
