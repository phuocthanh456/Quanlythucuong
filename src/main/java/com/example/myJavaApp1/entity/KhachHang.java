package com.example.myJavaApp1.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "khach_hang")
public class KhachHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_customer")
    private Integer idCustomer;

    @Column(name = "hovaten")
    private String hoVaTen;

    @Column(name = "ngaysinh")
    private LocalDate ngaySinh;

    @Column(name = "diachi")
    private String diaChi;

    @Column(name = "sdt")
    private String sdt;

    @Column(name = "email")
    private String email;

    // Constructor mặc định (yêu cầu bởi JPA)
    public KhachHang() {
    }

    // Constructor đầy đủ
    public KhachHang(Integer idCustomer, String hoVaTen, LocalDate ngaySinh, String diaChi, String sdt, String email) {
        this.idCustomer = idCustomer;
        this.hoVaTen = hoVaTen;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.email = email;
    }

    // Getters và Setters
    public Integer getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Integer idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getHoVaTen() {
        return hoVaTen;
    }

    public void setHoVaTen(String hoVaTen) {
        this.hoVaTen = hoVaTen;
    }

    public LocalDate getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}