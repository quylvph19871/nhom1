package com.example.nhom1.model;

public class GioHang {
    private int id_giohang;
    private int id_sp;
    private int soluong;
    private String kichco;
    private double dongia;

    public GioHang(int id_giohang, int id_sp, int soluong, String kichco, double dongia) {
        this.id_giohang = id_giohang;
        this.id_sp = id_sp;
        this.soluong = soluong;
        this.kichco = kichco;
        this.dongia = dongia;
    }

    public GioHang() {
    }

    public int getId_giohang() {
        return id_giohang;
    }

    public void setId_giohang(int id_giohang) {
        this.id_giohang = id_giohang;
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
}
