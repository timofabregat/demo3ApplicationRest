package com.example.demo3.services;

import com.example.demo3.dtos.OperadorDTO;
import com.example.demo3.entities.Operador;
import com.example.demo3.exceptions.InformacionInvalida;
import com.example.demo3.exceptions.OperadorYaExiste;
import com.example.demo3.managers.OperadorMgr;
import com.example.demo3.mappers.OperadorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RestController("/operadores")
public class OperadorRestService {

    private OperadorMapper operadorMapper;

    private OperadorMgr operadorMgr;

    @Autowired
    public void OperadorRestService(OperadorMapper operadorMapper, OperadorMgr operadorMgr){
        this.operadorMapper = operadorMapper;
        this.operadorMgr = operadorMgr;
    }

    @PostMapping("/operadores/add")
    @Transactional
    public void addOperador(@RequestBody OperadorDTO operadorDTO) throws InformacionInvalida, OperadorYaExiste {
        Operador op = operadorMapper.toOperador(operadorDTO);

        operadorMgr.addOperador(op.getEmpresa(),op.getDepartamento(),op.getTelefono(),
                                op.getEmailcontacto(),op.getDireccion());
    }

    @GetMapping("/operadores/{id}")
    @Transactional
    public ResponseEntity<OperadorDTO> findById(@PathVariable("id") Integer id){
        if(operadorMgr.getOperadorFromId(id) != null){
            Operador operador = operadorMgr.getOperadorFromId(id);
            OperadorDTO opDTO = operadorMapper.toDTO(operador);

            return new ResponseEntity<OperadorDTO>(opDTO, HttpStatus.OK);
        }

        return null;
    }

    @GetMapping("/operadores/getall")
    @Transactional
    public ResponseEntity<List<OperadorDTO>> getAll(){
        if(operadorMgr.getAll() != null){
            List<Operador> lista = operadorMgr.getAll();
            List<OperadorDTO> lista2 = new ArrayList<OperadorDTO>();

            for(Operador o: lista){
                OperadorDTO operadorDTO = operadorMapper.toDTO(o);
                lista2.add(operadorDTO);
            }

            return new ResponseEntity<List<OperadorDTO>>(lista2, HttpStatus.OK);
        }

        return null;
    }

    @PutMapping("/operadores/bloquear")
    @Transactional
    public void bloquearOperador(@RequestBody OperadorDTO operadorDTO){
        Operador op = operadorMapper.toOperador(operadorDTO);

        operadorMgr.bloquearOperador(op);
    }

    @PutMapping("/operadores/desbloquear")
    @Transactional
    public void desbloquearOperador(@RequestBody OperadorDTO operadorDTO){
        Operador op = operadorMapper.toOperador(operadorDTO);

        operadorMgr.desbloquearOperador(op);
    }

    @PutMapping("/operadores/update/{id}/{empresa}/{departamento}/{telefono}/{emailcontacto}/{direccion}")
    @Transactional
    public void updateOperador(@PathVariable("id") Integer id,@PathVariable("empresa")String empresa,
                               @PathVariable("departamento")String departamento,@PathVariable("telefono")Long telefono,
                               @PathVariable("emailcontacto")String emailcontacto,@PathVariable("direccion")String direccion){

        operadorMgr.updateOperador(id, empresa, departamento, telefono, emailcontacto, direccion);
    }

}
