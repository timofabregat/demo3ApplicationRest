package com.example.demo3.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Gustos {

    @EmbeddedId
    private GustosId id;

    public GustosId getId() {return id;}
    public void setId(GustosId id) {this.id = id;}

    public Gustos(){};

    public Gustos(String usuario, Integer idgusto) {
        GustosId gustosId = new GustosId(idgusto,usuario);
        this.id = gustosId;
    }

    @Override
    public String toString() {
        return "Gustos{" +
                "id=" + id +
                '}';
    }
}