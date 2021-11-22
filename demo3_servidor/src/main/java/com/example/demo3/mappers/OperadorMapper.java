package com.example.demo3.mappers;

import com.example.demo3.dtos.OperadorDTO;
import com.example.demo3.entities.Operador;
import org.springframework.stereotype.Component;

@Component
public class OperadorMapper {

    public Operador toOperador(OperadorDTO operadorDTO){
        Operador operador = new Operador();

        operador.setId(operadorDTO.getId());
        operador.setEmpresa(operadorDTO.getEmpresa());
        operador.setDepartamento(operadorDTO.getDepartamento());
        operador.setTelefono(operadorDTO.getTelefono());
        operador.setEmailcontacto(operadorDTO.getEmailcontacto());
        operador.setDireccion(operadorDTO.getDireccion());
        operador.setBloqueado(operadorDTO.getBloqueado());

        return operador;
    }

    public OperadorDTO toDTO(Operador operador){
        OperadorDTO operadorDTO = new OperadorDTO();

        operadorDTO.setId(operador.getId());
        operadorDTO.setEmpresa(operador.getEmpresa());
        operadorDTO.setDepartamento(operador.getDepartamento());
        operadorDTO.setTelefono(operador.getTelefono());
        operadorDTO.setEmailcontacto(operador.getEmailcontacto());
        operadorDTO.setDireccion(operador.getDireccion());
        operadorDTO.setBloqueado(operador.getBloqueado());

        return operadorDTO;
    }
}
