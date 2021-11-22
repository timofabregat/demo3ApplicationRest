package com.example.demo3.mappers;

import com.example.demo3.dtos.ComentarioDTO;
import com.example.demo3.entities.Comentario;
import org.springframework.stereotype.Component;

@Component
public class ComentarioMapper {

    public Comentario toComentario(ComentarioDTO comentarioDTO){
        Comentario comentario = new Comentario();

        comentario.setId(comentarioDTO.getId());
        comentario.setIdcliente(comentarioDTO.getIdcliente());
        comentario.setIdactividad(comentarioDTO.getIdactividad());
        comentario.setFecha(comentarioDTO.getFecha());
        comentario.setComentario(comentarioDTO.getComentario());

        return comentario;
    }

    public ComentarioDTO toDTO(Comentario comentario){
        ComentarioDTO comentarioDTO = new ComentarioDTO();

        comentarioDTO.setId(comentario.getId());
        comentarioDTO.setIdcliente(comentario.getIdcliente());
        comentarioDTO.setIdactividad(comentario.getIdactividad());
        comentarioDTO.setFecha(comentario.getFecha());
        comentarioDTO.setComentario(comentario.getComentario());

        return comentarioDTO;
    }
}
