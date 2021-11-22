package com.example.demo3.dtos;

public class GustosDTO {

    public GustosIdDTO id;

    public GustosDTO(){};

    public GustosDTO(Integer idgusto, String usuario){
        this.id = new GustosIdDTO(idgusto,usuario);
    }

    public GustosIdDTO getId() {
        return id;
    }

    public void setId(GustosIdDTO id) {
        this.id = id;
    }
}
