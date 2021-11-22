package com.example.demo3.managers;

import com.example.demo3.entities.Admin;
import com.example.demo3.entities.Cliente;
import com.example.demo3.entities.Empleado;
import com.example.demo3.exceptions.AdminYaExiste;
import com.example.demo3.exceptions.InformacionInvalida;
import com.example.demo3.exceptions.NombreDeUsuarioYaExiste;
import com.example.demo3.exceptions.OperadorYaExiste;
import com.example.demo3.persistence.AdminRepository;
import com.example.demo3.persistence.ClienteRepository;
import com.example.demo3.persistence.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminMgr {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public void addAdmin(String username, String password) throws InformacionInvalida, AdminYaExiste, OperadorYaExiste, NombreDeUsuarioYaExiste {

        if (username.equals("") || password.equals("")) {
            throw new InformacionInvalida();
        }

        List<Admin> lista1 = adminRepository.findAllByMail(username);
        if (lista1 != null && lista1.size() > 0) {
            throw new AdminYaExiste();
        }

        List<Empleado> lista2 = empleadoRepository.findAllByUsername(username);
        if (lista2 != null && lista2.size() > 0) {
            throw new OperadorYaExiste();
        }

        List<Cliente> lista3 = clienteRepository.findAllByMail(username);
        if (lista3 != null && lista3.size() > 0) {
            throw new NombreDeUsuarioYaExiste();
        }

        Admin admin = new Admin(username, password);
        adminRepository.save(admin);
    }

    public Admin getAdminFromMailAndPassword(String mail, String contrasena) {
        return adminRepository.findByMailAndPassword(mail,contrasena);
    }

}