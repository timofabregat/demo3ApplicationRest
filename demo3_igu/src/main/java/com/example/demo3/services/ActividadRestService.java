package com.example.demo3.services;

import com.example.demo3.dtos.ActividadDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalTime;
import java.util.List;

@Component
public class ActividadRestService {

    @Autowired
    private RestTemplate restTemplate;

    /*@Autowired
    public ActividadRestService(RestTemplate restTemplate){this.restTemplate=restTemplate;}*/

    public ResponseEntity registrarActividad(ActividadDTO actividadDTO){
        return restTemplate.postForEntity("http://localhost:8080/actividades/add",actividadDTO,ActividadDTO.class);
    }

    public ActividadDTO findByTituloAndOperador(String titulo, Integer id){
        return restTemplate.getForEntity("http://localhost:8080/actividades/" + titulo + "/" + id,ActividadDTO.class,
                titulo,id).getBody();
    }

    public List<ActividadDTO> getActividadesNoValidadas(){
        return restTemplate.getForEntity("http://localhost:8080/actividades/novalidadas",
                (Class<List<ActividadDTO>>)(Object)List.class).getBody();
    }

    public List<ActividadDTO> findByOperador(Integer idoperador){
        return restTemplate.getForEntity("http://localhost:8080/actividades/" + idoperador,
                (Class<List<ActividadDTO>>)(Object)List.class,idoperador).getBody();
    }

    public List<ActividadDTO> getAll(){
        return restTemplate.getForEntity("https://localhost:8080/actividades/getall",
                (Class<List<ActividadDTO>>)(Object)List.class).getBody();
    }

    public List<ActividadDTO> findByTituloContaining(String titulo){
        return  restTemplate.getForEntity("http://localhost:8080/actividades/" + titulo,
                (Class<List<ActividadDTO>>)(Object)List.class,titulo).getBody();
    }

    public String getTituloFromId(Integer id){
        return restTemplate.getForEntity("http://localhost/actividades/titulo/" + id,String.class,id).getBody();
    }

    public List<Integer> findIdByOperador(Integer idoperador){
        return restTemplate.getForEntity("http://localhost:8080/actividades/getfromop/" + idoperador,
                (Class<List<Integer>>)(Object)List.class,idoperador).getBody();
    }

    public void validarActividad(ActividadDTO actividadDTO){
        restTemplate.put("http://localhost:8080/actividades/validar",actividadDTO,ActividadDTO.class);
    }

    public void rechazarActividad(ActividadDTO actividadDTO){
        restTemplate.put("http://localhost:8080/actividades/rechazar",actividadDTO,ActividadDTO.class);
    }

    public void updateActividad(Integer id,String titulo,String descripcion, LocalTime apertura,LocalTime cierre,
                                Integer cupo,Boolean utiliza_reserva, byte[] imagenactividad,Boolean requiere_vacuna){
        restTemplate.put("http://localhost:8080/actividades/update/"+id +"/"+titulo+"/"+descripcion+"/"+
                        apertura+"/"+cierre+"/"+cupo+"/"+utiliza_reserva+"/"+imagenactividad+"/"+requiere_vacuna,
                        id,titulo,descripcion,apertura,cierre,cupo,utiliza_reserva,imagenactividad,requiere_vacuna);
    }

    public void eliminarActividad(ActividadDTO actividadDTO){
        restTemplate.delete("http://localhost:8080/actividades/eliminar",actividadDTO,ActividadDTO.class);
    }
}
