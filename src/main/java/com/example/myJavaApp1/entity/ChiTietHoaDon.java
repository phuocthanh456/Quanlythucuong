package com.example.myJavaApp1.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "chi_tiet_hoa_don")
@IdClass(ChiTietHoaDonId.class) // Thêm lại annotation này
public class ChiTietHoaDon {

    @Id
    @Column(name = "id_invoice")
    private Integer idInvoice;

    @Id
    @Column(name = "id_product")
    private Integer idProduct;

    @Column(name = "soluong")
    private Integer soLuong;

    @Column(name = "dongia")
    private Double donGia;

    @Column(name = "thanhtien")
    private Double thanhTien;

    // Constructors
    public ChiTietHoaDon() {
    }

    public ChiTietHoaDon(Integer idInvoice, Integer idProduct, Integer soLuong, Double donGia, Double thanhTien) {
        this.idInvoice = idInvoice;
        this.idProduct = idProduct;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = thanhTien;
    }

    // Getters and Setters
    public Integer getIdInvoice() {
        return idInvoice;
    }

    public void setIdInvoice(Integer idInvoice) {
        this.idInvoice = idInvoice;
    }

    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public Double getDonGia() {
        return donGia;
    }

    public void setDonGia(Double donGia) {
        this.donGia = donGia;
    }

    public Double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(Double thanhTien) {
        this.thanhTien = thanhTien;
    }
}