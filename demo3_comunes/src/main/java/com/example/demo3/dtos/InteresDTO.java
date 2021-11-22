package com.example.demo3.dtos;

public class InteresDTO {

    public Integer idinteres;

    public String nombre;

    public InteresDTO(){}

    public InteresDTO(Integer idinteres, String nombre) {
        this.idinteres = idinteres;
        this.nombre = nombre;
    }

    public Integer getIdinteres() {
        return idinteres;
    }

    public void setIdinteres(Integer idinteres) {
        this.idinteres = idinteres;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
