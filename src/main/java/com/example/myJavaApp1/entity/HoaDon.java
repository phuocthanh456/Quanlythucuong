package com.example.myJavaApp1.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "hoa_don")
public class HoaDon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_invoice")
    private Integer idInvoice;

    @Column(name = "id_customer")
    private Integer idCustomer;

    @Column(name = "ngaylap")
    private java.sql.Date ngayLap;

    @Column(name = "tongtien")
    private Integer tongTien;

    // Constructors
    public HoaDon() {
    }

    public HoaDon(Integer idInvoice, Integer idCustomer, java.sql.Date ngayLap, Integer tongTien) {
        this.idInvoice = idInvoice;
        this.idCustomer = idCustomer;
        this.ngayLap = ngayLap;
        this.tongTien = tongTien;
    }

    // Getters and Setters
    public Integer getIdInvoice() {
        return idInvoice;
    }

    public void setIdInvoice(Integer idInvoice) {
        this.idInvoice = idInvoice;
    }

    public Integer getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Integer idCustomer) {
        this.idCustomer = idCustomer;
    }

    public java.sql.Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(java.sql.Date ngayLap) {
        this.ngayLap = ngayLap;
    }

    public Integer getTongTien() {
        return tongTien;
    }

    public void setTongTien(Integer tongTien) {
        this.tongTien = tongTien;
    }
}