package com.example.demo.Facade;

import com.example.demo.Model.DanhmucEntity;
import com.example.demo.Model.NhacungcapEntity;
import com.example.demo.Model.SanphamEntity;
import com.example.demo.Repository.NCCRepo;
import com.example.demo.Repository.SanphamRepo;

import java.util.List;
import java.util.stream.Collectors;

public class NhaCungCapCriteria implements SearchProduct {
    private final String tenNCC;
    private final SanphamRepo sanphamRepo;
    private final NCCRepo nccRepo;

    public NhaCungCapCriteria(String tenNCC, SanphamRepo sanphamRepo, NCCRepo nccRepo) {
        this.tenNCC = tenNCC;
        this.sanphamRepo = sanphamRepo;
        this.nccRepo = nccRepo;
    }

    @Override
    public List<SanphamEntity> Criteria(List<SanphamEntity> products) {
        List<NhacungcapEntity> categories = nccRepo.findByTenNhaCungCapContaining(tenNCC);
        List<Integer> categoryIds = categories.stream()
                .map(NhacungcapEntity::getMaNhaCungCap)
                .collect(Collectors.toList());

        return sanphamRepo.findByMaDanhMucIn(categoryIds);
    }
}

