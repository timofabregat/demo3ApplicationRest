package com.example.demo3.services;

import com.example.demo3.dtos.ReservaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@Component
public class ReservaRestService {

    private RestTemplate restTemplate;

    @Autowired
    public ReservaRestService(RestTemplate restTemplate){this.restTemplate=restTemplate;}

    public ResponseEntity addReserva(ReservaDTO reservaDTO){
        return restTemplate.postForEntity("http://localhost:8080/reservas/add",reservaDTO,ReservaDTO.class);
    }

    public List<ReservaDTO> findByActivityInDate(Integer idactividad, LocalDate fecha){
        return restTemplate.getForEntity("http://localhost:8080/reservas/"+idactividad+"/"+fecha,
                (Class<List<ReservaDTO>>)(Object)List.class,idactividad,fecha).getBody();
    }

    public List<ReservaDTO> findByActividad(Integer idactividad){
        return restTemplate.getForEntity("http://localhost:8080/reservas/"+idactividad,
                (Class<List<ReservaDTO>>)(Object)List.class,idactividad).getBody();
    }

    public List<ReservaDTO> findByCliente(Integer idcliente){
        return restTemplate.getForEntity("http://localhost:8080/reservas/"+idcliente,
                (Class<List<ReservaDTO>>)(Object)List.class,idcliente).getBody();
    }

    public void updateValidada(ReservaDTO reservaDTO,Boolean validada){
        restTemplate.put("http://localhost:8080/reservas/"+validada,
                reservaDTO,ReservaDTO.class, validada);
    }


}
