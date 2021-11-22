package com.example.demo3.dtos;

public class AdminDTO {

    public Integer id_admin;

    public String email;

    public String password;

    public AdminDTO(){}

    public AdminDTO(Integer id_admin, String email, String password) {
        this.id_admin = id_admin;
        this.email = email;
        this.password = password;
    }

    public Integer getId_admin() {
        return id_admin;
    }

    public void setId_admin(Integer id_admin) {
        this.id_admin = id_admin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
