package com.example.demo3.services;

import com.example.demo3.dtos.InteresDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class InteresRestService {

    private RestTemplate restTemplate;

    @Autowired
    private InteresRestService(RestTemplate restTemplate){this.restTemplate = restTemplate;}

    public ResponseEntity addInteres(InteresDTO interesDTO){
        return restTemplate.postForEntity("http://localhost:8080/intereses",interesDTO,InteresDTO.class);
    }

    public InteresDTO findByNombre(String nombre){
        return restTemplate.getForEntity("http://localhost:8080/intereses/" + nombre ,
                InteresDTO.class,nombre).getBody();
    }

    public InteresDTO findById(Integer id){
        return restTemplate.getForEntity("http://localhost:8080/intereses/" + id,
                InteresDTO.class,id).getBody();
    }

    public List<InteresDTO> getAll(){
        return restTemplate.getForEntity("http://localhost:8080/intereses/getall",
                (Class<List<InteresDTO>>)(Object)List.class).getBody();
    }
}
