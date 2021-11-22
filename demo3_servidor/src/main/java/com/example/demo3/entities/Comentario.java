package com.example.demo3.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="idcliente")
    private Integer idcliente;

    @Column(name="idactividad")
    private Integer idactividad;

    @Column(name="fecha")
    private LocalDate fecha;

    @Column(name="comentario")
    private String comentario;

    public Comentario() {
    }

    public Comentario(Integer id_cliente, Integer id_actividad, String comentario) {
        this.idcliente = id_cliente;
        this.idactividad = id_actividad;
        this.fecha = LocalDate.now();
        this.comentario = comentario;
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

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @Override
    public String toString() {
        return "Comentario{" +
                "id=" + id +
                ", id_cliente=" + idcliente +
                ", id_actividad=" + idactividad +
                ", fecha=" + fecha +
                ", comentario='" + comentario + '\'' +
                '}';
    }

}