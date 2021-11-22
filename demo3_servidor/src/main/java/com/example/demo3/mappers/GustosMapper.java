package com.example.demo3.mappers;

import com.example.demo3.dtos.GustosDTO;
import com.example.demo3.dtos.GustosIdDTO;
import com.example.demo3.entities.Gustos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GustosMapper {

    private GustosIdMapper gustosIdMapper;

    @Autowired
    public void GustosMapper(GustosIdMapper gustosIdMapper){
        this.gustosIdMapper = gustosIdMapper;
    }

    public Gustos toGustos(GustosDTO gustosDTO){
        Gustos gustos = new Gustos();

        gustos.setId(gustosIdMapper.toGustosId(gustosDTO.getId()));

        return gustos;
    }

    public GustosDTO toDTO(Gustos gustos){
        GustosDTO gustosDTO = new GustosDTO();

        gustosDTO.setId(gustosIdMapper.toDTO(gustos.getId()));

        return gustosDTO;
    }
}
