package com.example.demo3.dtos;

public class OperadorDTO {

    public Integer id;

    public String empresa;

    public String departamento;

    public Long telefono;

    public String emailcontacto;

    public String direccion;

    public Boolean bloqueado;

    public OperadorDTO(){}

    public OperadorDTO(Integer id, String empresa, String departamento, Long telefono,
                       String emailcontacto, String direccion, Boolean bloqueado) {
        this.id = id;
        this.empresa = empresa;
        this.departamento = departamento;
        this.telefono = telefono;
        this.emailcontacto = emailcontacto;
        this.direccion = direccion;
        this.bloqueado = bloqueado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public Long getTelefono() {
        return telefono;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }

    public String getEmailcontacto() {
        return emailcontacto;
    }

    public void setEmailcontacto(String emailcontacto) {
        this.emailcontacto = emailcontacto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Boolean getBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(Boolean bloqueado) {
        this.bloqueado = bloqueado;
    }
}
