package com.example.demo3.dtos;

public class CalificacionDTO {

    public Integer id;

    public Integer idcliente;

    public Integer idactividad;

    public Integer puntos;

    public CalificacionDTO(){}

    public CalificacionDTO(Integer id, Integer idcliente, Integer idactividad, Integer puntos) {
        this.id = id;
        this.idcliente = idcliente;
        this.idactividad = idactividad;
        this.puntos = puntos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    public Integer getIdactividad() {
        return idactividad;
    }

    public void setIdactividad(Integer idactividad) {
        this.idactividad = idactividad;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }
}
