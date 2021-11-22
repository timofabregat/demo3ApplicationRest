package com.example.demo3.services;

import com.example.demo3.dtos.GustosDTO;
import com.example.demo3.entities.Gustos;
import com.example.demo3.exceptions.GustoYaExiste;
import com.example.demo3.exceptions.InformacionInvalida;
import com.example.demo3.managers.GustosMgr;
import com.example.demo3.mappers.GustosMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/gustos")
public class GustosRestService {

    private GustosMgr gustosMgr;

    private GustosMapper gustosMappers;

    @Autowired
    public void GustosRestService(GustosMgr gustosMgr, GustosMapper gustosMappers){
        this.gustosMgr = gustosMgr;
        this.gustosMappers = gustosMappers;
    }

    @PostMapping("/gustos/add")
    @Transactional
    public void addGusto(@RequestBody GustosDTO gustosDTO) throws GustoYaExiste {
        Gustos gustos = gustosMappers.toGustos(gustosDTO);

        gustosMgr.addGusto(gustosDTO.getId().getUsuario(),gustos.getId().getIdgustos());
    }


    @DeleteMapping("/gustos/{usuario}/{idgustos}")
    @Transactional
    public void deleteGusto(@PathVariable("usuario") String usuario, @PathVariable("idgustos") Integer idgustos) throws InformacionInvalida {
        gustosMgr.deleteGusto(usuario, idgustos);
    }


    @GetMapping("/gustos/getall")
    @Transactional
    public ResponseEntity<List<GustosDTO>> getAll(){
        if(gustosMgr.getAll() != null){

            List<Gustos> lista = gustosMgr.getAll();
            List<GustosDTO> lista2 = new ArrayList<GustosDTO>();

            for(Gustos g: lista){
                GustosDTO gustosDTO = gustosMappers.toDTO(g);
                lista2.add(gustosDTO);
            }

            return new ResponseEntity<List<GustosDTO>>(lista2, HttpStatus.OK);

        }

        return null;
    }
}
