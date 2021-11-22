package com.example.demo3.entities;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class GustosId implements Serializable {

    private Integer idgustos;
    private String usuario;

    public Integer getIdgustos() {return idgustos;}
    public void setIdgustos(Integer idgustos) {this.idgustos = idgustos;}

    public String getUsuario() {return usuario;}
    public void setUsuario(String usuario) {this.usuario = usuario;}

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public GustosId(){};

    public GustosId(Integer idgustos, String usuario) {
        this.idgustos = idgustos;
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "GustosId{" +
                "idgustos=" + idgustos +
                ", usuario='" + usuario + '\'' +
                '}';
    }
}