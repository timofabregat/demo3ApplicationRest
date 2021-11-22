package com.example.demo3.persistence;

import com.example.demo3.entities.Calificacion;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CalificacionRepository extends CrudRepository<Calificacion, Integer> {

    Calificacion findByIdclienteAndIdactividad(Integer idCliente, Integer idActividad);
    List<Calificacion> findAllByIdactividad(Integer id_actividad);

}