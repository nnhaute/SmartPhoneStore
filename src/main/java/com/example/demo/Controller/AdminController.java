package com.example.demo.Controller;

import com.example.demo.Model.DondathangEntity;
import com.example.demo.Model.NhacungcapEntity;
import com.example.demo.Repository.DanhmucRepo;
import com.example.demo.Repository.DondathangRepo;
import com.example.demo.Repository.NCCRepo;
import com.example.demo.Repository.SanphamRepo;
import com.example.demo.Model.DanhmucEntity;
import com.example.demo.Model.SanphamEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
//@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class  AdminController {
    @Autowired
    private DanhmucRepo danhMucRepository;
    @Autowired
    private SanphamRepo sanphamRepo;
    @Autowired
    private NCCRepo nccRepo;
    @Autowired
    private DondathangRepo dondathangRepo;
    @RequestMapping("/admin/add")
    public String addProduct(DanhmucEntity danhmucEntity,NhacungcapEntity nhacungcapEntity, Model model) {
        List<DanhmucEntity> danhMucList = danhMucRepository.findAll();
        model.addAttribute("danhMucList", danhMucList);
        List<NhacungcapEntity> nhacungcapList = nccRepo.findAll();
        model.addAttribute("nhacungcapList", nhacungcapList);
        return "admin/index";
    }
    @RequestMapping("/admin/showproduct")
    public  String showProduct(SanphamEntity sanphamEntity, Model model) {
        List<SanphamEntity> sanphamList = sanphamRepo.findAll();
        model.addAttribute("sanphamList",sanphamList);
        return "admin/product";
    }
    @RequestMapping("/admin/showdetail/{masanpham}")
    public String show(@PathVariable("masanpham") Integer masanpham,Model model) {
        SanphamEntity sanphamEntity=sanphamRepo.findByMaSanPham(masanpham);
        model.addAttribute("sanphamEntity",sanphamEntity);
        List<DanhmucEntity> danhMucList = danhMucRepository.findAll();
        model.addAttribute("danhMucList", danhMucList);
        return  "admin/editproduct";
    }
    @RequestMapping("/admin/showdanhmuc")
    public  String showDanhmuc(DanhmucEntity danhmucEntity, Model model) {
        List<DanhmucEntity> danhmucList = danhMucRepository.findAll();
        model.addAttribute("danhmucList",danhmucList);
        return "admin/danhmuc";
    }
    @RequestMapping("/admin/adddanhmuc")
    public String adddanhmuc(DanhmucEntity danhmucEntity, Model model) {
        List<DanhmucEntity> danhMucList = danhMucRepository.findAll();
        model.addAttribute("danhMucList", danhMucList);
        return "admin/adddanhmuc";
    }
    @RequestMapping("/admin/editdanhmuc/{madanhmuc}")
    public String showdanhmuc(@PathVariable("madanhmuc") Integer madanhmuc,Model model) {
        DanhmucEntity danhmucEntity=danhMucRepository.findByMaDanhMuc(madanhmuc);
        model.addAttribute("danhmucEntity",danhmucEntity);
        return  "admin/editdanhmuc";
    }
    @RequestMapping("/admin/showNCC")
    public  String showNCC(NhacungcapEntity nhacungcapEntity, Model model) {
        List<NhacungcapEntity> nhacungcapList = nccRepo.findAll();
        model.addAttribute("nhacungcapList",nhacungcapList);
        return "KANA/supplier";
    }
    @RequestMapping("/admin/updatenhacungcap/{tennhacungcap}")
    public  String updateNhaCungCap(@PathVariable("tennhacungcap") String tennhacungcap,  Model model) {
        NhacungcapEntity nhacungcapEntity=nccRepo.findByTenNhaCungCap(tennhacungcap);
        model.addAttribute("nhacungcapEntity",nhacungcapEntity);
        return  "KANA/editsupplier";
    }
    @RequestMapping("/admin/addsupplier")
    public String adddsupplier(NhacungcapEntity nhacungcapEntity, Model model) {
        List<NhacungcapEntity> nhaCungCapList = nccRepo.findAll();
        model.addAttribute("nhaCungCapList", nhaCungCapList);
        return "KANA/addsupplier";
    }
    @RequestMapping("/admin/showDonHang")
    public  String showDonHang(DondathangEntity dondathangEntity, Model model) {
        List<DondathangEntity> dondathangList = dondathangRepo.findAll();
        model.addAttribute("dondathangList",dondathangList);
        return "admin/donhang";
    }
}
