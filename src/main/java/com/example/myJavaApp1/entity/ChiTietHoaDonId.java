package com.example.myJavaApp1.entity;

import java.io.Serializable;
import java.util.Objects;

public class ChiTietHoaDonId implements Serializable {

    private Integer idInvoice;
    private Integer idProduct;

    // Constructors
    public ChiTietHoaDonId() {
    }

    public ChiTietHoaDonId(Integer idInvoice, Integer idProduct) {
        this.idInvoice = idInvoice;
        this.idProduct = idProduct;
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

    // Equals and HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChiTietHoaDonId that = (ChiTietHoaDonId) o;
        return Objects.equals(idInvoice, that.idInvoice) &&
                Objects.equals(idProduct, that.idProduct);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idInvoice, idProduct);
    }
}