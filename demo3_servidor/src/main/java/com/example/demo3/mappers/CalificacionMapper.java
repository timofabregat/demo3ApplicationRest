package com.example.demo3.mappers;

import com.example.demo3.dtos.CalificacionDTO;
import com.example.demo3.entities.Calificacion;
import org.springframework.stereotype.Component;

@Component
public class CalificacionMapper{

    public Calificacion toCalificacion(CalificacionDTO calificacionDTO){
        Calificacion calificacion = new Calificacion();

        calificacion.setId(calificacionDTO.getId());
        calificacion.setIdcliente(calificacionDTO.getIdcliente());
        calificacion.setIdactividad(calificacionDTO.getIdactividad());
        calificacion.setPuntos(calificacionDTO.getPuntos());

        return calificacion;
    }

    public CalificacionDTO toDTO(Calificacion calificacion){
        CalificacionDTO calificacionDTO = new CalificacionDTO();

        calificacionDTO.setId(calificacion.getId());
        calificacionDTO.setIdcliente(calificacion.getIdcliente());
        calificacionDTO.setIdactividad(calificacion.getIdactividad());
        calificacionDTO.setPuntos(calificacion.getPuntos());

        return calificacionDTO;
    }
}
