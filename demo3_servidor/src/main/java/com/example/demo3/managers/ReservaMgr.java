package com.example.demo3.managers;

import com.example.demo3.entities.Reserva;
import com.example.demo3.persistence.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class ReservaMgr {

    @Autowired
    private ReservaRepository reservaRepository;

    public void crearReserva(Integer id_cliente, Integer id_actividad, LocalDate fecha, LocalTime hora, Integer cantidad) {
        Reserva res = new Reserva(id_cliente, id_actividad, fecha, hora, cantidad);
        // los chequeos ya se hacen en el controller para poder mostrar las horas disponibles
        reservaRepository.save(res);
    }

    public List<Reserva> getFromActivityInDate(Integer id_actividad, LocalDate fecha) {
        return reservaRepository.findAllByIdactividadAndFecha(id_actividad, fecha);
    }

    public List<Reserva> getFromActividad(Integer id_actividad) {
        return reservaRepository.findAllByIdactividad(id_actividad);
    }

    public void setValidada(Reserva res, Boolean b) {
        res.setValidada(b);
        reservaRepository.save(res);
    }

    public List<Reserva> getReservasFromCliente(Integer id_cliente) {
        return reservaRepository.findAllByIdcliente(id_cliente);
    }

}