package com.example.demo.Repository;

import com.example.demo.Model.SanphamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SanphamRepo extends JpaRepository<SanphamEntity, Integer> {
    Boolean existsByTenSanPham(String a);
    Boolean existsByMaDanhMuc(Integer b);
    List<SanphamEntity> findByMaDanhMuc(int maDanhMuc);

    boolean existsByMaSanPham(Integer masanpham);

    void deleteByMaSanPham(Integer masanpham);

    SanphamEntity findByMaSanPham(Integer masanpham);

    SanphamEntity findByTenSanPham(String tensanpham);
    SanphamEntity findByGiaSanPham(Float giaSanPham);
    SanphamEntity findByMaDanhMuc(Integer madanhmuc);
    SanphamEntity findByMaNhaCungCap(Integer mancc);

    List<SanphamEntity> findByMaDanhMucIn(List<Integer> categoryIds);
}
