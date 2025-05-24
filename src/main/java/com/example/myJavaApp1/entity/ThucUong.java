package com.example.myJavaApp1.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "thuc_uong")
public class ThucUong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private Integer idProduct;

    @Column(name = "id_type")
    private Integer idType;

    @Column(name = "id_supplier")
    private Integer idSupplier;

    @Column(name = "ten")
    private String ten;

    @Column(name = "dongia")
    private Double donGia;

    @Column(name = "soluong")
    private Integer soLuong;

    @Column(name = "nsx")
    private String nsx;

    @Column(name = "hsd")
    private String hsd;

    @Column(name = "image_url")
    private String imageUrl;

    // Constructors
    public ThucUong() {
    }

    public ThucUong(Integer idProduct, Integer idType, Integer idSupplier, String ten, Double donGia, Integer soLuong, String nsx, String hsd, String imageUrl) {
        this.idProduct = idProduct;
        this.idType = idType;
        this.idSupplier = idSupplier;
        this.ten = ten;
        this.donGia = donGia;
        this.soLuong = soLuong;
        this.nsx = nsx;
        this.hsd = hsd;
        this.imageUrl = imageUrl;
    }

    // Getters and Setters
    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }

    public Integer getIdType() {
        return idType;
    }

    public void setIdType(Integer idType) {
        this.idType = idType;
    }

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

    public Double getDonGia() {
        return donGia;
    }

    public void setDonGia(Double donGia) {
        this.donGia = donGia;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public String getNsx() {
        return nsx;
    }

    public void setNsx(String nsx) {
        this.nsx = nsx;
    }

    public String getHsd() {
        return hsd;
    }

    public void setHsd(String hsd) {
        this.hsd = hsd;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}