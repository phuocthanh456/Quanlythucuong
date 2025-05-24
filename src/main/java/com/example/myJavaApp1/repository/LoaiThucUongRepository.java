package com.example.myJavaApp1.repository;

import com.example.myJavaApp1.entity.LoaiThucUong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoaiThucUongRepository extends JpaRepository<LoaiThucUong, Integer> {
}