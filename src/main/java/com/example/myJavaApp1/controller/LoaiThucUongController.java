package com.example.myJavaApp1.controller;

import com.example.myJavaApp1.entity.LoaiThucUong;
import com.example.myJavaApp1.repository.LoaiThucUongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/loaithucuong")
public class LoaiThucUongController {

    @Autowired
    private LoaiThucUongRepository loaiThucUongRepository;

    // Lấy tất cả loại thức uống
    @GetMapping
    public List<LoaiThucUong> getAllLoaiThucUong() {
        return loaiThucUongRepository.findAll();
    }

    // Lấy loại thức uống theo ID
    @GetMapping("/{id}")
    public ResponseEntity<LoaiThucUong> getLoaiThucUongById(@PathVariable Integer id) {
        Optional<LoaiThucUong> loaiThucUong = loaiThucUongRepository.findById(id);
        if (loaiThucUong.isPresent()) {
            return ResponseEntity.ok(loaiThucUong.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Tạo loại thức uống mới
    @PostMapping
    public LoaiThucUong createLoaiThucUong(@RequestBody LoaiThucUong loaiThucUong) {
        return loaiThucUongRepository.save(loaiThucUong);
    }

    // Cập nhật loại thức uống
    @PutMapping("/{id}")
    public ResponseEntity<LoaiThucUong> updateLoaiThucUong(@PathVariable Integer id, @RequestBody LoaiThucUong loaiThucUongDetails) {
        Optional<LoaiThucUong> loaiThucUong = loaiThucUongRepository.findById(id);
        if (loaiThucUong.isPresent()) {
            LoaiThucUong updatedLoaiThucUong = loaiThucUong.get();
            updatedLoaiThucUong.setTen(loaiThucUongDetails.getTen());
            return ResponseEntity.ok(loaiThucUongRepository.save(updatedLoaiThucUong));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Xóa loại thức uống
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoaiThucUong(@PathVariable Integer id) {
        Optional<LoaiThucUong> loaiThucUong = loaiThucUongRepository.findById(id);
        if (loaiThucUong.isPresent()) {
            loaiThucUongRepository.delete(loaiThucUong.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}