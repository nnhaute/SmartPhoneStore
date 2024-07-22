package com.example.demo.Service;

import com.example.demo.Model.ThanhvienEntity;
import com.example.demo.Repository.ThanhvienRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThanhvienSer {
    @Autowired
    private ThanhvienRepo thanhvienRepo;

    public void updateUser(int userID, String email, String addr, String num, String name) {
        ThanhvienEntity user = thanhvienRepo.findByMaKhachHang(userID);
        user.setDiaChi(addr);
        user.setEmail(email);
        user.setDienThoai(num);
        user.setHoTen(name);
        thanhvienRepo.save(user);
        return;
    }
}
