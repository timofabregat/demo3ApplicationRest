package com.example.demo3.dtos;

public class TagsDTO {

    public TagsIdDTO id;

    public TagsDTO(){};

    public TagsDTO(Integer idtags, Integer actividad){
        this.id = new TagsIdDTO(idtags,actividad);
    }

    public TagsIdDTO getId() {
        return id;
    }

    public void setId(TagsIdDTO id) {
        this.id = id;
    }
}
