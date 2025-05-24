package com.example.myJavaApp1.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tai_khoan")
public class TaiKhoan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_account")
    private Integer idAccount;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role = "CUSTOMER"; // Giá trị mặc định là "CUSTOMER"

    // Constructors
    public TaiKhoan() {
    }

    public TaiKhoan(Integer idAccount, String username, String password, String role) {
        this.idAccount = idAccount;
        this.username = username;
        this.password = password;
        this.role = (role != null) ? role : "CUSTOMER"; // Đảm bảo role mặc định nếu truyền null
    }

    // Getters and Setters
    public Integer getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(Integer idAccount) {
        this.idAccount = idAccount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = (role != null) ? role : "CUSTOMER"; // Đảm bảo role mặc định nếu set null
    }
}