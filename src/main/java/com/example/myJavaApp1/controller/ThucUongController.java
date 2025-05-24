package com.example.myJavaApp1.controller;

import com.example.myJavaApp1.entity.ThucUong;
import com.example.myJavaApp1.repository.ThucUongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/thucuong")
@CrossOrigin(origins = "http://localhost:8080") // Thêm CORS nếu frontend chạy trên port khác
public class ThucUongController {

    @Autowired
    private ThucUongRepository thucUongRepository;

    // Thư mục lưu ảnh (sử dụng thư mục static của Spring Boot)
    private static final String UPLOAD_DIR = "src/main/resources/static/images/";

    // Lấy tất cả thức uống
    @GetMapping
    public List<ThucUong> getAllThucUong() {
        return thucUongRepository.findAll();
    }

    // Lấy thức uống theo ID
    @GetMapping("/{id}")
    public ResponseEntity<ThucUong> getThucUongById(@PathVariable Integer id) {
        Optional<ThucUong> thucUong = thucUongRepository.findById(id);
        return thucUong.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Tạo thức uống mới với upload ảnh
    @PostMapping
    public ResponseEntity<ThucUong> createThucUong(
            @RequestParam("idType") Integer idType,
            @RequestParam("idSupplier") Integer idSupplier,
            @RequestParam("ten") String ten,
            @RequestParam("donGia") Double donGia,
            @RequestParam("soLuong") Integer soLuong,
            @RequestParam("nsx") String nsx,
            @RequestParam("hsd") String hsd,
            @RequestParam(value = "imageUrl", required = false) MultipartFile imageUrl) throws IOException {

        ThucUong thucUong = new ThucUong();
        thucUong.setIdType(idType);
        thucUong.setIdSupplier(idSupplier);
        thucUong.setTen(ten);
        thucUong.setDonGia(donGia);
        thucUong.setSoLuong(soLuong);
        thucUong.setNsx(nsx);
        thucUong.setHsd(hsd);

        // Xử lý upload ảnh
        if (imageUrl != null && !imageUrl.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + imageUrl.getOriginalFilename(); // Tên duy nhất
            Path uploadPath = Paths.get(UPLOAD_DIR + fileName);
            Files.createDirectories(uploadPath.getParent()); // Tạo thư mục nếu chưa tồn tại
            Files.write(uploadPath, imageUrl.getBytes()); // Lưu file
            thucUong.setImageUrl("/images/" + fileName); // Đường dẫn tương đối
        }

        ThucUong savedThucUong = thucUongRepository.save(thucUong);
        return ResponseEntity.ok(savedThucUong);
    }

    // Cập nhật thức uống với upload ảnh
    @PutMapping("/{id}")
    public ResponseEntity<ThucUong> updateThucUong(
            @PathVariable Integer id,
            @RequestParam("idType") Integer idType,
            @RequestParam("idSupplier") Integer idSupplier,
            @RequestParam("ten") String ten,
            @RequestParam("donGia") Double donGia,
            @RequestParam("soLuong") Integer soLuong,
            @RequestParam("nsx") String nsx,
            @RequestParam("hsd") String hsd,
            @RequestParam(value = "imageUrl", required = false) MultipartFile imageUrl) throws IOException {

        Optional<ThucUong> thucUong = thucUongRepository.findById(id);
        if (thucUong.isPresent()) {
            ThucUong existingThucUong = thucUong.get();
            existingThucUong.setIdType(idType);
            existingThucUong.setIdSupplier(idSupplier);
            existingThucUong.setTen(ten);
            existingThucUong.setDonGia(donGia);
            existingThucUong.setSoLuong(soLuong);
            existingThucUong.setNsx(nsx);
            existingThucUong.setHsd(hsd);

            // Xử lý upload ảnh (cập nhật nếu có ảnh mới)
            if (imageUrl != null && !imageUrl.isEmpty()) {
                String fileName = System.currentTimeMillis() + "_" + imageUrl.getOriginalFilename();
                Path uploadPath = Paths.get(UPLOAD_DIR + fileName);
                Files.createDirectories(uploadPath.getParent());
                Files.write(uploadPath, imageUrl.getBytes());
                existingThucUong.setImageUrl("/images/" + fileName);
            }

            return ResponseEntity.ok(thucUongRepository.save(existingThucUong));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Xóa thức uống
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteThucUong(@PathVariable Integer id) {
        if (thucUongRepository.existsById(id)) {
            thucUongRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}