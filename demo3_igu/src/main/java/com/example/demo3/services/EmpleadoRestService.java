package com.example.demo3.services;

import com.example.demo3.dtos.EmpleadoDTO;
import com.example.demo3.dtos.OperadorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class EmpleadoRestService {

    private RestTemplate restTemplate;

    @Autowired
    public EmpleadoRestService(RestTemplate restTemplate){this.restTemplate=restTemplate;}

    public ResponseEntity addEmpleado(EmpleadoDTO empleadoDTO){
        return restTemplate.postForEntity("http://localhost:8080/empleado/add",empleadoDTO,EmpleadoDTO.class);
    }

    public OperadorDTO findOperadorFromEmpleado(Integer id){
        return restTemplate.getForEntity("http://localhost:8080/empleado/"+id,
                OperadorDTO.class,id).getBody();
    }

    public EmpleadoDTO findByEmpleadoByEmailAndPassword(String username, String password){
        return restTemplate.getForEntity("http://localhost:8080/empleado/"+username+"/"+password,
                EmpleadoDTO.class,username,password).getBody();
    }
}
