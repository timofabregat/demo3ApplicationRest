package com.example.demo3.persistence;

import com.example.demo3.entities.Operador;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperadorRepository extends CrudRepository<Operador,Integer> {

    List<Operador> findAllByempresa(String empresa);
    Operador findOperadorById(Integer id);
    Operador findOperadorByEmpresa(String empresa);
}