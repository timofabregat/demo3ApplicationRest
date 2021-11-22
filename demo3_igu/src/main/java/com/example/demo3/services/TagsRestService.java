package com.example.demo3.services;

import com.example.demo3.dtos.TagsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class TagsRestService {

    private RestTemplate restTemplate;

    @Autowired
    public TagsRestService(RestTemplate restTemplate){this.restTemplate=restTemplate;}

    public ResponseEntity addTag(TagsDTO tagsDTO){
        return restTemplate.postForEntity("http://localhost:8080/tags/add",tagsDTO,TagsDTO.class);
    }

    public List<TagsDTO> getAll(){
        return restTemplate.getForEntity("http://localhost:8080/tags/getall",
                (Class<List<TagsDTO>>)(Object)List.class).getBody();
    }

    public void deleteTag(Integer actividad,Integer idtags){
        restTemplate.delete("http://localhost:8080/tags/"+actividad+"/"+idtags,actividad,idtags);
    }
}
