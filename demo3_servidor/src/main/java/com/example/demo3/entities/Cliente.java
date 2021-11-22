package com.example.demo3.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Arrays;

@Entity // This tells Hibernate to make a table out of this class
public class Cliente {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(name="mail")
    private String mail;

    @Column(name="contrasena")
    private String contrasena;

    @Column(name="documento")
    private Long documento;

    @Column(name="tipo_documento")
    private String tipo_documento;

    @Column(name="fecha_nacimiento")
    private LocalDate fecha_nacimiento;

    @Column(name="vacuna_covid")
    private Boolean vacuna_covid;

    @Column(name="pais")
    private String pais;

    @Column(name="bloqueado")
    private Boolean bloqueado;

    @Lob
    @Column(name="imagencliente", columnDefinition = "BLOB")
    private byte[] imagencliente;

    public Cliente() {
    }

    public Cliente(String mail, String contrasena, Long documento, String tipo_documento, LocalDate fecha_nacimiento,
                   Boolean vacuna_covid, String pais, byte[] imagencliente) {
        this.mail = mail;
        this.contrasena = contrasena;
        this.documento = documento;
        this.tipo_documento = tipo_documento;
        this.fecha_nacimiento = fecha_nacimiento;
        this.vacuna_covid = vacuna_covid;
        this.pais = pais;
        this.bloqueado = false;
        this.imagencliente = imagencliente;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Long getDocumento() {
        return documento;
    }

    public void setDocumento(Long documento) {
        this.documento = documento;
    }

    public String getTipo_documento() {
        return tipo_documento;
    }

    public void setTipo_documento(String tipo_documento) {
        this.tipo_documento = tipo_documento;
    }

    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public Boolean getVacuna_covid() {
        return vacuna_covid;
    }

    public void setVacuna_covid(Boolean vacuna_covid) {
        this.vacuna_covid = vacuna_covid;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Boolean getBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(Boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    public byte[] getImagencliente() {
        return imagencliente;
    }

    public void setImagencliente(byte[] imagencliente) {
        this.imagencliente = imagencliente;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", mail='" + mail + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", documento=" + documento +
                ", tipo_documento='" + tipo_documento + '\'' +
                ", fecha_nacimiento=" + fecha_nacimiento +
                ", vacuna_covid=" + vacuna_covid +
                ", pais='" + pais + '\'' +
                ", bloqueado=" + bloqueado +
                ", imagencliente=" + Arrays.toString(imagencliente) +
                '}';
    }
}