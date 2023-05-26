package com.natalia.gestionnotas.utils;

import com.natalia.gestionnotas.security.entity.Rol;
import com.natalia.gestionnotas.security.enums.RolNombre;
import com.natalia.gestionnotas.security.service.RolServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @Project gestionnotas
 * @Author Sergio Abelardo Rodríguez Vásquez
 * @Email ingsergiorodriguezv@gmail.com
 * @Date 09/05/2023 - 14:48
 **/

@Component
public class CrearRoles implements CommandLineRunner {

    @Autowired
    private RolServiceImpl rolService;

    @Override
    public void run(String... args) throws Exception {

        Rol admin;
        Rol profesor;
        Rol estudiante;

        if (!rolService.getByRolNombre(RolNombre.ROLE_ADMIN).isPresent()) {
            admin = new Rol(RolNombre.ROLE_ADMIN);
            rolService.crearRol(admin);
        }

        if (!rolService.getByRolNombre(RolNombre.ROLE_PROFESOR).isPresent()) {
            profesor = new Rol(RolNombre.ROLE_PROFESOR);
            rolService.crearRol(profesor);
        }

        if (!rolService.getByRolNombre(RolNombre.ROLE_ESTUDIANTE).isPresent()) {
            estudiante = new Rol(RolNombre.ROLE_ESTUDIANTE);
            rolService.crearRol(estudiante);
        }
    }
}
