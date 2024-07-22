package com.example.demo.Repository;

import com.example.demo.Model.DanhmucEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DanhmucRepo extends JpaRepository<DanhmucEntity, Integer> {
    Boolean existsByTenDanhMuc(String tendanhmuc);
    Boolean existsByMaDanhMuc(Integer madanhmuc);
    void deleteByMaDanhMuc(Integer madanhmuc);

    DanhmucEntity findByMaDanhMuc(Integer madanhmuc);

    List<DanhmucEntity> findByTenDanhMucContaining(String tenDanhMuc);
}
