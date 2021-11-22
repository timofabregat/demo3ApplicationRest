package com.example.demo3.mappers;

import com.example.demo3.dtos.AdminDTO;
import com.example.demo3.entities.Admin;
import org.springframework.stereotype.Component;

@Component
public class AdminMapper {

    public Admin toAdmin(AdminDTO adminDTO){
        Admin admin = new Admin();

        admin.setId_admin(adminDTO.getId_admin());
        admin.setMail(adminDTO.getEmail());
        admin.setPassword(adminDTO.getPassword());

        return admin;
    }

    public AdminDTO toDTO(Admin admin){
        AdminDTO adminDTO= new AdminDTO();

        adminDTO.setId_admin(admin.getId_admin());
        adminDTO.setEmail(admin.getMail());
        adminDTO.setPassword(admin.getPassword());

        return adminDTO;
    }
}
