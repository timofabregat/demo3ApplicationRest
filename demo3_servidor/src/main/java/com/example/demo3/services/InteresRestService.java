package com.example.demo3.services;

import com.example.demo3.dtos.InteresDTO;
import com.example.demo3.entities.Interes;
import com.example.demo3.exceptions.InteresYaExiste;
import com.example.demo3.managers.InteresMgr;
import com.example.demo3.mappers.InteresMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/intereses")
public class InteresRestService {

    private InteresMgr interesMgr;

    private InteresMapper interesMapper;

    @Autowired
    public void InteresRestService(InteresMgr interesMgr, InteresMapper interesMapper){
        this.interesMapper = interesMapper;
        this.interesMgr = interesMgr;
    }

    @PostMapping("/intereses/add")
    @Transactional
    public void addInteres(@RequestBody InteresDTO interesDTO) throws InteresYaExiste {
        Interes interes = interesMapper.toInteres(interesDTO);

        interesMgr.addInteres(interes.getNombre());
    }

    @GetMapping("intereses/{nombre}")
    @Transactional
    public ResponseEntity<InteresDTO> findByNombre(@PathVariable("nombre") String nombre){
        if(interesMgr.getInteresFromNombre(nombre) != null){
            Interes interes = interesMgr.getInteresFromNombre(nombre);
            InteresDTO interesDTO = interesMapper.toDTO(interes);

            return new ResponseEntity<InteresDTO>(interesDTO,HttpStatus.OK);
        }
        return null;
    }

    @GetMapping("intereses/{idinteres}")
    @Transactional
    public ResponseEntity<InteresDTO> findById(@PathVariable("idinteres") Integer id){
        if(interesMgr.getInteresFromId(id) != null){
            Interes interes = interesMgr.getInteresFromId(id);
            InteresDTO interesDTO = interesMapper.toDTO(interes);

            return new ResponseEntity<InteresDTO>(interesDTO, HttpStatus.OK);
        }

        return null;
    }

    @GetMapping("/intereses/getall")
    @Transactional
    public ResponseEntity<List<InteresDTO>> getAll(){
        if(interesMgr.getAll() != null){
            List<Interes> lista = interesMgr.getAll();
            List<InteresDTO> lista2 = new ArrayList<InteresDTO>();

            for(Interes i : lista){
                InteresDTO interesDTO = interesMapper.toDTO(i);
                lista2.add(interesDTO);
            }

            return new ResponseEntity<List<InteresDTO>>(lista2, HttpStatus.OK);
        }

        return null;
    }

}
