package com.example.demo3.managers;

import com.example.demo3.entities.Comentario;
import com.example.demo3.persistence.ActividadRepository;
import com.example.demo3.persistence.ClienteRepository;
import com.example.demo3.persistence.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComentarioMgr {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ActividadRepository actividadRepository;

    public void addComentario(Integer id_cliente, Integer id_actividad, String comentario) {
        if (clienteRepository.existsById(id_cliente) && actividadRepository.existsById(id_actividad)) {
            Comentario com = new Comentario(id_cliente, id_actividad, comentario);
            comentarioRepository.save(com);
        }
    }

    public List<Comentario> getCommentsFromActivity(Integer id_actividad) {
        return comentarioRepository.findAllByIdactividad(id_actividad);
    }

}