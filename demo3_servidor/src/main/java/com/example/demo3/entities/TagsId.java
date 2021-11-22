package com.example.demo3.entities;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class TagsId implements Serializable{

    private Integer idtags;
    private Integer actividad;

    public Integer getIdtags() {return idtags;}
    public void setIdtags(Integer idtags) {this.idtags = idtags;}

    public Integer getActividad() {return actividad;}
    public void setActividad(Integer actividad) {this.actividad = actividad;}

    @Override
    public boolean equals(Object obj){
        return super.equals(obj);
    }

    @Override
    public int hashCode(){
        return super.hashCode();
    }

    public TagsId(){}

    public TagsId(Integer idtags, Integer actividad){
        this.idtags = idtags;
        this.actividad = actividad;
    }

    @Override
    public String toString() {
        return "TagsId{" +
                "idtags=" + idtags +
                ", actividad=" + actividad +
                '}';
    }
}