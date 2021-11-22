package com.example.demo3.mappers;

import com.example.demo3.dtos.TagsIdDTO;
import com.example.demo3.entities.TagsId;
import org.springframework.stereotype.Component;

@Component
public class TagsIdMapper {

    public TagsId toTagsId(TagsIdDTO tagsIdDTO){
        TagsId tagsId = new TagsId();

        tagsId.setIdtags(tagsIdDTO.getIdtags());
        tagsId.setActividad(tagsIdDTO.getActividad());

        return tagsId;
    }

    public TagsIdDTO toDTO(TagsId tagsId){
        TagsIdDTO tagsIdDTO = new TagsIdDTO();

        tagsIdDTO.setIdtags(tagsId.getIdtags());
        tagsIdDTO.setActividad(tagsId.getActividad());

        return tagsIdDTO;
    }
}
