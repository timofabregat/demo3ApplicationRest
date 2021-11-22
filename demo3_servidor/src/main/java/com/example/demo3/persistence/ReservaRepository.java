package com.example.demo3.persistence;

import com.example.demo3.entities.Reserva;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservaRepository extends CrudRepository<Reserva, Integer> {

    List<Reserva> findAllByIdactividadAndFecha(Integer id_actividad, LocalDate fecha);
    List<Reserva> findAllByIdactividad(Integer id_actividad);
    List<Reserva> findAllByIdcliente(Integer id_cliente);

}