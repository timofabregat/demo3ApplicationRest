package com.example.demo3.persistence;

import com.example.demo3.entities.Comentario;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ComentarioRepository extends CrudRepository<Comentario,Integer> {

    List<Comentario> findAllByIdactividad(Integer id_actividad);

}