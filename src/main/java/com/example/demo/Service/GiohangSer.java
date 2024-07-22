package com.example.demo.Service;

import com.example.demo.Model.GiohangEntity;
import com.example.demo.Model.SanphamEntity;
import com.example.demo.Repository.GiohangRepo;
import com.example.demo.Repository.SanphamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GiohangSer {
    @Autowired
    private GiohangRepo giohangRepo;

    @Autowired
    private SanphamRepo sanphamRepo;

    public GiohangSer(GiohangRepo cartRepository) {
        this.giohangRepo = cartRepository;
    }
    public SanphamEntity sanpham(int maSanPham){
        List<SanphamEntity> sanphamEntityList = sanphamRepo.findAll();
        for (var a : sanphamEntityList){
            if (a.getMaSanPham() == maSanPham)
            {
                return a;
            }
        }
        return null;
    }
    public void updateQuantity(int productId, int userID, int quantity) {
        // Lấy thông tin giỏ hàng của người dùng
        List<GiohangEntity> cart1 = giohangRepo.findAllByMaThanhVien(userID);

        for (var a : cart1){
            if(a.getMaSanPham() == productId){
                // Cập nhật số lượng sản phẩm
                a.setSoLuong(quantity);
                giohangRepo.save(a);
                break;
            }
        }

    }
    public void addPrd(int prdID, int userID)
    {
        List<SanphamEntity> sanphamList = sanphamRepo.findAll();
        List<GiohangEntity> cart = giohangRepo.findAllByMaThanhVien(userID);

        // Tìm sản phẩm add
        GiohangEntity prd = new GiohangEntity();
        for (var a : sanphamList){
            if (a.getMaSanPham() == prdID) {
                prd.setGiaSanPham(a.getGiaSanPham());
                prd.setTenSanPham(a.getTenSanPham());
                prd.setImg(a.getHinhAnhSanPham());
                break;
            }
        }
//        int check = 0;
//        if (cart.isEmpty()){
//            prd.setMaSanPham(prdID);
//            prd.setSoLuong(1);
//            prd.setMaGioHang(0);
//            prd.setMaThanhVien(userID);
//            giohangRepo.save(prd);
//        }
//        else {
//            for (var a : cart)
//            {
//                if (a.getMaSanPham() == prdID){
//                    this.updateQuantity(prdID, userID, a.getSoLuong()+1);
//                    break;
//                }
//                check = 1;
//            }
//            if (check == 1) {
//                prd.setMaSanPham(prdID);
//                prd.setSoLuong(1);
//                prd.setMaGioHang(0);
//                prd.setMaThanhVien(userID);
//                giohangRepo.save(prd);
//            }
//        }
        if (!giohangRepo.existsByMaSanPhamAndMaThanhVien(prdID,userID)){
            prd.setMaSanPham(prdID);
            prd.setSoLuong(1);
            prd.setMaGioHang(0);
            prd.setMaThanhVien(userID);
            giohangRepo.save(prd);
        }

        return;
    }
}
