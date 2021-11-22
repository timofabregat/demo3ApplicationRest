package com.example.demo3.services;

import com.example.demo3.dtos.ClienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class ClienteRestService {

    private RestTemplate restTemplate;

    @Autowired
    private ClienteRestService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public ResponseEntity addCliente(ClienteDTO clienteDTO){
        return restTemplate.postForEntity("http://localhost:8080/clientes",
                clienteDTO, ClienteDTO.class);
    }


    public ClienteDTO findByMailandPassword(String email, String contrasena){
        return restTemplate.getForEntity("http://localhost:8080/clientes/" + email + "/" + contrasena,
                ClienteDTO.class,email,contrasena).getBody();

    }

    public List<ClienteDTO> findByContainingEmail(String mail){
        return restTemplate.getForEntity("http://localhost:8080/clientes/" + mail,
                (Class<List<ClienteDTO>>)(Object)List.class, mail).getBody();
    }

    public List<ClienteDTO> getAll(){
        return restTemplate.getForEntity("http://localhost:8080/clientes/getall",
                (Class<List<ClienteDTO>>)(Object)List.class).getBody();
    }

    public void desbloquearCliente(ClienteDTO clienteDTO){
         restTemplate.put("http://localhost:8080/clientes/desbloquear",clienteDTO,ClienteDTO.class);
    }

    public void bloquearCliente(ClienteDTO clienteDTO){
        restTemplate.put("http://localhost:8080/clientes/bloquear",clienteDTO,ClienteDTO.class);
    }

    public void updateCliente(Integer id, String contrasena, Boolean vacuna){
        restTemplate.put("/clientes/update/" +id + "/" + contrasena + "/" + vacuna,
                id,contrasena,vacuna);
    }

    public void updateImagen(Integer id, byte[] imagencliente){
        restTemplate.put("/clientes/updateimagen/" + id + "/" + imagencliente,
                id,imagencliente);
    }

    public String findUsernameById(Integer id){
        return restTemplate.getForEntity("/clientes/username/" + id,String.class,id).getBody();
    }


}
