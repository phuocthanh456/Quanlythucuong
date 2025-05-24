package com.example.myJavaApp1.controller;

import com.example.myJavaApp1.entity.TaiKhoan;
import com.example.myJavaApp1.repository.TaiKhoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/taikhoan")
public class TaiKhoanController {

    @Autowired
    private TaiKhoanRepository taiKhoanRepository;

    // Lấy tất cả tài khoản
    @GetMapping
    public List<TaiKhoan> getAllTaiKhoan() {
        return taiKhoanRepository.findAll();
    }

    // Lấy tài khoản theo ID
    @GetMapping("/{id}")
    public ResponseEntity<TaiKhoan> getTaiKhoanById(@PathVariable Integer id) {
        Optional<TaiKhoan> taiKhoan = taiKhoanRepository.findById(id);
        return taiKhoan.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Tạo tài khoản mới
    @PostMapping
    public ResponseEntity<TaiKhoan> createTaiKhoan(@RequestBody TaiKhoan taiKhoan) {
        // Đảm bảo role mặc định là "CUSTOMER" nếu không được cung cấp
        if (taiKhoan.getRole() == null || taiKhoan.getRole().isEmpty()) {
            taiKhoan.setRole("CUSTOMER");
        }
        TaiKhoan savedTaiKhoan = taiKhoanRepository.save(taiKhoan);
        return ResponseEntity.ok(savedTaiKhoan);
    }

    // Cập nhật tài khoản
    @PutMapping("/{id}")
    public ResponseEntity<TaiKhoan> updateTaiKhoan(@PathVariable Integer id, @RequestBody TaiKhoan taiKhoanDetails) {
        Optional<TaiKhoan> taiKhoan = taiKhoanRepository.findById(id);
        if (taiKhoan.isPresent()) {
            TaiKhoan existingTaiKhoan = taiKhoan.get();
            existingTaiKhoan.setUsername(taiKhoanDetails.getUsername());
            existingTaiKhoan.setPassword(taiKhoanDetails.getPassword());
            String newRole = taiKhoanDetails.getRole();
            existingTaiKhoan.setRole(newRole != null && !newRole.isEmpty() ? newRole : "CUSTOMER");
            return ResponseEntity.ok(taiKhoanRepository.save(existingTaiKhoan));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Xóa tài khoản
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaiKhoan(@PathVariable Integer id) {
        if (taiKhoanRepository.existsById(id)) {
            taiKhoanRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Kiểm tra đăng nhập
    @PostMapping("/login")
    public ResponseEntity<TaiKhoan> login(@RequestBody TaiKhoan loginRequest) {
        Optional<TaiKhoan> taiKhoan = taiKhoanRepository.findByUsername(loginRequest.getUsername());
        if (taiKhoan.isPresent() && taiKhoan.get().getPassword().equals(loginRequest.getPassword())) {
            return ResponseEntity.ok(taiKhoan.get());
        }
        return ResponseEntity.status(401).build();
    }

    // Kiểm tra username đã tồn tại chưa
    @GetMapping("/username/{username}")
    public ResponseEntity<Boolean> checkUsernameExists(@PathVariable String username) {
        boolean exists = taiKhoanRepository.findByUsername(username).isPresent();
        return ResponseEntity.ok(exists);
    }
}