package com.example.demo3.persistence;

import com.example.demo3.entities.Actividad;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActividadRepository extends CrudRepository<Actividad,Integer> {

    List<Actividad> findAllByIdoperador(Integer idoperador);
    List<Actividad> findAllByTituloContaining(String titulo);
    List<Actividad> findAllByValidadaIsFalse();
    Actividad findActividadById(Integer id_actividad);
    Actividad findByTituloAndAndIdoperador(String titulo,Integer idoperador);

}
