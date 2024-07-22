package com.example.demo.Repository;

import com.example.demo.Model.GiohangEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GiohangRepo extends JpaRepository<GiohangEntity, Integer> {
    Boolean existsByMaSanPham(Integer masanpham);
    Boolean existsByMaGioHang(Integer magiohang);
    Boolean existsByMaSanPhamAndMaThanhVien (Integer masanpham, Integer mathanhvien);
    void deleteByMaSanPham(Integer masanpham);
    void deleteByMaSanPhamAndMaThanhVien(Integer masanpham, Integer makhachhang);
    void deleteByMaThanhVien(Integer makhachhang);
    void deleteAllByMaThanhVien(Integer makhachhang);
    GiohangEntity findByMaSanPham(Integer masanpham);
    List<GiohangEntity> findAllByMaThanhVien(Integer mathanhvien);
}
