package com.example.demo3.services;

import com.example.demo3.dtos.AdminDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AdminRestService {

    private RestTemplate restTemplate;

    @Autowired
    public AdminRestService(RestTemplate restTemplate){this.restTemplate=restTemplate;}

    public ResponseEntity addAdmin(AdminDTO adminDTO){
        return restTemplate.postForEntity("http://localhost:8080/admin/add",adminDTO,AdminDTO.class);
    }

    public AdminDTO findByMailandPassword(String mail, String password){
        return restTemplate.getForEntity("http://localhost:8080/admin/"+mail+"/"+password,
                AdminDTO.class,mail,password).getBody();
    }
}
