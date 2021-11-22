package com.example.demo3.mappers;

import com.example.demo3.dtos.InteresDTO;
import com.example.demo3.entities.Interes;
import org.springframework.stereotype.Component;

@Component
public class InteresMapper {

    public Interes toInteres(InteresDTO interesDTO){
        Interes interes = new Interes();

        interes.setIdinteres(interesDTO.getIdinteres());
        interes.setNombre(interesDTO.getNombre());

        return interes;
    }

    public InteresDTO toDTO(Interes interes){
        InteresDTO interesDTO = new InteresDTO();

        interesDTO.setIdinteres(interes.getIdinteres());
        interesDTO.setNombre(interes.getNombre());

        return interesDTO;
    }
}
