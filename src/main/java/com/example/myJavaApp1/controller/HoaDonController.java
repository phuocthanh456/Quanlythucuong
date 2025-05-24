package com.example.myJavaApp1.controller;

import com.example.myJavaApp1.entity.HoaDon;
import com.example.myJavaApp1.repository.HoaDonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/hoadon")
public class HoaDonController {

    @Autowired
    private HoaDonRepository hoaDonRepository;

    // Lấy tất cả hóa đơn
    @GetMapping
    public List<HoaDon> getAllHoaDon() {
        return hoaDonRepository.findAll();
    }

    // Lấy hóa đơn theo ID
    @GetMapping("/{id}")
    public ResponseEntity<HoaDon> getHoaDonById(@PathVariable Integer id) {
        return hoaDonRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Tạo hóa đơn mới
    @PostMapping
    public ResponseEntity<HoaDon> createHoaDon(@RequestBody HoaDon hoaDon) {
        HoaDon savedHoaDon = hoaDonRepository.save(hoaDon);
        return ResponseEntity.ok(savedHoaDon);
    }

    // Cập nhật hóa đơn
    @PutMapping("/{id}")
    public ResponseEntity<HoaDon> updateHoaDon(@PathVariable Integer id, @RequestBody HoaDon hoaDonDetails) {
        return hoaDonRepository.findById(id)
                .map(existingHoaDon -> {
                    existingHoaDon.setIdCustomer(hoaDonDetails.getIdCustomer());
                    existingHoaDon.setNgayLap(hoaDonDetails.getNgayLap());
                    existingHoaDon.setTongTien(hoaDonDetails.getTongTien());
                    return ResponseEntity.ok(hoaDonRepository.save(existingHoaDon));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Xóa hóa đơn
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHoaDon(@PathVariable Integer id) {
        if (hoaDonRepository.existsById(id)) {
            hoaDonRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    // API thống kê doanh thu theo ngày/tháng/năm
    @GetMapping("/thongke")
    public ResponseEntity<List<Map<String, Object>>> thongKeDoanhThu(
            @RequestParam String type, // "day", "month", "year"
            @RequestParam String startDate, // Format: "yyyy-MM-dd"
            @RequestParam String endDate) { // Format: "yyyy-MM-dd"
        try {
            List<HoaDon> hoaDons = hoaDonRepository.findAll();
            Date start = Date.valueOf(startDate);
            Date end = Date.valueOf(endDate);

            // Lọc hóa đơn trong khoảng thời gian
            List<HoaDon> filteredHoaDons = hoaDons.stream()
                    .filter(hd -> hd.getNgayLap() != null && !hd.getNgayLap().before(start) && !hd.getNgayLap().after(end))
                    .collect(Collectors.toList());

            // Nhóm theo type (ngày/tháng/năm)
            Map<String, Integer> doanhThuMap = new HashMap<>();
            for (HoaDon hd : filteredHoaDons) {
                String key;
                if (type.equals("day")) {
                    key = hd.getNgayLap().toString(); // yyyy-MM-dd
                } else if (type.equals("month")) {
                    key = hd.getNgayLap().toString().substring(0, 7); // yyyy-MM
                } else {
                    key = hd.getNgayLap().toString().substring(0, 4); // yyyy
                }
                doanhThuMap.put(key, doanhThuMap.getOrDefault(key, 0) + hd.getTongTien());
            }

            // Chuyển đổi Map thành List để trả về JSON
            List<Map<String, Object>> result = doanhThuMap.entrySet().stream()
                    .map(entry -> {
                        Map<String, Object> map = new HashMap<>();
                        map.put("thoiGian", entry.getKey());
                        map.put("doanhThu", entry.getValue());
                        return map;
                    })
                    .collect(Collectors.toList());

            // Sắp xếp theo thời gian
            result.sort((a, b) -> a.get("thoiGian").toString().compareTo(b.get("thoiGian").toString()));

            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            // Xử lý định dạng ngày không hợp lệ
            return ResponseEntity.badRequest().body(Collections.singletonList(
                    Map.of("error", "Định dạng ngày không hợp lệ. Vui lòng dùng yyyy-MM-dd")
            ));
        } catch (Exception e) {
            // Ghi log lỗi để debug
            e.printStackTrace();
            return ResponseEntity.badRequest().body(Collections.singletonList(
                    Map.of("error", "Đã xảy ra lỗi khi xử lý yêu cầu")
            ));
        }
    }
}