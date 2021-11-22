package com.example.demo3.persistence;


import com.example.demo3.entities.Gustos;
import com.example.demo3.entities.Tags;
import com.example.demo3.entities.TagsId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagsRepository extends JpaRepository<Tags, TagsId> {

    List<Tags> findAllByIdIdtags(Integer idtags);

    List<Tags> findAllByIdActividad(Integer actividad);

    List<Tags> findAllByIdIdtagsAndAndIdActividad(Integer idtags, Integer actividad);

    List<Tags> findAllById(Integer id);

    @Override
    List<Tags> findAllById(Iterable<TagsId> tagsIds);
}