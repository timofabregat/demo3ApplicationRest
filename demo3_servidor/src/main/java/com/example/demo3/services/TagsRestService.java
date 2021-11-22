package com.example.demo3.services;

import com.example.demo3.dtos.TagsDTO;
import com.example.demo3.entities.Tags;
import com.example.demo3.exceptions.InformacionInvalida;
import com.example.demo3.exceptions.TagYaExiste;
import com.example.demo3.managers.TagsMgr;
import com.example.demo3.mappers.TagsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RestController("/tags")
public class TagsRestService {

    private TagsMgr tagsMgr;

    private TagsMapper tagsMapper;

    @Autowired
    public void TagsRestService(TagsMgr tagsMgr, TagsMapper tagsMapper){
        this.tagsMgr = tagsMgr;
        this.tagsMapper = tagsMapper;
    }

    @PostMapping("/tags/add")
    @Transactional
    public void addTag(@RequestBody TagsDTO tagsDTO) throws TagYaExiste {
        Tags tags = tagsMapper.toTags(tagsDTO);

        tagsMgr.addTag(tagsDTO.getId().getActividad(),tagsDTO.getId().getIdtags());
    }

    @DeleteMapping("/tags/{actividad}/{idtags}")
    @Transactional
    public void deleteTag(@PathVariable("actividad") Integer actividad, @PathVariable("idtags") Integer idtags) throws InformacionInvalida {
        tagsMgr.deleteTags(actividad, idtags);
    }

    @GetMapping("/tags/getall")
    @Transactional
    public ResponseEntity<List<TagsDTO>> getAll(){
        if(tagsMgr.getAll() != null){
            List<Tags> lista = tagsMgr.getAll();
            List<TagsDTO> lista2 = new ArrayList<TagsDTO>();

            for(Tags t: lista){
                TagsDTO tagsDTO = tagsMapper.toDTO(t);
                lista2.add(tagsDTO);
            }

            return new ResponseEntity<List<TagsDTO>>(lista2, HttpStatus.OK);
        }

        return null;
    }
}
