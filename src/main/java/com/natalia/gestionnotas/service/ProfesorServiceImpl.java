package com.natalia.gestionnotas.service;

import com.natalia.gestionnotas.entity.Profesor;
import com.natalia.gestionnotas.repository.ProfesorRepository;
import com.natalia.gestionnotas.security.entity.Rol;
import com.natalia.gestionnotas.security.enums.RolNombre;
import com.natalia.gestionnotas.security.service.RolServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @Project gestionnotas
 * @Author Sergio Abelardo Rodríguez Vásquez
 * @Email ingsergiorodriguezv@gmail.com
 * @Date 04/05/2023 - 11:51
 **/

@Service
public class ProfesorServiceImpl implements ProfesorService {

    @Autowired
    private ProfesorRepository profesorRepository;

    @Autowired
    RolServiceImpl rolService;

    @Override
    @Transactional(readOnly = true)
    public Page<Profesor> filtrar(String nombre, String apellido, PageRequest pageable) {

        if (nombre == null) {
            nombre = "";
        }

        if (apellido == null) {
            apellido = "";
        }

        return profesorRepository.filtrarP(nombre, apellido, pageable);

    }

    @Override
    @Transactional
    public Profesor agregarProfesor(Profesor profesor) {

        Optional<Profesor> result = profesorRepository.findByCorreo(profesor.getCorreo());

        if (!result.isPresent()) {
            Set<Rol> roles = new HashSet<>();
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
            profesor.setRoles(roles);

            return profesorRepository.save(profesor);
        } else {
            throw new RuntimeException("El correo del profesor ya existe");
        }

    }

    @Override
    @Transactional
    public Profesor editarProfesor(Profesor profesor) {

        Optional<Profesor> result = profesorRepository.findById(profesor.getIdusuario());

        if (result.isPresent()) {
            Optional<Profesor> result2 = profesorRepository.findByCorreo(profesor.getCorreo());

            if (!result2.isPresent()) {
                return profesorRepository.save(profesor);
            } else {
                if(result2.get().getIdusuario() == profesor.getIdusuario()){
                    return profesorRepository.save(profesor);
                }else {
                    throw new RuntimeException("El correo el profesor a editar ya existe");
                }
            }
        } else {
            throw new RuntimeException("El profesor a editar no existe");
        }
    }

    @Override
    @Transactional
    public boolean eliminarProfesor(Profesor profesor) {

        Optional<Profesor> result = profesorRepository.findById(profesor.getIdusuario());

        if (result.isPresent()) {
            profesorRepository.delete(profesor);
            return true;
        } else {
            throw new RuntimeException("El profesor que intenta eliminar no existe");
        }
    }
}
