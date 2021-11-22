package com.example.demo3.services;

import com.example.demo3.dtos.ComentarioDTO;
import com.example.demo3.entities.Comentario;
import com.example.demo3.managers.ComentarioMgr;
import com.example.demo3.mappers.ComentarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RestController("/comentarios")
public class ComentarioRestService {

    private ComentarioMgr comentarioMgr;

    private ComentarioMapper comentarioMapper;

    @Autowired
    public void ComentarioRestService(ComentarioMgr comentarioMgr, ComentarioMapper comentarioMapper){
        this.comentarioMgr = comentarioMgr;
        this.comentarioMapper = comentarioMapper;
    }

    @PostMapping("/comentarios/add")
    @Transactional
    public void addComentario(@RequestBody ComentarioDTO comentarioDTO){
        Comentario comentario = comentarioMapper.toComentario(comentarioDTO);

        comentarioMgr.addComentario(comentario.getIdcliente(),comentario.getIdactividad(),comentario.getComentario());
    }

    @GetMapping("/comentarios/{idactividad}")
    public ResponseEntity<List<ComentarioDTO>> findByActivity(@PathVariable("idactividad") Integer idactividad){
        if(comentarioMgr.getCommentsFromActivity(idactividad) != null){
            List<Comentario> lista = comentarioMgr.getCommentsFromActivity(idactividad);
            List<ComentarioDTO> lista2 = new ArrayList<ComentarioDTO>();

            for (Comentario c: lista){
                ComentarioDTO comentarioDTO = comentarioMapper.toDTO(c);
                lista2.add(comentarioDTO);
            }

            return new ResponseEntity<List<ComentarioDTO>>(lista2, HttpStatus.OK);
        }

        return null;
    }
}
