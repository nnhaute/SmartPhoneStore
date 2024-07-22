package com.example.demo.Facade;

import com.example.demo.Model.SanphamEntity;
import com.example.demo.Repository.DanhmucRepo;
import com.example.demo.Repository.NCCRepo;
import com.example.demo.Repository.SanphamRepo;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class userSearchProduct {
    private final SanphamRepo sanphamRepo;
    private final DanhmucRepo danhmucRepo;
    private final NCCRepo nccRepo;

    public userSearchProduct(SanphamRepo sanphamRepo, DanhmucRepo danhmucRepo, NCCRepo nccRepo) {
        this.sanphamRepo = sanphamRepo;
        this.danhmucRepo = danhmucRepo;
        this.nccRepo = nccRepo;
    }

    public List<SanphamEntity> searchProducts(List<SearchProduct> criteria) {
        List<SanphamEntity> allProducts = sanphamRepo.findAll();
        List<SanphamEntity> result = allProducts;

        for (SearchProduct searchCriteria : criteria) {
            if (searchCriteria instanceof TenDanhMucCriteria) {
                result = ((TenDanhMucCriteria) searchCriteria).Criteria(result);
            } else if (searchCriteria instanceof NhaCungCapCriteria) {
                result = ((NhaCungCapCriteria) searchCriteria).Criteria(result);
            } else {
                result = searchCriteria.Criteria(result);
            }
        }
        return result;
    }
}
