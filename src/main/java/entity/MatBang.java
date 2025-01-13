package entity;

import java.util.Date;

public class MatBang {
    private String maMatBang;
    private String trangThai;
    private double dienTich;
    private int tang;
    private String loaiMatBang;
    private long giaTien;
    private Date ngayBatDau;
    private Date ngayKetThuc;

    // Constructor mặc định
    public MatBang() {
    }

    // Constructor đầy đủ tham số
    public MatBang(String maMatBang, String trangThai, double dienTich, int tang,
                   String loaiMatBang, long giaTien, Date ngayBatDau, Date ngayKetThuc) {
        this.maMatBang = maMatBang;
        this.trangThai = trangThai;
        this.dienTich = dienTich;
        this.tang = tang;
        this.loaiMatBang = loaiMatBang;
        this.giaTien = giaTien;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
    }

    public MatBang(String matBangID, String matBangName, String matBangBrand, String matBangDes, int matBangPrice, int listMatBangtock) {
    }

    // Getters và Setters
    public String getMaMatBang() {
        return maMatBang;
    }

    public void setMaMatBang(String maMatBang) {
        this.maMatBang = maMatBang;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public double getDienTich() {
        return dienTich;
    }

    public void setDienTich(double dienTich) {
        this.dienTich = dienTich;
    }

    public int getTang() {
        return tang;
    }

    public void setTang(int tang) {
        this.tang = tang;
    }

    public String getLoaiMatBang() {
        return loaiMatBang;
    }

    public void setLoaiMatBang(String loaiMatBang) {
        this.loaiMatBang = loaiMatBang;
    }

    public long getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(long giaTien) {
        this.giaTien = giaTien;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    @Override
    public String toString() {
        return "MatBang{" +
                "maMatBang='" + maMatBang + '\'' +
                ", trangThai='" + trangThai + '\'' +
                ", dienTich=" + dienTich +
                ", tang=" + tang +
                ", loaiMatBang='" + loaiMatBang + '\'' +
                ", giaTien=" + giaTien +
                ", ngayBatDau=" + ngayBatDau +
                ", ngayKetThuc=" + ngayKetThuc +
                '}';
    }
}
