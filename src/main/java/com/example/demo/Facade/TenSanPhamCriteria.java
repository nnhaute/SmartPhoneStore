package com.example.demo.Facade;

import com.example.demo.Model.SanphamEntity;

import java.util.List;
import java.util.stream.Collectors;

public class TenSanPhamCriteria implements  SearchProduct{
    private final String tenSanPham;

    public TenSanPhamCriteria(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    @Override
    public List<SanphamEntity> Criteria(List<SanphamEntity> products) {
        return products.stream()
                .filter(p -> p.getTenSanPham().contains(tenSanPham))
                .collect(Collectors.toList());
    }
}
