package com.example.nhom1.model;

public class User {
    private int id_user;
    private String ten_user;
    private String password;
    private String sdt;
    private String diachi;

    public User() {
    }

    public User(int id_user, String ten_user, String password, String sdt, String diachi) {
        this.id_user = id_user;
        this.ten_user = ten_user;
        this.password = password;
        this.sdt = sdt;
        this.diachi = diachi;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }
}
