package com.example.demo3.services;

import com.example.demo3.dtos.CalificacionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CalificacionRestService {

    private RestTemplate restTemplate;

    @Autowired
    public CalificacionRestService(RestTemplate restTemplate){this.restTemplate = restTemplate;}

    public ResponseEntity upsortCalificacion(CalificacionDTO calificacionDTO){
        return restTemplate.postForEntity("http://localhost:8080/calificacion/add",calificacionDTO,
                CalificacionDTO.class);
    }

    public CalificacionDTO findByClienteAndActivity(Integer idcliente, Integer idactividad){
        return restTemplate.getForEntity("http://localhost:8080/calificacion/"+idcliente+"/"+idactividad,
                CalificacionDTO.class,idcliente,idactividad).getBody();
    }

    public Float puntuacionPromedio(Integer idactividad){
        return restTemplate.getForEntity("http://localhost:8080/calificacion/"+idactividad,
                Float.class,idactividad).getBody();
    }


}
