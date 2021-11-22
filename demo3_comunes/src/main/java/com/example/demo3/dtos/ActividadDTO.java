package com.example.demo3.dtos;

import java.time.LocalTime;

public class ActividadDTO {

    public Integer id;

    public String titulo;

    public byte[] imagenactividad;

    public Integer idoperador;

    public String descripcion;

    public LocalTime apertura;

    public LocalTime cierre;

    public Boolean validada;

    public Integer cupo;

    public Boolean utiliza_reserva;

    public Boolean requiere_vacuna;

    public ActividadDTO(){}

    public ActividadDTO(Integer id, String titulo, byte[] imagenactividad, Integer idoperador,
                        String descripcion, LocalTime apertura, LocalTime cierre, Boolean validada,
                        Integer cupo, Boolean utiliza_reserva, Boolean requiere_vacuna) {
        this.id = id;
        this.titulo = titulo;
        this.imagenactividad = imagenactividad;
        this.idoperador = idoperador;
        this.descripcion = descripcion;
        this.apertura = apertura;
        this.cierre = cierre;
        this.validada = validada;
        this.cupo = cupo;
        this.utiliza_reserva = utiliza_reserva;
        this.requiere_vacuna = requiere_vacuna;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public byte[] getImagenactividad() {
        return imagenactividad;
    }

    public void setImagenactividad(byte[] imagenactividad) {
        this.imagenactividad = imagenactividad;
    }

    public Integer getIdoperador() {
        return idoperador;
    }

    public void setIdoperador(Integer idoperador) {
        this.idoperador = idoperador;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalTime getApertura() {
        return apertura;
    }

    public void setApertura(LocalTime apertura) {
        this.apertura = apertura;
    }

    public LocalTime getCierre() {
        return cierre;
    }

    public void setCierre(LocalTime cierre) {
        this.cierre = cierre;
    }

    public Boolean getValidada() {
        return validada;
    }

    public void setValidada(Boolean validada) {
        this.validada = validada;
    }

    public Integer getCupo() {
        return cupo;
    }

    public void setCupo(Integer cupo) {
        this.cupo = cupo;
    }

    public Boolean getUtiliza_reserva() {
        return utiliza_reserva;
    }

    public void setUtiliza_reserva(Boolean utiliza_reserva) {
        this.utiliza_reserva = utiliza_reserva;
    }

    public Boolean getRequiere_vacuna() {
        return requiere_vacuna;
    }

    public void setRequiere_vacuna(Boolean requiere_vacuna) {
        this.requiere_vacuna = requiere_vacuna;
    }
}
