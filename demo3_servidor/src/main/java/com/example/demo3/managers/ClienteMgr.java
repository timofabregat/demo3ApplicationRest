package com.example.demo3.managers;

import com.example.demo3.entities.Admin;
import com.example.demo3.entities.Cliente;
import com.example.demo3.entities.Empleado;
import com.example.demo3.exceptions.DocumentoYaExisteParaMismoPais;
import com.example.demo3.exceptions.InformacionInvalida;
import com.example.demo3.exceptions.NombreDeUsuarioYaExiste;
import com.example.demo3.persistence.AdminRepository;
import com.example.demo3.persistence.ClienteRepository;
import com.example.demo3.persistence.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ClienteMgr {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private AdminRepository adminRepository;

    public void addClient(String mail, String contrasena, Long documento, String tipo_documento,
                          LocalDate fecha_nacimiento, Boolean vacuna_covid, String pais,byte[] imagencliente) throws NombreDeUsuarioYaExiste, InformacionInvalida, DocumentoYaExisteParaMismoPais {
        //if (document == 0 || name.equals("") || email.equals("")) {
        //    throw new InformacionInvalida();
        //}

        List<Cliente> client_username_check = clienteRepository.findAllByMail(mail);
        if (client_username_check != null && client_username_check.size() > 0) {
            throw new NombreDeUsuarioYaExiste();
        }// chequeo que ese nombre de usuario no este asociado a ningun cliente

        List<Empleado> emp_username_check = empleadoRepository.findAllByUsername(mail);
        if (emp_username_check != null && emp_username_check.size() > 0) {
            throw new NombreDeUsuarioYaExiste();
        }// chequeo que ese nombre de usuario no este asociado a ningun empleado

        List<Admin> admin_username_check = adminRepository.findAllByMail(mail);
        if (admin_username_check != null && admin_username_check.size() > 0) {
            throw new NombreDeUsuarioYaExiste();
        }// chequeo que ese nombre de usuario no este asociado a ningun admin

        List<Cliente> doc_check = clienteRepository.findAllByDocumentoAndAndPais(documento, pais);
        if (doc_check != null && doc_check.size() > 0) {
            throw new DocumentoYaExisteParaMismoPais();
        }

        Cliente cliente = new Cliente(mail, contrasena, documento, tipo_documento, fecha_nacimiento,
                vacuna_covid, pais, imagencliente);
        clienteRepository.save(cliente);
    }

    public void bloquearCliente(Cliente cliente) {
        cliente.setBloqueado(true);
        clienteRepository.save(cliente);
    }

    public void desbloquearCliente(Cliente cliente) {
        cliente.setBloqueado(false);
        clienteRepository.save(cliente);
    }

    public void updateCliente(Integer id_cliente, String contrasena, Boolean vacuna){
        Cliente cliente = clienteRepository.findClienteById(id_cliente);
        if(contrasena != null){
            if (!contrasena.equals("") && !contrasena.equals(cliente.getContrasena())){
                cliente.setContrasena(contrasena);
            }
        }
        cliente.setVacuna_covid(vacuna);
        clienteRepository.save(cliente);
    }

    public void updateImagen(Integer id_cliente, byte[] imagen){
        if(imagen != null){
            Cliente cliente = clienteRepository.findClienteById(id_cliente);
            cliente.setImagencliente(imagen);
            clienteRepository.save(cliente);
        }

    }

    public Cliente getClienteFromMailAndPassword(String mail, String contrasena) {
        return clienteRepository.findByMailAndAndContrasena(mail,contrasena);
    }

    public List<Cliente> getClientesFromMailContaining(String mail) {
        return (List<Cliente>) clienteRepository.findAllByMailContaining(mail);
    }

    public List<Cliente> getAll() {
        return (List<Cliente>) clienteRepository.findAll();
    }

    public String getUsernameFromId(Integer id_cliente) {
        return clienteRepository.findClienteById(id_cliente).getMail();
    }

}