package com.example.demo.Model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "giohang", schema = "new_schema", catalog = "")
public class GiohangEntity {
    @Id
    @Column(name = "MaGioHang")
    private int maGioHang;

    public Integer getMaThanhVien() {
        return maThanhVien;
    }

    public void setMaThanhVien(Integer maThanhVien) {
        this.maThanhVien = maThanhVien;
    }

    @Basic
    @Column(name = "MaSanPham")
    private Integer maSanPham;
    @Basic
    @Column(name = "SoLuong")
    private Integer soLuong;
    @Basic
    @Column(name = "NgayTaoGioHang")
    private Date ngayTaoGioHang;

    @Basic
    @Column(name = "MaThanhVien")
    private Integer maThanhVien;

    private String tenSanPham;
    private Float giaSanPham;
    private String img;

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public Float getGiaSanPham() {
        return giaSanPham;
    }

    public void setGiaSanPham(Float giaSanPham) {
        this.giaSanPham = giaSanPham;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getMaGioHang() {
        return maGioHang;
    }

    public void setMaGioHang(int maGioHang) {
        this.maGioHang = maGioHang;
    }

    public Integer getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(Integer maSanPham) {
        this.maSanPham = maSanPham;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public Date getNgayTaoGioHang() {
        return ngayTaoGioHang;
    }

    public void setNgayTaoGioHang(Date ngayTaoGioHang) {
        this.ngayTaoGioHang = ngayTaoGioHang;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GiohangEntity that = (GiohangEntity) o;

        if (maGioHang != that.maGioHang) return false;
        if (maSanPham != null ? !maSanPham.equals(that.maSanPham) : that.maSanPham != null) return false;
        if (soLuong != null ? !soLuong.equals(that.soLuong) : that.soLuong != null) return false;
        if (ngayTaoGioHang != null ? !ngayTaoGioHang.equals(that.ngayTaoGioHang) : that.ngayTaoGioHang != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = maGioHang;
        result = 31 * result + (maSanPham != null ? maSanPham.hashCode() : 0);
        result = 31 * result + (soLuong != null ? soLuong.hashCode() : 0);
        result = 31 * result + (ngayTaoGioHang != null ? ngayTaoGioHang.hashCode() : 0);
        return result;
    }
}
