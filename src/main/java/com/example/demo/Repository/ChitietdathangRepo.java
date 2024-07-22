package com.example.demo.Repository;

import com.example.demo.Model.ChitietdondathangEntity;
import com.example.demo.Model.GiohangEntity;
import com.example.demo.Service.Chitietdathang;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChitietdathangRepo extends JpaRepository<ChitietdondathangEntity, Integer> {
}
