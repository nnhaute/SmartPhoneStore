package com.example.demo.Repository;

import com.example.demo.Model.NhacungcapEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NCCRepo extends JpaRepository<NhacungcapEntity, Integer> {
Boolean existsByTenNhaCungCap(String tennhacungcap);
void deleteByTenNhaCungCap(String tennhacungcap);
NhacungcapEntity findByTenNhaCungCap(String tennhacungcap);

    List<NhacungcapEntity> findByTenNhaCungCapContaining(String tenNCC);
}

