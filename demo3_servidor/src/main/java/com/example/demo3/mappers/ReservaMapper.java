package com.example.demo3.mappers;

import com.example.demo3.dtos.ReservaDTO;
import com.example.demo3.entities.Reserva;
import org.springframework.stereotype.Component;

@Component
public class ReservaMapper {

    public Reserva toReserva(ReservaDTO reservaDTO){
        Reserva reserva = new Reserva();

        reserva.setId(reservaDTO.getId());
        reserva.setIdcliente(reservaDTO.getIdcliente());
        reserva.setIdactividad(reservaDTO.getIdactividad());
        reserva.setFecha(reservaDTO.getFecha());
        reserva.setHora(reservaDTO.getHora());
        reserva.setCantidad(reservaDTO.getCantidad());
        reserva.setValidada(reservaDTO.getValidada());

        return reserva;
    }

    public ReservaDTO toDTO(Reserva reserva){
        ReservaDTO reservaDTO = new ReservaDTO();

        reservaDTO.setId(reserva.getId());
        reservaDTO.setIdcliente(reserva.getIdcliente());
        reservaDTO.setIdactividad(reserva.getIdactividad());
        reservaDTO.setFecha(reserva.getFecha());
        reservaDTO.setHora(reserva.getHora());
        reservaDTO.setCantidad(reserva.getCantidad());
        reservaDTO.setValidada(reserva.getValidada());

        return reservaDTO;
    }
}
