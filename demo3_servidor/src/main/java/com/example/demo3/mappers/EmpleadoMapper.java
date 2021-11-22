package com.example.demo3.mappers;

import com.example.demo3.dtos.EmpleadoDTO;
import com.example.demo3.entities.Empleado;
import org.springframework.stereotype.Component;

@Component
public class EmpleadoMapper {

    public Empleado toEmpleado(EmpleadoDTO empleadoDTO){
        Empleado empleado = new Empleado();

        empleado.setId(empleadoDTO.getId());
        empleado.setUsername(empleadoDTO.getUsername());
        empleado.setPassword(empleadoDTO.getPassword());
        empleado.setIdoperador(empleadoDTO.getIdoperador());

        return empleado;
    }

    public EmpleadoDTO toDTO(Empleado empleado){
        EmpleadoDTO empleadoDTO = new EmpleadoDTO();

        empleadoDTO.setId(empleado.getId());
        empleadoDTO.setUsername(empleado.getUsername());
        empleadoDTO.setPassword(empleado.getPassword());
        empleadoDTO.setIdoperador(empleado.getIdoperador());

        return empleadoDTO;
    }
}
