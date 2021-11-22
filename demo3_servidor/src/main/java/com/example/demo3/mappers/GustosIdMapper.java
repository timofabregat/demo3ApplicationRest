package com.example.demo3.mappers;

import com.example.demo3.dtos.GustosIdDTO;
import com.example.demo3.entities.GustosId;
import org.springframework.stereotype.Component;

@Component
public class GustosIdMapper {

    public GustosId toGustosId(GustosIdDTO gustosIdDTO){
        GustosId gustosId = new GustosId();
        gustosId.setIdgustos(gustosIdDTO.getIdgustos());
        gustosId.setUsuario(gustosIdDTO.getUsuario());

        return gustosId;
    }

    public GustosIdDTO toDTO(GustosId gustosId){
        GustosIdDTO gustosIdDTO = new GustosIdDTO();

        gustosIdDTO.setIdgustos(gustosId.getIdgustos());
        gustosIdDTO.setUsuario(gustosId.getUsuario());

        return gustosIdDTO;
    }
}
