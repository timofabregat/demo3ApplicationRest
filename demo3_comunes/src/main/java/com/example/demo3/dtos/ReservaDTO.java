package com.example.demo3.dtos;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReservaDTO {

    public Integer id;

    public Integer idcliente;

    public Integer idactividad;

    public LocalDate fecha;

    public LocalTime hora;

    public Integer cantidad;

    public Boolean validada;

    public ReservaDTO(){}

    public ReservaDTO(Integer id, Integer idcliente, Integer idactividad,
                      LocalDate fecha, LocalTime hora, Integer cantidad, Boolean validada) {
        this.id = id;
        this.idcliente = idcliente;
        this.idactividad = idactividad;
        this.fecha = fecha;
        this.hora = hora;
        this.cantidad = cantidad;
        this.validada = validada;
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

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Boolean getValidada() {
        return validada;
    }

    public void setValidada(Boolean validada) {
        this.validada = validada;
    }
}
