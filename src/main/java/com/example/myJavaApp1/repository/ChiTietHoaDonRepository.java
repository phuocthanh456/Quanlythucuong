package com.example.myJavaApp1.repository;

import com.example.myJavaApp1.entity.ChiTietHoaDon;
import com.example.myJavaApp1.entity.ChiTietHoaDonId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChiTietHoaDonRepository extends JpaRepository<ChiTietHoaDon, ChiTietHoaDonId> {
}