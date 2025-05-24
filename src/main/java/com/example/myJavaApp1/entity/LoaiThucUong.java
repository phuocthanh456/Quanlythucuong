package com.example.myJavaApp1.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "loai_thuc_uong")
public class LoaiThucUong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_type")
    private Integer idType;

    @Column(name = "ten")
    private String ten;

    // Constructor mặc định
    public LoaiThucUong() {
    }

    // Constructor đầy đủ
    public LoaiThucUong(Integer idType, String ten) {
        this.idType = idType;
        this.ten = ten;
    }

    // Getters và Setters
    public Integer getIdType() {
        return idType;
    }

    public void setIdType(Integer idType) {
        this.idType = idType;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }
}