package com.example.demo3.services;

import com.example.demo3.dtos.ReservaDTO;
import com.example.demo3.entities.Reserva;
import com.example.demo3.managers.ReservaMgr;
import com.example.demo3.mappers.ReservaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController("/reservas")
public class ReservaRestService {

    private ReservaMgr reservaMgr;

    private ReservaMapper reservaMapper;

    @Autowired
    public ReservaRestService(ReservaMgr reservaMgr, ReservaMapper reservaMapper){
        this.reservaMgr = reservaMgr;
        this.reservaMapper = reservaMapper;
    }

    @PostMapping("/reservas/add")
    @Transactional
    public void addReserva(@RequestBody ReservaDTO reservaDTO){
        Reserva reserva = reservaMapper.toReserva(reservaDTO);

        reservaMgr.crearReserva(reserva.getIdcliente(),reserva.getIdactividad(),reserva.getFecha(),
                                reserva.getHora(),reserva.getCantidad());
    }

    @GetMapping("/reservas/{idactividad}/{fecha}")
    @Transactional
    public ResponseEntity<List<ReservaDTO>> findBYActivityInDate(@PathVariable("idactividad")Integer idactividad,
                                                           @PathVariable("fecha")LocalDate fecha){
        if(reservaMgr.getFromActivityInDate(idactividad,fecha) != null){
            List<Reserva> lista = reservaMgr.getFromActivityInDate(idactividad, fecha);
            List<ReservaDTO> lista2 = new ArrayList<ReservaDTO>();

            for(Reserva r: lista){
                ReservaDTO reservaDTO = reservaMapper.toDTO(r);
                lista2.add(reservaDTO);
            }

            return new ResponseEntity<List<ReservaDTO>>(lista2, HttpStatus.OK);
        }

        return null;
    }

    @GetMapping("/reservas/{idactividad}")
    @Transactional
    public ResponseEntity<List<ReservaDTO>> findByActividad(@PathVariable("idactividad") Integer idactividad){
        if(reservaMgr.getFromActividad(idactividad) != null){
            List<Reserva> lista = reservaMgr.getFromActividad(idactividad);
            List<ReservaDTO> lista2 = new ArrayList<ReservaDTO>();

            for(Reserva r: lista){
                ReservaDTO reservaDTO = reservaMapper.toDTO(r);
                lista2.add(reservaDTO);
            }

            return new ResponseEntity<List<ReservaDTO>>(lista2, HttpStatus.OK);
        }

        return null;
    }

    @GetMapping("/reservas/{idcliente}")
    @Transactional
    public ResponseEntity<List<ReservaDTO>> findByCliente(@PathVariable("idcliente") Integer idcliente){
        if(reservaMgr.getReservasFromCliente(idcliente) != null){
            List<Reserva> lista = reservaMgr.getReservasFromCliente(idcliente);
            List<ReservaDTO> lista2 = new ArrayList<ReservaDTO>();

            for(Reserva r: lista){
                ReservaDTO reservaDTO = reservaMapper.toDTO(r);
                lista2.add(reservaDTO);
            }

            return new ResponseEntity<List<ReservaDTO>>(lista2, HttpStatus.OK);
        }
        return null;
    }

    @PutMapping("reserva/setvalidada/{validada}")
    @Transactional
    public void updateValidada(@RequestBody ReservaDTO reservaDTO, @PathVariable("validad") Boolean validada){
        Reserva res = reservaMapper.toReserva(reservaDTO);

        reservaMgr.setValidada(res,validada);
    }
}
