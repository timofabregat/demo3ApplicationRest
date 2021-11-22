package com.example.demo3.entities;

import javax.persistence.*;

@Entity
public class Calificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="idcliente")
    private Integer idcliente;

    @Column(name="idactividad")
    private Integer idactividad;

    @Column(name="puntos")
    private Integer puntos;

    public Calificacion() {
    }

    public Calificacion(Integer id_cliente, Integer id_actividad, Integer puntos) {
        this.idcliente = id_cliente;
        this.idactividad = id_actividad;
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

    public void setIdcliente(Integer id_cliente) {
        this.idcliente = id_cliente;
    }

    public Integer getIdactividad() {
        return idactividad;
    }

    public void setIdactividad(Integer id_actividad) {
        this.idactividad = id_actividad;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }

    @Override
    public String toString() {
        return "Calificacion{" +
                "id=" + id +
                ", id_cliente=" + idcliente +
                ", id_actividad=" + idactividad +
                ", puntos=" + puntos +
                '}';
    }

}