package com.example.demo.Facade;

import com.example.demo.Model.DanhmucEntity;
import com.example.demo.Model.SanphamEntity;
import com.example.demo.Repository.DanhmucRepo;
import com.example.demo.Repository.SanphamRepo;

import java.util.List;
import java.util.stream.Collectors;

public class TenDanhMucCriteria implements SearchProduct{
    private final String tenDanhMuc;
    private final SanphamRepo sanphamRepo;
    private final DanhmucRepo danhmucRepo;

    public TenDanhMucCriteria(String tenDanhMuc, SanphamRepo sanphamRepo, DanhmucRepo danhmucRepo) {
        this.tenDanhMuc = tenDanhMuc;
        this.sanphamRepo = sanphamRepo;
        this.danhmucRepo = danhmucRepo;
    }
    @Override
    public List<SanphamEntity> Criteria(List<SanphamEntity> products) {
        List<DanhmucEntity> categories = danhmucRepo.findByTenDanhMucContaining(tenDanhMuc);
        List<Integer> categoryIds = categories.stream()
                .map(DanhmucEntity::getMaDanhMuc)
                .collect(Collectors.toList());

        return sanphamRepo.findByMaDanhMucIn(categoryIds);
    }
}
