package com.example.demo.Controller;

import com.example.demo.Model.ThanhvienEntity;
import com.example.demo.Repository.ThanhvienRepo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ThanhvienController {
    @Autowired
    private ThanhvienRepo thanhvienRepo;
    @RequestMapping("/sign")
    public String signinup() {
        return "sign/login";
    }
    @RequestMapping("/user/showdetail/{makhachhang}")
    public String show(@PathVariable("makhachhang") Integer makhachhang, Model model) {
        ThanhvienEntity thanhvienEntity=thanhvienRepo.findByMaKhachHang(makhachhang);
        model.addAttribute("thanhvienEntity",thanhvienEntity);
        return  "sign/edituser";
    }
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // Xóa session
        }
        return "redirect:/trangchu"; // Chuyển hướng về trang chủ
    }
}
