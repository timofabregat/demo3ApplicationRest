package com.example.demo3.services;

import com.example.demo3.dtos.ActividadDTO;
import com.example.demo3.entities.Actividad;
import com.example.demo3.managers.ActividadMgr;
import com.example.demo3.mappers.ActividadMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@RestController("/actividades")
public class ActividadRestService {

    private ActividadMgr actividadMgr;

    private ActividadMapper actividadMapper;

    @Autowired
    public void ActividadRestService(ActividadMgr actividadMgr, ActividadMapper actividadMapper){
        this.actividadMgr = actividadMgr;
        this.actividadMapper = actividadMapper;
    }

    @PostMapping("/actividades/add")
    @Transactional
    public void registrarActividad(@RequestBody ActividadDTO actividadDTO){
        Actividad act = actividadMapper.toActividad(actividadDTO);

        actividadMgr.registrarActividad(act.getTitulo(),act.getImagenactividad(),act.getIdoperador(),act.getDescripcion(),
                                        act.getApertura(),act.getCierre(),act.getValidada(),act.getCupo(),act.getUtiliza_reservas(),
                                        act.getRequiere_vacuna());
    }

    @GetMapping("/actividades/{titulo}/{id}")
    @Transactional
    public ResponseEntity<ActividadDTO> findByTituloAndOperador(@PathVariable("titulo") String titulo,
                                                                     @PathVariable("id") Integer id){
        if(actividadMgr.getActividadFromTituloAndIdoperador(titulo, id)!=null){
            Actividad act = actividadMgr.getActividadFromTituloAndIdoperador(titulo, id);
            ActividadDTO actividadDTO = actividadMapper.toDTO(act);

            return new ResponseEntity<ActividadDTO>(actividadDTO, HttpStatus.OK);
        }
        return null;
    }

    @GetMapping("/actividades/novalidadas}")
    @Transactional
    public ResponseEntity<List<ActividadDTO>> getActividadesNoValidadas(){
        if(actividadMgr.getActividadesNoValidadas() != null){
            List<Actividad> lista = actividadMgr.getActividadesNoValidadas();
            List<ActividadDTO> lista2 = new ArrayList<ActividadDTO>();
             for(Actividad a: lista){
                 ActividadDTO actividadDTO = actividadMapper.toDTO(a);
                 lista2.add(actividadDTO);
             }

             return new ResponseEntity<List<ActividadDTO>>(lista2 , HttpStatus.OK);
        }
        return null;
    }

    @GetMapping("/actividades/{idoperador}")
    @Transactional
    public ResponseEntity<List<ActividadDTO>> findByOperador(@PathVariable("idoperador") Integer idoperador){
        if(actividadMgr.getActividadesFromOperador(idoperador) != null){
            List<Actividad> lista = actividadMgr.getActividadesFromOperador(idoperador);
            List<ActividadDTO> lista2 = new ArrayList<ActividadDTO>();
            for(Actividad a: lista){
                ActividadDTO actividadDTO = actividadMapper.toDTO(a);
                lista2.add(actividadDTO);
            }

            return new ResponseEntity<List<ActividadDTO>>(lista2 , HttpStatus.OK);
        }
        return null;
    }

    @GetMapping("/actividades/getall")
    @Transactional
    public ResponseEntity<List<ActividadDTO>> getAll(){
        if(actividadMgr.getAll() != null){
            List<Actividad> lista = actividadMgr.getAll();
            List<ActividadDTO> lista2 = new ArrayList<ActividadDTO>();
            for(Actividad a: lista){
                ActividadDTO actividadDTO = actividadMapper.toDTO(a);
                lista2.add(actividadDTO);
            }

            return new ResponseEntity<List<ActividadDTO>>(lista2 , HttpStatus.OK);
        }
        return null;
    }

    @GetMapping("/actividades/{titulo}")
    @Transactional
    public ResponseEntity<List<ActividadDTO>> findByTituloContaining(@PathVariable("titulo") String titulo){
        if(actividadMgr.getActividadesFromTituloContaining(titulo) != null){
            List<Actividad> lista = actividadMgr.getActividadesFromTituloContaining(titulo);
            List<ActividadDTO> lista2 = new ArrayList<ActividadDTO>();
            for(Actividad a: lista){
                ActividadDTO actividadDTO = actividadMapper.toDTO(a);
                lista2.add(actividadDTO);
            }

            return new ResponseEntity<List<ActividadDTO>>(lista2 , HttpStatus.OK);
        }
        return null;
    }

    @GetMapping("/actividades/titulo/{id}")
    @Transactional
    public ResponseEntity<String> getTituloFromId(@PathVariable("id") Integer id){
        String tit = actividadMgr.getTituloFromId(id);
        return new ResponseEntity<String>(tit, HttpStatus.OK);
    }

    @GetMapping("actividades/getfromop/{idoperador}")
    @Transactional
    public ResponseEntity<List<Integer>> findIdByOperador(@PathVariable("id") Integer idoperador){
        if(actividadMgr.getIdActividadesFromOperador(idoperador) != null){
            List<Integer> lista = actividadMgr.getIdActividadesFromOperador(idoperador);

            return new ResponseEntity<List<Integer>>(lista, HttpStatus.OK);
        }

        return null;
    }

    @PutMapping("/actividades/validar")
    @Transactional
    public void validarActividad(@RequestBody ActividadDTO actividadDTO){
        Actividad act = actividadMapper.toActividad(actividadDTO);

        actividadMgr.validarActividad(act);
    }

    @PutMapping("/actividades/rechazar")
    @Transactional
    public void rechazarActividad(@RequestBody ActividadDTO actividadDTO){
        Actividad act = actividadMapper.toActividad(actividadDTO);

        actividadMgr.rechazarActividad(act);
    }

    @PutMapping("/actividades/update/{id}/{titulo}/{descripcion}/{apertura}/{cierre}/{cupo}/{utiliza_reservas}/" +
                "{imagenactividad}/{requiere_vacuna}")
    @Transactional
    public void updateActividad(@PathVariable("id")Integer id, @PathVariable("titulo")String titulo,
                                @PathVariable("descripcion")String descripcion, @PathVariable("apertura")LocalTime apertura,
                                @PathVariable("cierre")LocalTime cierre,@PathVariable("cupo")Integer cupo,
                                @PathVariable("utiliza_reserva")Boolean utiliza_reserva,@PathVariable("imagenactividad")byte[] imagenactividad,
                                @PathVariable("requiere_vacuna")Boolean requiere_vacuna){
        actividadMgr.updateActividad(id,titulo,descripcion,apertura,cierre,cupo,utiliza_reserva,imagenactividad,requiere_vacuna);
    }

    @DeleteMapping("/actividad/eliminar")
    @Transactional
    public void eliminarActividad(@RequestBody ActividadDTO actividadDTO){
        Actividad act = actividadMapper.toActividad(actividadDTO);

        actividadMgr.eliminarActividad(act);
    }
}

