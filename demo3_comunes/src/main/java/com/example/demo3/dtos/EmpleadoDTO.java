package com.example.demo3.dtos;

public class EmpleadoDTO {

    public Integer id;

    public String username;

    public String password;

    public Integer idoperador;

    public EmpleadoDTO(){}

    public EmpleadoDTO(Integer id, String username, String password, Integer idoperador) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.idoperador = idoperador;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getIdoperador() {
        return idoperador;
    }

    public void setIdoperador(Integer idoperador) {
        this.idoperador = idoperador;
    }
}
