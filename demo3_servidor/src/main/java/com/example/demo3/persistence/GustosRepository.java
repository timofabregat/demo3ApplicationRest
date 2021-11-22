package com.example.demo3.persistence;

import com.example.demo3.entities.Gustos;
import com.example.demo3.entities.GustosId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GustosRepository extends JpaRepository<Gustos, GustosId> {

    List<Gustos> findAllByIdIdgustos(Integer idgustos);

    List<Gustos> findAllByIdUsuario(String usuario);

    List<Gustos> findAllByIdIdgustosAndAndIdUsuario(Integer idgustos, String usuario);

    List<Gustos> findAllById(Integer id);


    @Override
    List<Gustos> findAllById(Iterable<GustosId> gustosIds);

}