package com.example.demo3.entities;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
public class Interes {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO) // "id" es el id de la tabla
    private Integer idinteres;

    @Column(name="nombre")
    private String nombre;

    public Interes() {
    }

    public Interes(String interes) {
        this.nombre = interes;
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

    @Override
    public String toString() {
        return nombre;
    }
}