package com.example.demo3.entities;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // "id" es el id de la tabla
    private Integer id_admin;

    @Column(name = "mail")
    private String mail;

    @Column(name = "password")
    private String password;

    public Admin() {
    }

    public Admin(String mail, String password) {
        this.mail = mail;
        this.password = password;
    }

    public Integer getId_admin() {
        return id_admin;
    }

    public void setId_admin(Integer id_admin) {
        this.id_admin = id_admin;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}