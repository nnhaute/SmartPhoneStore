package com.example.demo.Controller;

import com.example.demo.Model.ThanhvienEntity;
import com.example.demo.Repository.ThanhvienRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.net.ssl.HandshakeCompletedEvent;
import java.util.List;
import java.util.Optional;
import org.springframework.ui.Model;
@Controller
@RequestMapping("/api")
public class ThanhvienAPIController {
    @Autowired
    private ThanhvienRepo thanhvienRepo;
    /* ---------------- CREATE NEW CUSTOMER ------------------------ */
    @PostMapping("/customer")
    public ResponseEntity<?> registerUser(@ModelAttribute ThanhvienEntity thanhvienEntity) {
        if (thanhvienRepo.existsByTenDangNhap(thanhvienEntity.getTenDangNhap())) {
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }
        ThanhvienEntity a = new ThanhvienEntity();


        a.setTenDangNhap(thanhvienEntity.getTenDangNhap());
        a.setMatKhau(thanhvienEntity.getMatKhau());
        a.setHoTen(thanhvienEntity.getHoTen());
        a.setEmail(thanhvienEntity.getEmail());
        a.setDiaChi(thanhvienEntity.getDiaChi());
        a.setDienThoai(thanhvienEntity.getDienThoai());
        a.setRole("USER");
        thanhvienRepo.save(a);
        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }

    /* -------------- SIGNIN CUSTOMER ------------------------ */
    @PostMapping("/sign2")
    public String signinCustomer(@ModelAttribute ThanhvienEntity thanhvienEntity, HttpSession session, Model model) {
        List<ThanhvienEntity> validAccounts = thanhvienRepo.findAll();
        Optional<ThanhvienEntity> validAccount = validAccounts.stream()
                .filter(account -> {
                    String loginName = thanhvienEntity.getTenDangNhap();
                    return loginName != null && loginName.equals(account.getTenDangNhap())
                            && account.getMatKhau().equals(thanhvienEntity.getMatKhau());
                })
                .findFirst();

        if (validAccount.isPresent()) {
            ThanhvienEntity user = validAccount.get();
            session.setAttribute("makhachhang", user.getMaKhachHang());
            session.setAttribute("ten", user.getTenDangNhap());
            session.setAttribute("user",user);
            model.addAttribute("user",user);
            model.addAttribute("userID", user.getMaKhachHang());
            if (user.getRole().equals("ADMIN")) {
                return "redirect:/admin/showproduct";
            } else {
                return "redirect:/product";
            }
        } else {
            // Đăng nhập không thành công
            return "sign";
        }
    }
    @DeleteMapping("/customer/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("id") Integer id) {
        if (!thanhvienRepo.existsById(id)) {
            return new ResponseEntity<>("Customer not found", HttpStatus.NOT_FOUND);
        }

        thanhvienRepo.deleteById(id);
        return new ResponseEntity<>("Customer deleted successfully", HttpStatus.OK);
    }
    @PostMapping("/updatethanhvien/{makhachhang}")
    @Transactional
    public String updateThanhVien(@PathVariable("makhachhang") Integer maKhachHang, @ModelAttribute ThanhvienEntity thanhvienEntity) {
        ThanhvienEntity existingThanhVien = thanhvienRepo.findByMaKhachHang(maKhachHang);

        if (thanhvienEntity.getTenDangNhap() != null) {
            existingThanhVien.setTenDangNhap(thanhvienEntity.getTenDangNhap());
        }
        if (thanhvienEntity.getMatKhau() != null) {
            existingThanhVien.setMatKhau(thanhvienEntity.getMatKhau());
        }
        if (thanhvienEntity.getHoTen() != null) {
            existingThanhVien.setHoTen(thanhvienEntity.getHoTen());
        }
        if (thanhvienEntity.getEmail() != null) {
            existingThanhVien.setEmail(thanhvienEntity.getEmail());
        }
        if (thanhvienEntity.getDiaChi() != null) {
            existingThanhVien.setDiaChi(thanhvienEntity.getDiaChi());
        }
        if (thanhvienEntity.getDienThoai() != null) {
            existingThanhVien.setDienThoai(thanhvienEntity.getDienThoai());
        }

        thanhvienRepo.save(existingThanhVien);
        return "redirect:/product";
    }
}