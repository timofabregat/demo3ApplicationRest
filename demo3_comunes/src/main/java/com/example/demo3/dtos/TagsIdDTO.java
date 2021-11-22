package com.example.demo3.dtos;

import java.io.Serializable;

public class TagsIdDTO implements Serializable {

    public Integer idtags;
    public Integer actividad;

    public TagsIdDTO(){};

    public TagsIdDTO(Integer idtags, Integer actividad){
        this.idtags = idtags;
        this.actividad = actividad;
    }

    public Integer getIdtags() {
        return idtags;
    }

    public void setIdtags(Integer idtags) {
        this.idtags = idtags;
    }

    public Integer getActividad() {
        return actividad;
    }

    public void setActividad(Integer actividad) {
        this.actividad = actividad;
    }
}
