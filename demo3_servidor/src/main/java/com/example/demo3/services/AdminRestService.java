package com.example.demo3.services;

import com.example.demo3.dtos.AdminDTO;
import com.example.demo3.entities.Admin;
import com.example.demo3.exceptions.AdminYaExiste;
import com.example.demo3.exceptions.InformacionInvalida;
import com.example.demo3.exceptions.NombreDeUsuarioYaExiste;
import com.example.demo3.exceptions.OperadorYaExiste;
import com.example.demo3.managers.AdminMgr;
import com.example.demo3.mappers.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController("/admin")
public class AdminRestService {

    private AdminMgr adminMgr;

    private AdminMapper adminMapper;

    @Autowired
    public void AdminRestService(AdminMgr adminMgr, AdminMapper adminMapper){
        this.adminMgr = adminMgr;
        this.adminMapper = adminMapper;
    }

    @PostMapping("/admin/add")
    @Transactional
    public void addAdmin(@RequestBody AdminDTO adminDTO) throws InformacionInvalida, AdminYaExiste, OperadorYaExiste, NombreDeUsuarioYaExiste {
        Admin admin = adminMapper.toAdmin(adminDTO);

        adminMgr.addAdmin(admin.getMail(),admin.getPassword());
    }

    @GetMapping("/admin/{mail}/{password}")
    @Transactional
    public ResponseEntity<AdminDTO> findByMailandPassword(@PathVariable("mail") String mail, @PathVariable("password") String password){
        if(adminMgr.getAdminFromMailAndPassword(mail, password) != null){
            Admin admin = adminMgr.getAdminFromMailAndPassword(mail, password);
            AdminDTO adminDTO = adminMapper.toDTO(admin);

            return new ResponseEntity<AdminDTO>(adminDTO, HttpStatus.OK);
        }
        return null;
    }
}
