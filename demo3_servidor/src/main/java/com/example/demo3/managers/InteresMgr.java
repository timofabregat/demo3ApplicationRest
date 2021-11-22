package com.example.demo3.managers;

import com.example.demo3.entities.Interes;
import com.example.demo3.exceptions.InteresYaExiste;
import com.example.demo3.persistence.InteresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InteresMgr {

    @Autowired
    private InteresRepository interesRepository;

    public void addInteres(String nombre) throws InteresYaExiste {
        Interes interes_nombre_check = interesRepository.findByNombre(nombre);
            if(interes_nombre_check != null ){
                throw new InteresYaExiste();
            }
            else {
                Interes interes = new Interes(nombre);
                interesRepository.save(interes);
            }
    }

    public Interes getInteresFromNombre(String nombre) {
        return interesRepository.findByNombre(nombre);
    }

    public Interes getInteresFromId(Integer id) {
        return interesRepository.findByIdinteres(id);
    }

    public List<Interes> getAll() {
        return (List<Interes>) interesRepository.findAll();
    }

}