package com.example.demo3.managers;

import com.example.demo3.entities.Operador;
import com.example.demo3.exceptions.InformacionInvalida;
import com.example.demo3.exceptions.OperadorYaExiste;
import com.example.demo3.persistence.OperadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperadorMgr {

    @Autowired
    private OperadorRepository operadorRepository;

    public void addOperador(String nombreEmpresa, String departamento,
                            Long telefono, String emailContacto, String direccion) throws InformacionInvalida, OperadorYaExiste {

        if (nombreEmpresa.equals("") || departamento.equals("") ||
                telefono == 0 || emailContacto.equals("") || direccion.equals("")) {
            throw new InformacionInvalida();
        }

        List<Operador> lista = operadorRepository.findAllByempresa(nombreEmpresa);
        if (lista != null && lista.size() > 0) {
            throw new OperadorYaExiste();
        }

        Operador operador = new Operador(nombreEmpresa, departamento,
                telefono, emailContacto, direccion);

        operadorRepository.save(operador);
    }

    public void bloquearOperador(Operador operador) {
        operador.setBloqueado(true);
        operadorRepository.save(operador);
    }

    public void desbloquearOperador(Operador operador) {
        operador.setBloqueado(false);
        operadorRepository.save(operador);
    }

    public void updateOperador (Integer id_operador, String nombreEmpresa, String departamento, Long telefono,
                                String emailContacto, String direccion) {

    Operador operador = operadorRepository.findOperadorById(id_operador);

    if (nombreEmpresa != null) {
        if (!nombreEmpresa.equals("")) {
            operador.setEmpresa(nombreEmpresa);
        }
        }
    if (departamento != null) {
        operador.setDepartamento(departamento);
    }
    if (telefono != 0L) {
        operador.setTelefono(telefono);
    }
    if (emailContacto != null) {
        if (!emailContacto.equals("")) {
            operador.setEmailcontacto(emailContacto);
        }
    }
    if (direccion != null) {
        if (!direccion.equals("")) {
            operador.setDireccion(direccion);
        }
    }
    operadorRepository.save(operador);
    }

    public Operador getOperadorFromId(Integer id_operador) {
        return operadorRepository.findOperadorById(id_operador);
    }

    public List<Operador> getAll() {
        return (List<Operador>) operadorRepository.findAll();
    }

}