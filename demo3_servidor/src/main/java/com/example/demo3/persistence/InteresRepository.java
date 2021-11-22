package com.example.demo3.persistence;

import com.example.demo3.entities.Interes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InteresRepository extends CrudRepository<Interes,Integer> {

    Interes findByNombre(String nombre);

    Interes findByIdinteres(Integer id);


}