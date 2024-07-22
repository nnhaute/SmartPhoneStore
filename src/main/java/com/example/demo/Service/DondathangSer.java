package com.example.demo.Service;

import com.example.demo.Model.DondathangEntity;
import com.example.demo.Repository.DondathangRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DondathangSer {
    @Autowired
    private DondathangRepo dondathangRepo;

    public void createOrder(int userID, String email, String num, String name, String description, Date date, Float total, String payment){
        DondathangEntity order = new DondathangEntity();
        order.setMaDonDatHang(1);
        order.setMaKhachHang(userID);
        order.setDienThoaiNguoiNhan(num);
        order.setEmailNguoiNhan(email);
        order.setNgayTao(date);
        order.setTenNguoiNhan(name);
        order.setTongTien(total);
        order.setMoTaDonDatHang(description);
        order.setTrangThai(payment);
        dondathangRepo.save(order);
        return;
    }
}
