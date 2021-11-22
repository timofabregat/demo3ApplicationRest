package com.example.demo3.services;

import com.example.demo3.dtos.EmpleadoDTO;
import com.example.demo3.dtos.OperadorDTO;
import com.example.demo3.entities.Empleado;
import com.example.demo3.entities.Operador;
import com.example.demo3.exceptions.NombreDeUsuarioYaExiste;
import com.example.demo3.managers.EmpleadoMgr;
import com.example.demo3.mappers.EmpleadoMapper;
import com.example.demo3.mappers.OperadorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController("/empleado")
public class EmpleadoRestService {

    private EmpleadoMgr empleadoMgr;

    private EmpleadoMapper empleadoMapper;

    private OperadorMapper operadorMapper;

    @Autowired
    public void EmpleadoRestService(EmpleadoMgr empleadoMgr,EmpleadoMapper empleadoMapper, OperadorMapper operadorMapper){
        this.empleadoMgr = empleadoMgr;
        this.empleadoMapper = empleadoMapper;
        this.operadorMapper = operadorMapper;
    }

    @PostMapping("/empleado/add")
    @Transactional
    public void addEmpleado(@RequestBody EmpleadoDTO empleadoDTO) throws NombreDeUsuarioYaExiste {

        Empleado empleado = empleadoMapper.toEmpleado(empleadoDTO);

        empleadoMgr.addEmpleado(empleado.getUsername(), empleado.getPassword(), empleado.getIdoperador());
    }

    @GetMapping("/empleado/{id}")
    @Transactional
    public ResponseEntity<OperadorDTO> findOperadorFromEmpleado(@PathVariable("id") Integer id){
        if(empleadoMgr.getOperadorFromEmpleado(id) != null){
            Operador op = empleadoMgr.getOperadorFromEmpleado(id);
            OperadorDTO opDTO = operadorMapper.toDTO(op);

            return new ResponseEntity<OperadorDTO>(opDTO, HttpStatus.OK);
        }
        return null;
    }

    @GetMapping("/empleado/{username}/{password}")
    @Transactional
    public ResponseEntity<EmpleadoDTO> findByEmpleadoAndEmail(@PathVariable("username") String username,
                                                              @PathVariable("password") String password){
        if(empleadoMgr.getEmpleadoFromMailAndPassword(username, password)!= null){
            Empleado emp = empleadoMgr.getEmpleadoFromMailAndPassword(username, password);
            EmpleadoDTO empDTO = empleadoMapper.toDTO(emp);

            return new ResponseEntity<EmpleadoDTO>(empDTO, HttpStatus.OK);
        }

        return null;
    }


}
