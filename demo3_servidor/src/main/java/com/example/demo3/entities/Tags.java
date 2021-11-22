package com.example.demo3.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Tags {

    @EmbeddedId
    private TagsId id;

    public TagsId getId() {return id;}
    public void setId(TagsId id) {this.id = id;}

    public Tags(){}

    public Tags(Integer idtags,Integer actividad){
        TagsId tagsId = new TagsId(idtags,actividad);
        this.id = tagsId;
    }

    @Override
    public String toString() {
        return "Tags{" +
                "id=" + id +
                '}';
    }
}