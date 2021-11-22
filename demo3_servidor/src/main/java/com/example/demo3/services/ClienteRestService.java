package com.example.demo3.services;

import com.example.demo3.dtos.ClienteDTO;
import com.example.demo3.entities.Cliente;
import com.example.demo3.exceptions.DocumentoYaExisteParaMismoPais;
import com.example.demo3.exceptions.InformacionInvalida;
import com.example.demo3.exceptions.NombreDeUsuarioYaExiste;
import com.example.demo3.managers.ClienteMgr;
import com.example.demo3.mappers.ClienteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteRestService {

    private ClienteMapper clienteMapper;

    private ClienteMgr clienteMgr;

    @Autowired
    public void ClienteRestService(ClienteMapper clienteMapper, ClienteMgr clienteMgr){
        this.clienteMapper = clienteMapper;
        this.clienteMgr = clienteMgr;
    }

    @PostMapping
    public void addCliente(@RequestBody ClienteDTO clienteDTO) throws InformacionInvalida, NombreDeUsuarioYaExiste, DocumentoYaExisteParaMismoPais {
        Cliente cliente = clienteMapper.toCliente(clienteDTO);

        clienteMgr.addClient(cliente.getMail(),cliente.getContrasena(),cliente.getDocumento(),
                cliente.getTipo_documento(),cliente.getFecha_nacimiento(),
                cliente.getVacuna_covid(),cliente.getPais(),cliente.getImagencliente());
    }

    @GetMapping("clientes/{email}/{contrasena}")
    @Transactional
    public ResponseEntity<ClienteDTO> findByMailandPassword(@PathVariable("mail") String mail, @PathVariable("contrasena") String contrasena){
        if(clienteMgr.getClienteFromMailAndPassword(mail,contrasena) != null){
            Cliente cliente = clienteMgr.getClienteFromMailAndPassword(mail,contrasena);
            ClienteDTO cliente2 = clienteMapper.toDTO(cliente);
            return new ResponseEntity<ClienteDTO>(cliente2, HttpStatus.OK);
        }
        return null;
    }

    @GetMapping("/cliente/getfrommail/{mail}")
    @Transactional
    public ResponseEntity<List<ClienteDTO>> findByContainingMail(@PathVariable("mail") String mail){
        if(clienteMgr.getClientesFromMailContaining(mail) != null){
            List<Cliente> lista = clienteMgr.getClientesFromMailContaining(mail);
            List<ClienteDTO> lista2 = new ArrayList<ClienteDTO>();

            for(Cliente c: lista){
                ClienteDTO clienteDTO = clienteMapper.toDTO(c);
                lista2.add(clienteDTO);
            }

            return new ResponseEntity<List<ClienteDTO>>(lista2, HttpStatus.OK);
        }
        return null;
    }

    @GetMapping("/clientes/getall")
    @Transactional
    public ResponseEntity<List<ClienteDTO>> getAll(){
        if(clienteMgr.getAll() != null){
            List<Cliente> lista = clienteMgr.getAll();
            List<ClienteDTO> lista2 = new ArrayList<ClienteDTO>();

            for(Cliente c: lista){
                ClienteDTO clienteDTO = clienteMapper.toDTO(c);
                lista2.add(clienteDTO);
            }

            return new ResponseEntity<List<ClienteDTO>>(lista2, HttpStatus.OK);
        }
        return null;
    }

    @GetMapping("/clientes/username/{id}")
    @Transactional
    public ResponseEntity<String> findUsernameById(@PathVariable("id") Integer id){
        String username = clienteMgr.getUsernameFromId(id);

        return new ResponseEntity<String>(username, HttpStatus.OK);
    }

    @PutMapping("/clientes/desbloquear")
    @Transactional
    public void desbloquearCliente(@RequestBody ClienteDTO clienteDTO){
        Cliente cliente = clienteMapper.toCliente(clienteDTO);

        clienteMgr.desbloquearCliente(cliente);
    }

    @PutMapping("/clientes/bloquear")
    @Transactional
    public void bloquearCliente(@RequestBody ClienteDTO clienteDTO){
        Cliente cliente = clienteMapper.toCliente(clienteDTO);

        clienteMgr.bloquearCliente(cliente);
    }

    @PutMapping("/clientes/update/{id}/{contrasena}/{vacuna}")
    @Transactional
    public void updateCliente(@PathVariable("id") Integer id, @PathVariable("contrasena")String contrasena,
                              @PathVariable("vacuna") Boolean vacuna){
        clienteMgr.updateCliente(id,contrasena,vacuna);
    }

    @PutMapping("/clientes/updateimagen/{id}/{imagencliente}")
    @Transactional
    public void updateImagen(@PathVariable("id") Integer id, @PathVariable("imagencliente") byte[] imagencliente){

        clienteMgr.updateImagen(id,imagencliente);
    }

}
