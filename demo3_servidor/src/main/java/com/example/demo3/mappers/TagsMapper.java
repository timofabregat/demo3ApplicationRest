package com.example.demo3.mappers;

import com.example.demo3.dtos.TagsDTO;
import com.example.demo3.entities.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TagsMapper {

    private TagsIdMapper tagsIdMapper;

    @Autowired
    public void TagsMapper(TagsIdMapper tagsIdMapper){
        this.tagsIdMapper = tagsIdMapper;
    }

    public Tags toTags(TagsDTO tagsDTO){
        Tags tags = new Tags();

        tags.setId(tagsIdMapper.toTagsId(tagsDTO.getId()));

        return tags;
    }

    public TagsDTO toDTO(Tags tags){
        TagsDTO tagsDTO = new TagsDTO();

        tagsDTO.setId(tagsIdMapper.toDTO(tags.getId()));

        return tagsDTO;
    }
}
