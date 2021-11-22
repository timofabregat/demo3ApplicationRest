package com.example.demo3.services;

import com.example.demo3.dtos.ComentarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class ComentarioRestService {

    private RestTemplate restTemplate;

    @Autowired
    public ComentarioRestService(RestTemplate restTemplate){this.restTemplate=restTemplate;}

    public ResponseEntity addComentario(ComentarioDTO comentarioDTO){
        return restTemplate.postForEntity("http://localhost:8080/comentarios/add",
                comentarioDTO,ComentarioDTO.class);
    }

    public List<ComentarioDTO> findByActivity(Integer idactividad){
        return restTemplate.getForEntity("http://localhost:8080/comentarios/"+idactividad,
                (Class<List<ComentarioDTO>>)(Object)List.class,idactividad).getBody();
    }
}
