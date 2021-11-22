package com.example.demo3.managers;

import com.example.demo3.entities.Gustos;
import com.example.demo3.exceptions.GustoYaExiste;
import com.example.demo3.exceptions.InformacionInvalida;
import com.example.demo3.persistence.GustosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GustosMgr {

    @Autowired
    private GustosRepository gustosRepository;

    public void addGusto(String usuario, Integer idgusto) throws GustoYaExiste {

        List<Gustos> gustos_check = gustosRepository.findAllByIdIdgustosAndAndIdUsuario(idgusto,usuario);

        if(gustos_check != null && gustos_check.size() > 0){
            throw new GustoYaExiste("");
        }

        Gustos gusto = new Gustos(usuario,idgusto);
        gustosRepository.save(gusto);
    }

    public void deleteGusto(String usuario, Integer idgusto) throws InformacionInvalida{

        List<Gustos> gustos_check = gustosRepository.findAllByIdIdgustosAndAndIdUsuario(idgusto,usuario);

        if(gustos_check == null && gustos_check.size() == 0){
            throw new InformacionInvalida();
        }

        gustosRepository.delete(gustos_check.get(0));
    }

    public List<Gustos> getAll() {
        return (List<Gustos>) gustosRepository.findAll();
    }

}