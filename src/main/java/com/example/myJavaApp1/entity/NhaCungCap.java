package com.example.myJavaApp1.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "nha_cung_cap")
public class NhaCungCap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_supplier")
    private Integer idSupplier;

    @Column(name = "ten")
    private String ten;

    // Constructor mặc định
    public NhaCungCap() {
    }

    // Constructor đầy đủ
    public NhaCungCap(Integer idSupplier, String ten) {
        this.idSupplier = idSupplier;
        this.ten = ten;
    }

    // Getters và Setters
    public Integer getIdSupplier() {
        return idSupplier;
    }

    public void setIdSupplier(Integer idSupplier) {
        this.idSupplier = idSupplier;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }
}