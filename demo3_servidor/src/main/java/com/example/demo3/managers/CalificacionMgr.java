package com.example.demo3.managers;

import com.example.demo3.entities.Calificacion;
import com.example.demo3.persistence.ActividadRepository;
import com.example.demo3.persistence.CalificacionRepository;
import com.example.demo3.persistence.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalificacionMgr {

    @Autowired
    private CalificacionRepository calificacionRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ActividadRepository actividadRepository;

    public void upsortCalificacion(Integer id_cliente, Integer id_actividad, Integer puntos) {
        if (clienteRepository.existsById(id_cliente) && actividadRepository.existsById(id_actividad)) {
            Calificacion temp = calificacionRepository.findByIdclienteAndIdactividad(id_cliente, id_actividad);
            if (temp == null) {
                Calificacion calif = new Calificacion(id_cliente, id_actividad, puntos);
                calificacionRepository.save(calif);
            }
            else {
                temp.setPuntos(puntos);
                calificacionRepository.save(temp);
            }
        }
    }

    public Calificacion getFromClienteAndActivity (Integer id_cliente, Integer id_actividad) {
        return calificacionRepository.findByIdclienteAndIdactividad(id_cliente, id_actividad);
    }

    public float puntuacionPromedio (Integer id_actividad) {
        List<Calificacion> califs = calificacionRepository.findAllByIdactividad(id_actividad);
        float promedio = 0;
        if (califs.size() > 0) {
            Integer suma = 0;
            for (Calificacion calif : califs) {
                suma = suma + calif.getPuntos();
            }
            promedio = (float) (suma / califs.size());
        }
        return promedio;
    }

}