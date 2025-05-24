package com.example.myJavaApp1.repository;

import com.example.myJavaApp1.entity.ThucUong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThucUongRepository extends JpaRepository<ThucUong, Integer> {
}