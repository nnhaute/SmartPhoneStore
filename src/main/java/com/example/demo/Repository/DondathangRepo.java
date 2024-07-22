package com.example.demo.Repository;

import com.example.demo.Model.DondathangEntity;
import com.example.demo.Model.GiohangEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DondathangRepo extends JpaRepository<DondathangEntity, Integer> {
    DondathangEntity findByMaKhachHang(Integer makhachhang);
    List<DondathangEntity> findAllByMaKhachHang(Integer makhachhang);
    int countByMaKhachHang(int i);
}
