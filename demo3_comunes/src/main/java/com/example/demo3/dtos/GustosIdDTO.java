package com.example.demo3.dtos;

import java.io.Serializable;

public class GustosIdDTO implements Serializable {

    public Integer idgustos;
    public String usuario;

    public GustosIdDTO(){}

    public GustosIdDTO(Integer idgusto, String usuario){
        this.idgustos = idgusto;
        this.usuario = usuario;
    }

    public Integer getIdgustos() {
        return idgustos;
    }

    public void setIdgustos(Integer idgusto) {
        this.idgustos = idgusto;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
