package com.example.demo3.mappers;

import com.example.demo3.dtos.ActividadDTO;
import com.example.demo3.entities.Actividad;
import org.springframework.stereotype.Component;

@Component
public class ActividadMapper {

    public Actividad toActividad(ActividadDTO actividadDTO){
        Actividad actividad = new Actividad();

        actividad.setId(actividadDTO.getId());
        actividad.setTitulo(actividadDTO.getTitulo());
        actividad.setImagenactividad(actividadDTO.getImagenactividad());
        actividad.setIdoperador(actividadDTO.getIdoperador());
        actividad.setDescripcion(actividadDTO.getDescripcion());
        actividad.setApertura(actividadDTO.getApertura());
        actividad.setCierre(actividadDTO.getCierre());
        actividad.setValidada(actividadDTO.getValidada());
        actividad.setCupo(actividadDTO.getCupo());
        actividad.setUtiliza_reservas(actividadDTO.getUtiliza_reserva());
        actividad.setRequiere_vacuna(actividadDTO.getRequiere_vacuna());

        return actividad;
    }

    public ActividadDTO toDTO(Actividad actividad){
        ActividadDTO actividadDTO = new ActividadDTO();

        actividadDTO.setId(actividad.getId());
        actividadDTO.setTitulo(actividad.getTitulo());
        actividadDTO.setImagenactividad(actividad.getImagenactividad());
        actividadDTO.setIdoperador(actividad.getIdoperador());
        actividadDTO.setDescripcion(actividad.getDescripcion());
        actividadDTO.setApertura(actividad.getApertura());
        actividadDTO.setCierre(actividad.getCierre());
        actividadDTO.setValidada(actividad.getValidada());
        actividadDTO.setCupo(actividad.getCupo());
        actividadDTO.setUtiliza_reserva(actividad.getUtiliza_reservas());
        actividadDTO.setRequiere_vacuna(actividad.getRequiere_vacuna());

        return actividadDTO;
    }
}
