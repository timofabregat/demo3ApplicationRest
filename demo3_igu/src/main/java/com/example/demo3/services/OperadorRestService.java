package com.example.demo3.services;

import com.example.demo3.dtos.OperadorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class OperadorRestService {

    private RestTemplate restTemplate;

    @Autowired
    public OperadorRestService(RestTemplate restTemplate){this.restTemplate=restTemplate;}

    public ResponseEntity addOperador(OperadorDTO operadorDTO){
        return restTemplate.postForEntity("http://localhost:8080/operadores/add",
                operadorDTO,OperadorDTO.class);
    }

    public OperadorDTO finById(Integer id){
        return restTemplate.getForEntity("http://localhost:8080/operadores/"+id,OperadorDTO.class,id).getBody();
    }

    public List<OperadorDTO> getAll(){
        return restTemplate.getForEntity("http://localhost:8080/operadores/getall",
                (Class<List<OperadorDTO>>)(Object)List.class).getBody();
    }

    public void bloquearOperador(OperadorDTO operadorDTO){
        restTemplate.put("http://localhost:8080/operadores/bloquear",operadorDTO,OperadorDTO.class);
    }

    public void desbloquearOperador(OperadorDTO operadorDTO){
        restTemplate.put("http://localhost:8080/operadores/desbloquear",operadorDTO,OperadorDTO.class);
    }

    public void updateOperador(Integer id,String empresa, String departamento,Long telefono,
                               String emialcontacto,String direccion){

        restTemplate.put("http://localhost:8080/operadores/update/"
                        +id+"/"+empresa+"/"+departamento+"/"+telefono+"/"+emialcontacto+"/"+direccion,
                        id,empresa,departamento,telefono,emialcontacto,direccion);
    }
}
