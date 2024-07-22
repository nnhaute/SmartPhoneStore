package com.example.demo.Controller;
import com.example.demo.Repository.NCCRepo;
import com.example.demo.Service.ThanhvienSer;
import com.example.demo.Model.NhacungcapEntity;
import com.example.demo.Model.ThanhvienEntity;
import com.example.demo.Repository.ThanhvienRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api")
public class NCCController {
    @Autowired
    private NCCRepo nccRepo;
    /* ---------------- CREATE NEW Supplier ------------------------ */
    @PostMapping("/supplier")
    public ResponseEntity<?> registerSupplier(@ModelAttribute NhacungcapEntity nhacungcapEntity) {
        if (nccRepo.existsByTenNhaCungCap(nhacungcapEntity.getTenNhaCungCap())) {
            return new ResponseEntity<>("Supplier is already taken!", HttpStatus.BAD_REQUEST);
        }
        NhacungcapEntity a = new NhacungcapEntity();
        a.setTenNhaCungCap(nhacungcapEntity.getTenNhaCungCap());
        a.setGhiChu(nhacungcapEntity.getGhiChu());
        a.setKichHoat(nhacungcapEntity.getKichHoat());
        nccRepo.save(a);
        return new ResponseEntity<>("Supplier registered successfully", HttpStatus.OK);
    }
    @PutMapping("/updatenhacungcap/{tennhacungcap}")
    @Transactional
    public ResponseEntity<?> updateNhaCungCap(@PathVariable("tennhacungcap") String tennhacungcap, @RequestBody NhacungcapEntity nhacungcapEntity) {
        NhacungcapEntity existingNhaCungCap = nccRepo.findByTenNhaCungCap(tennhacungcap);
        if (existingNhaCungCap != null) {
            // Update fields based on the provided NhacungcapEntity object
            if (nhacungcapEntity.getTenNhaCungCap() != null) {
                existingNhaCungCap.setTenNhaCungCap(nhacungcapEntity.getTenNhaCungCap());
            }
            if (nhacungcapEntity.getGhiChu() != null) {
                existingNhaCungCap.setGhiChu(nhacungcapEntity.getGhiChu());
            }
            if (nhacungcapEntity.getKichHoat() != null) {
                existingNhaCungCap.setKichHoat(nhacungcapEntity.getKichHoat());
            }
            nccRepo.save(existingNhaCungCap);
            return new ResponseEntity<>("Supplier updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Supplier not found", HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/deletetennhacungcap/{tennhacungcap}")
    @Transactional

    public ResponseEntity<?> deleteSupplier(@PathVariable("tennhacungcap") String tennhacungcap) {
        if (nccRepo.existsByTenNhaCungCap(tennhacungcap))
        {
            nccRepo.deleteByTenNhaCungCap(tennhacungcap);
            return new ResponseEntity<>("Supplier deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Supplier not found", HttpStatus.NOT_FOUND);
        }
    }
}
