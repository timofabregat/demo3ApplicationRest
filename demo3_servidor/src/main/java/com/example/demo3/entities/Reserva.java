package com.example.demo3.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Reserva {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO) // "id" es el id de la tabla. "identificador" es el id del operador
    private Integer id;

    @Column(name="idcliente")
    private Integer idcliente;

    @Column(name="idactividad")
    private Integer idactividad;

    @Column(name="fecha")
    private LocalDate fecha;

    @Column(name="hora")
    private LocalTime hora;

    @Column(name="cantidad")
    private Integer cantidad;

    @Column(name="validada")
    private Boolean validada;

    public Reserva(){

    }

    public Reserva(Integer idcliente, Integer idactividad, LocalDate fecha, LocalTime hora, Integer cantidad) {
        this.idcliente = idcliente;
        this.idactividad = idactividad;
        this.fecha = fecha;
        this.hora = hora;
        this.cantidad = cantidad;
        this.validada = null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdactividad() {
        return idactividad;
    }

    public void setIdactividad(Integer idactividad) {
        this.idactividad = idactividad;
    }

    public Integer getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Integer idcliente) {
        this.idcliente = idcliente;
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

    @Override
    public String toString() {
        return "Reserva{" +
                "id=" + id +
                ", idcliente=" + idcliente +
                ", idactividad=" + idactividad +
                ", fecha=" + fecha +
                ", hora=" + hora +
                ", cantidad=" + cantidad +
                ", validada=" + validada +
                '}';
    }

}