package com.example.demo3.managers;

import com.example.demo3.entities.Gustos;
import com.example.demo3.entities.Tags;
import com.example.demo3.exceptions.GustoYaExiste;
import com.example.demo3.exceptions.InformacionInvalida;
import com.example.demo3.exceptions.TagYaExiste;
import com.example.demo3.persistence.GustosRepository;
import com.example.demo3.persistence.TagsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagsMgr {

    @Autowired
    private TagsRepository tagsRepository;

    public void addTag(Integer acitividadId, Integer idTag) throws TagYaExiste{
        List<Tags> tags_check = tagsRepository.findAllByIdIdtagsAndAndIdActividad(idTag,acitividadId);

        if(tags_check != null && tags_check.size()>0){
            throw new TagYaExiste();
        }

        Tags tag = new Tags(idTag,acitividadId);
        tagsRepository.save(tag);
    }

    public void deleteTags(Integer actividadId, Integer idTag) throws InformacionInvalida{
        List<Tags> tags_check = tagsRepository.findAllByIdIdtagsAndAndIdActividad(idTag,actividadId);

        if(tags_check == null && tags_check.size() == 0){
            throw new InformacionInvalida();
        }

        tagsRepository.delete(tags_check.get(0));
    }

    public List<Tags> getAll(){
        return (List<Tags>) tagsRepository.findAll();
    }
}