package com.example.demo.Controller;

import com.example.demo.Factory.DiscountStrategy;
import com.example.demo.Factory.DiscountStrategyFactory;
import com.example.demo.Model.*;
import com.example.demo.Repository.*;
import com.example.demo.Service.DondathangSer;
import com.example.demo.Service.GiohangSer;
import com.example.demo.Service.ThanhvienSer;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value="/product",consumes = {"application/x-www-form-urlencoded;charset=UTF-8"})
public class GiohangController {
    @Autowired
    private GiohangRepo giohangRepo;

    @Autowired
    private SanphamRepo sanphamRepo ;

    @Autowired
    private GiohangSer giohangSer;

    @Autowired
    private ThanhvienRepo thanhvienRepo;

    @Autowired
    private ThanhvienSer thanhvienSer;

    @Autowired
    private DondathangSer dondathangSer;

    @Autowired
    private ChitietdathangRepo chitietdathangRepo;

    @Autowired
    private DondathangRepo dondathangRepo;

    @PostMapping("/add")
    public String addToCart(@RequestParam("prdID") int prdID, @RequestParam("userID") String userID , Model model, HttpSession session ) {

        List<SanphamEntity> sanphamList = sanphamRepo.findAll();

        int newUserID = 0;

        if(userID.equals("")) {
            List<ThanhvienEntity> users = thanhvienRepo.findAll();

            ThanhvienEntity newUser = new ThanhvienEntity();
            newUser.setRole("guest");
            newUser.setMaKhachHang(users.get(users.size()-1).getMaKhachHang()+1);
            thanhvienRepo.save(newUser);
            newUserID = newUser.getMaKhachHang();
            giohangSer.addPrd(prdID, newUser.getMaKhachHang());
        }
        else {
            int a = Integer.parseInt(userID);
            newUserID = a;
            giohangSer.addPrd(prdID, a);
        }
        session.setAttribute("makhachhang", newUserID);
        model.addAttribute("userID", newUserID);
        model.addAttribute("sanphamlist",sanphamList);

        return "/product/productUser";
    }

    /* ---------------- Show Cart ------------------------ */
    @PostMapping("/showCart")
    public String showCart(HttpSession session, Model redirectAttributes,@RequestParam("userID") String userID)
    {
        List<GiohangEntity> cart = new ArrayList<>();
        if (userID != "")
        {
            int a = Integer.parseInt(userID);
            cart = giohangRepo.findAllByMaThanhVien(a);
        }
        redirectAttributes.addAttribute("giohangList", cart);
        redirectAttributes.addAttribute("userID", userID);
        redirectAttributes.addAttribute("amount", cart.size());
        return "/product/productCart";
    }

    @PostMapping("/update-quantity")
    @Transactional
    public String updateQuantity(@RequestParam("userID") String userID,@RequestParam("quantity") Integer quantity, @RequestParam("prdID") Integer prdID, Model redirectAttributes  ) {
        int a = Integer.parseInt(userID);
        // Cập nhật số lượng sản phẩm trong giỏ hàng
        giohangSer.updateQuantity(prdID, a, quantity);
        List<GiohangEntity> cart = giohangRepo.findAllByMaThanhVien(a);
        redirectAttributes.addAttribute("amount", cart.size());
        redirectAttributes.addAttribute("userID", userID);
        redirectAttributes.addAttribute("giohangList", cart);
        return "/product/productCart";
    }

    @PostMapping("/deletePrd")
    @Transactional
    public String deletePRD(@RequestParam("userID") String userID, @RequestParam("prdID") Integer prdID, Model redirectAttributes, HttpServletResponse response) {

        List<GiohangEntity> cart = giohangRepo.findAllByMaThanhVien(Integer.parseInt(userID));
        giohangRepo.deleteByMaSanPhamAndMaThanhVien(prdID, Integer.parseInt(userID));
        redirectAttributes.addAttribute("amount", cart.size());
        redirectAttributes.addAttribute("userID", userID);
        redirectAttributes.addAttribute("giohangList", cart);

//        // Chặn cache của trình duyệt
//        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
//        response.setHeader("Pragma", "no-cache");
//        response.setDateHeader("Expires", 0);

        return "/product/productCart";
    }

    @PostMapping("/checkout")
    @Transactional
    public String checkOut(@RequestParam("userID") String userID, Model redirectAttributes)
    {
        int orderCount = dondathangRepo.countByMaKhachHang(Integer.parseInt(userID));
        DiscountStrategy discountStrategy = DiscountStrategyFactory.getDiscountStrategy(orderCount);

        List<GiohangEntity> cart = giohangRepo.findAllByMaThanhVien(Integer.parseInt(userID));
        Float total = (float) 0;
        for (var a : cart) {
            total += (((float) a.getSoLuong()) * a.getGiaSanPham());
        }

        // Tính giảm giá
        float discount = discountStrategy.calculateDiscount(total);
        float finalTotal = total - discount;

        String customerType;
        float discountPercent;
        if (orderCount >= 3) {
            customerType = "VIP 2";
            discountPercent = 10;
        } else if (orderCount > 0) {
            customerType = "VIP 1";
            discountPercent = 5;
        } else {
            customerType = "Khách hàng thường";
            discountPercent = 0;
        }

        // Thêm thông tin vào model
        redirectAttributes.addAttribute("customerType", customerType);
        redirectAttributes.addAttribute("discount", discount);
        redirectAttributes.addAttribute("discountPercent", discountPercent);
        redirectAttributes.addAttribute("finalTotal", finalTotal);
        redirectAttributes.addAttribute("userID", userID);
        redirectAttributes.addAttribute("amount", cart.size());
        redirectAttributes.addAttribute("total", total);
        redirectAttributes.addAttribute("giohangList", cart);
        return "/product/checkout";
    }
    @PostMapping("/placeorder")
    @Transactional
    public String placeOrder(@RequestParam("userID") String userID, @RequestParam("firstname") String firstname,
                             @RequestParam("lastname") String lastname, @RequestParam("addr") String addr,
                             @RequestParam("mobile") String mobile, @RequestParam("email") String email,
                             @RequestParam("text") String description, @RequestParam("payment-method") String paymentmethod,
                             Model redirectAttributes)
    {
        List<GiohangEntity> cart = giohangRepo.findAllByMaThanhVien(Integer.parseInt(userID));
        Float total = (float) 0;
        for (var a : cart){
            total += (((float) a.getSoLuong()) * a.getGiaSanPham());
        }



        String name = firstname + " " + lastname;
        thanhvienSer.updateUser(Integer.parseInt(userID),email,addr,mobile,name);

        Date date = new Date();
        String payment; int pay = 2;
        if(paymentmethod == "Transfer"){
            pay = 1;
            payment = "Banking";
        } else if (paymentmethod == "Delivery") {
            pay = 2;
            payment = "Cash";
        }
        else{
            pay =3;
            payment = "Paypal";
        }
        dondathangSer.createOrder(Integer.parseInt(userID),email,mobile,name,description,date,total, payment);

        ChitietdondathangEntity newDt = new ChitietdondathangEntity();
        List<DondathangEntity> listord = dondathangRepo.findAllByMaKhachHang(Integer.parseInt(userID));
        int ordIDmax = listord.get(listord.size()-1).getMaDonDatHang();
        for (var a : cart){
            newDt.setSoLuong(a.getSoLuong());
            newDt.setMaSanPham(a.getMaSanPham());
            newDt.setMaDonDatHang(ordIDmax);
            chitietdathangRepo.save(newDt);
            newDt = new ChitietdondathangEntity();
        }

        giohangRepo.deleteAllByMaThanhVien(Integer.parseInt(userID));


        redirectAttributes.addAttribute("amount", giohangRepo.findAllByMaThanhVien(Integer.parseInt(userID)).size());
        redirectAttributes.addAttribute("amount", cart.size());
        redirectAttributes.addAttribute("total", total);
        redirectAttributes.addAttribute("userID", userID);
        redirectAttributes.addAttribute("sanphamlist",sanphamRepo.findAll());
        redirectAttributes.addAttribute("giohangList", cart);
        //FACTORY PATTENT


        return "product/productUser";
    }
}
