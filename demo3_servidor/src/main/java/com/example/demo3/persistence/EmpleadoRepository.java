package com.example.demo3.persistence;

import com.example.demo3.entities.Empleado;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpleadoRepository extends CrudRepository<Empleado,Integer> {

    List<Empleado> findAllByUsername(String username);
    Empleado findByUsernameAndPassword(String username, String password);
    Empleado findEmpleadoById(Integer id);

}