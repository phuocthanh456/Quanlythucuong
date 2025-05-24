package com.example.myJavaApp1.controller;

import com.example.myJavaApp1.entity.KhachHang;
import com.example.myJavaApp1.repository.KhachHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/khachhang")
public class KhachHangController {

    @Autowired
    private KhachHangRepository khachHangRepository;

    // Lấy tất cả khách hàng
    @GetMapping
    public List<KhachHang> getAllKhachHang() {
        return khachHangRepository.findAll();
    }

    // Lấy khách hàng theo ID
    @GetMapping("/{id}")
    public ResponseEntity<KhachHang> getKhachHangById(@PathVariable Integer id) {
        Optional<KhachHang> khachHang = khachHangRepository.findById(id);
        if (khachHang.isPresent()) {
            return ResponseEntity.ok(khachHang.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Tạo khách hàng mới
    @PostMapping
    public KhachHang createKhachHang(@RequestBody KhachHang khachHang) {
        return khachHangRepository.save(khachHang);
    }

    // Cập nhật khách hàng
    @PutMapping("/{id}")
    public ResponseEntity<KhachHang> updateKhachHang(@PathVariable Integer id, @RequestBody KhachHang khachHangDetails) {
        Optional<KhachHang> khachHang = khachHangRepository.findById(id);
        if (khachHang.isPresent()) {
            KhachHang updatedKhachHang = khachHang.get();
            updatedKhachHang.setHoVaTen(khachHangDetails.getHoVaTen());
            updatedKhachHang.setNgaySinh(khachHangDetails.getNgaySinh());
            updatedKhachHang.setDiaChi(khachHangDetails.getDiaChi());
            updatedKhachHang.setSdt(khachHangDetails.getSdt());
            updatedKhachHang.setEmail(khachHangDetails.getEmail());
            return ResponseEntity.ok(khachHangRepository.save(updatedKhachHang));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Xóa khách hàng
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKhachHang(@PathVariable Integer id) {
        Optional<KhachHang> khachHang = khachHangRepository.findById(id);
        if (khachHang.isPresent()) {
            khachHangRepository.delete(khachHang.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}