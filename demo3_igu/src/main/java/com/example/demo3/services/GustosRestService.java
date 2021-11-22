package com.example.demo3.services;

import com.example.demo3.dtos.GustosDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class GustosRestService {

    private RestTemplate restTemplate;

    @Autowired
    public GustosRestService(RestTemplate restTemplate){this.restTemplate=restTemplate;}

    public ResponseEntity addGusto(GustosDTO gustosDTO){
        return restTemplate.postForEntity("http://localhost:8080/gustos/add",gustosDTO,GustosDTO.class);
    }

    public void deleteGusto(String usuario,Integer idgusto){
        restTemplate.delete("http://localhost:8080/gustos"+usuario+"/"+idgusto,usuario,idgusto);
    }

    public List<GustosDTO> getAll(){
        return restTemplate.getForEntity("http://localhost:8080/gustos/getall",
                (Class<List<GustosDTO>>)(Object)List.class).getBody();
    }
}
