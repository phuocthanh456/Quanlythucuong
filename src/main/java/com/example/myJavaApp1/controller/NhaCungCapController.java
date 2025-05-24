package com.example.myJavaApp1.controller;

import com.example.myJavaApp1.entity.NhaCungCap;
import com.example.myJavaApp1.repository.NhaCungCapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/nhacungcap")
public class NhaCungCapController {

    @Autowired
    private NhaCungCapRepository nhaCungCapRepository;

    // Lấy tất cả nhà cung cấp
    @GetMapping
    public List<NhaCungCap> getAllNhaCungCap() {
        return nhaCungCapRepository.findAll();
    }

    // Lấy nhà cung cấp theo ID
    @GetMapping("/{id}")
    public ResponseEntity<NhaCungCap> getNhaCungCapById(@PathVariable Integer id) {
        Optional<NhaCungCap> nhaCungCap = nhaCungCapRepository.findById(id);
        if (nhaCungCap.isPresent()) {
            return ResponseEntity.ok(nhaCungCap.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Tạo nhà cung cấp mới
    @PostMapping
    public NhaCungCap createNhaCungCap(@RequestBody NhaCungCap nhaCungCap) {
        return nhaCungCapRepository.save(nhaCungCap);
    }

    // Cập nhật nhà cung cấp
    @PutMapping("/{id}")
    public ResponseEntity<NhaCungCap> updateNhaCungCap(@PathVariable Integer id, @RequestBody NhaCungCap nhaCungCapDetails) {
        Optional<NhaCungCap> nhaCungCap = nhaCungCapRepository.findById(id);
        if (nhaCungCap.isPresent()) {
            NhaCungCap updatedNhaCungCap = nhaCungCap.get();
            updatedNhaCungCap.setTen(nhaCungCapDetails.getTen());
            return ResponseEntity.ok(nhaCungCapRepository.save(updatedNhaCungCap));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Xóa nhà cung cấp
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNhaCungCap(@PathVariable Integer id) {
        Optional<NhaCungCap> nhaCungCap = nhaCungCapRepository.findById(id);
        if (nhaCungCap.isPresent()) {
            nhaCungCapRepository.delete(nhaCungCap.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}