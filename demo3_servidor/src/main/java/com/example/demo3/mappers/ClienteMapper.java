package com.example.demo3.mappers;

import com.example.demo3.dtos.ClienteDTO;
import com.example.demo3.entities.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    public Cliente toCliente(ClienteDTO clienteDTO){
        Cliente cliente = new Cliente();
        cliente.setId(clienteDTO.getId());
        cliente.setMail(clienteDTO.getMail());
        cliente.setContrasena(clienteDTO.getContrasena());
        cliente.setDocumento(clienteDTO.getDocumento());
        cliente.setTipo_documento(clienteDTO.getTipo_documento());
        cliente.setFecha_nacimiento(clienteDTO.getFecha_nacimiento());
        cliente.setVacuna_covid(clienteDTO.getVacuna_covid());
        cliente.setPais(clienteDTO.getPais());
        cliente.setBloqueado(clienteDTO.getBloqueado());
        cliente.setImagencliente(clienteDTO.getImagencliente());

        return cliente;
    }

    public ClienteDTO toDTO(Cliente cliente){
        ClienteDTO clienteDTO = new ClienteDTO();

        clienteDTO.setId(cliente.getId());
        clienteDTO.setMail(cliente.getMail());
        clienteDTO.setContrasena(cliente.getContrasena());
        clienteDTO.setDocumento(cliente.getDocumento());
        clienteDTO.setTipo_documento(cliente.getTipo_documento());
        clienteDTO.setFecha_nacimiento(cliente.getFecha_nacimiento());
        clienteDTO.setVacuna_covid(cliente.getVacuna_covid());
        clienteDTO.setPais(cliente.getPais());
        clienteDTO.setBloqueado(cliente.getBloqueado());
        clienteDTO.setImagencliente(cliente.getImagencliente());

        return clienteDTO;
    }
}
