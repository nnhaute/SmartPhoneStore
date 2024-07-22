package com.example.demo.Service;

import com.example.demo.Model.ChitietdondathangEntity;
import com.example.demo.Repository.ChitietdathangRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Chitietdathang {
    @Autowired
    private ChitietdathangRepo chitietdathangRepo;

    public void createOrdDetail(int userID, int amount, int prdID, String description)
    {
        ChitietdondathangEntity ordt = new ChitietdondathangEntity();
        ordt.setSoLuong(amount);
        ordt.setMaSanPham(prdID);
        ordt.setMoTaChiTiet(description);

    }
}
