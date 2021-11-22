package com.example.demo3.dtos;

import java.time.LocalDate;

public class ClienteDTO {

    public Integer id;

    public String mail;

    public String contrasena;

    public Long documento;

    public String tipo_documento;

    public LocalDate fecha_nacimiento;

    public Boolean vacuna_covid;

    public String pais;

    public Boolean bloqueado;

    public byte[] imagencliente;

    public ClienteDTO(){}

    public ClienteDTO(Integer id, String mail, String contrasena, Long documento,
                     String tipo_documento, LocalDate fecha_nacimiento, Boolean vacuna_covid,
                     String pais, Boolean bloqueado, byte[] imagencliente) {
        this.id = id;
        this.mail = mail;
        this.contrasena = contrasena;
        this.documento = documento;
        this.tipo_documento = tipo_documento;
        this.fecha_nacimiento = fecha_nacimiento;
        this.vacuna_covid = vacuna_covid;
        this.pais = pais;
        this.bloqueado = bloqueado;
        this.imagencliente = imagencliente;
    }

    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}

    public String getMail() {return mail;}
    public void setMail(String mail) {this.mail = mail;}

    public String getContrasena() {return contrasena;}
    public void setContrasena(String contrasena) {this.contrasena = contrasena;}

    public Long getDocumento() {return documento;}
    public void setDocumento(Long documento) {this.documento = documento;}

    public String getTipo_documento() {return tipo_documento;}
    public void setTipo_documento(String tipo_documento) {this.tipo_documento = tipo_documento;}

    public LocalDate getFecha_nacimiento() {return fecha_nacimiento;}
    public void setFecha_nacimiento(LocalDate fecha_nacimiento) {this.fecha_nacimiento = fecha_nacimiento;}

    public Boolean getVacuna_covid() {return vacuna_covid;}
    public void setVacuna_covid(Boolean vacuna_covid) {this.vacuna_covid = vacuna_covid;}

    public String getPais() {return pais;}
    public void setPais(String pais) {this.pais = pais;}

    public Boolean getBloqueado() {return bloqueado;}
    public void setBloqueado(Boolean bloqueado) {this.bloqueado = bloqueado;}

    public byte[] getImagencliente() {return imagencliente;}
    public void setImagencliente(byte[] imagencliente) {this.imagencliente = imagencliente;}
}
