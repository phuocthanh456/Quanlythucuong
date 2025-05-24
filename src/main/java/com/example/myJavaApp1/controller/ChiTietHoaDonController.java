package com.example.myJavaApp1.controller;

import com.example.myJavaApp1.entity.ChiTietHoaDon;
import com.example.myJavaApp1.entity.ChiTietHoaDonId;
import com.example.myJavaApp1.repository.ChiTietHoaDonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/chitiethoadon")
public class ChiTietHoaDonController {

    @Autowired
    private ChiTietHoaDonRepository chiTietHoaDonRepository;

    // Lấy tất cả chi tiết hóa đơn
    @GetMapping
    public List<ChiTietHoaDon> getAllChiTietHoaDon() {
        return chiTietHoaDonRepository.findAll();
    }

    // Lấy chi tiết hóa đơn theo ID (id_invoice và id_product)
    @GetMapping("/{idInvoice}/{idProduct}")
    public ResponseEntity<ChiTietHoaDon> getChiTietHoaDonById(@PathVariable Integer idInvoice, @PathVariable Integer idProduct) {
        ChiTietHoaDonId id = new ChiTietHoaDonId(idInvoice, idProduct);
        Optional<ChiTietHoaDon> chiTietHoaDon = chiTietHoaDonRepository.findById(id);
        return chiTietHoaDon.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Tạo chi tiết hóa đơn mới
    @PostMapping
    public ResponseEntity<ChiTietHoaDon> createChiTietHoaDon(@RequestBody ChiTietHoaDon chiTietHoaDon) {
        ChiTietHoaDon savedChiTietHoaDon = chiTietHoaDonRepository.save(chiTietHoaDon);
        return ResponseEntity.ok(savedChiTietHoaDon);
    }

    // Cập nhật chi tiết hóa đơn
    @PutMapping("/{idInvoice}/{idProduct}")
    public ResponseEntity<ChiTietHoaDon> updateChiTietHoaDon(
            @PathVariable Integer idInvoice,
            @PathVariable Integer idProduct,
            @RequestBody ChiTietHoaDon chiTietHoaDonDetails) {
        ChiTietHoaDonId id = new ChiTietHoaDonId(idInvoice, idProduct);
        Optional<ChiTietHoaDon> chiTietHoaDon = chiTietHoaDonRepository.findById(id);
        if (chiTietHoaDon.isPresent()) {
            ChiTietHoaDon existingChiTietHoaDon = chiTietHoaDon.get();
            existingChiTietHoaDon.setSoLuong(chiTietHoaDonDetails.getSoLuong());
            existingChiTietHoaDon.setDonGia(chiTietHoaDonDetails.getDonGia());
            existingChiTietHoaDon.setThanhTien(chiTietHoaDonDetails.getThanhTien());
            return ResponseEntity.ok(chiTietHoaDonRepository.save(existingChiTietHoaDon));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Xóa chi tiết hóa đơn
    @DeleteMapping("/{idInvoice}/{idProduct}")
    public ResponseEntity<Void> deleteChiTietHoaDon(@PathVariable Integer idInvoice, @PathVariable Integer idProduct) {
        ChiTietHoaDonId id = new ChiTietHoaDonId(idInvoice, idProduct);
        if (chiTietHoaDonRepository.existsById(id)) {
            chiTietHoaDonRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}