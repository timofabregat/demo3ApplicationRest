package com.example.demo3.services;


import com.example.demo3.dtos.CalificacionDTO;
import com.example.demo3.entities.Calificacion;
import com.example.demo3.managers.CalificacionMgr;
import com.example.demo3.mappers.CalificacionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController("/calificacion")
public class CalificacionRestService {

    private CalificacionMgr calificacionMgr;

    private CalificacionMapper calificacionMapper;

    @Autowired
    public void CalificaconRestService(CalificacionMgr calificacionMgr, CalificacionMapper calificacionMapper){
        this.calificacionMgr = calificacionMgr;
        this.calificacionMapper = calificacionMapper;
    }

    @PostMapping("/calificacion/add")
    @Transactional
    public void upsortCalificacion(@RequestBody CalificacionDTO calificacionDTO){
        Calificacion calificacion = calificacionMapper.toCalificacion(calificacionDTO);

        calificacionMgr.upsortCalificacion(calificacion.getIdcliente(),calificacion.getIdactividad(),calificacion.getPuntos());
    }

    @GetMapping("/calificacion/{idcliente}/{idactividad}")
    @Transactional
    public ResponseEntity<CalificacionDTO> findbyClienteAndActivity(@PathVariable("idcliente") Integer idcliente,
                                                                    @PathVariable("idactividad") Integer idactividad ){
        if(calificacionMgr.getFromClienteAndActivity(idcliente, idactividad) != null){
            Calificacion calificacion = calificacionMgr.getFromClienteAndActivity(idcliente, idactividad);

            CalificacionDTO calificacionDTO = calificacionMapper.toDTO(calificacion);

            return new ResponseEntity<CalificacionDTO>(calificacionDTO, HttpStatus.OK);
        }

        return null;
    }

    @GetMapping("/calificacion/{idactividad}")
    @Transactional
    public ResponseEntity<Float> puntuacionPromedio(@PathVariable("idactividad") Integer idactividad){
        Float promedio = calificacionMgr.puntuacionPromedio(idactividad);

        return new ResponseEntity<Float>(promedio , HttpStatus.OK);
    }
}
