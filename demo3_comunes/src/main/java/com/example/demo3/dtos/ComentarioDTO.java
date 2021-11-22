package com.example.demo3.dtos;

import java.time.LocalDate;

public class ComentarioDTO {

    public Integer id;

    public Integer idcliente;

    public Integer idactividad;

    public LocalDate fecha;

    public String comentario;

    public ComentarioDTO(){}

    public ComentarioDTO(Integer id, Integer idcliente, Integer idactividad,
                         LocalDate fecha, String comentario) {
        this.id = id;
        this.idcliente = idcliente;
        this.idactividad = idactividad;
        this.fecha = fecha;
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

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
