package com.natalia.gestionnotas.service;

import com.natalia.gestionnotas.entity.Estudiante;
import com.natalia.gestionnotas.entity.Profesor;
import com.natalia.gestionnotas.repository.EstudianteRepository;
import com.natalia.gestionnotas.security.entity.Rol;
import com.natalia.gestionnotas.security.enums.RolNombre;
import com.natalia.gestionnotas.security.service.RolServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @Project gestionnotas
 * @Author Sergio Abelardo Rodríguez Vásquez
 * @Email ingsergiorodriguezv@gmail.com
 * @Date 02/05/2023 - 15:46
 **/

@Service
public class EstudianteServiceImpl implements EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private RolServiceImpl rolService;

    @Override
    @Transactional(readOnly = true)
    public Page<Estudiante> filtrar(String nombre, String apellido, Pageable pageable) {

        if (nombre == null) {
            nombre = "";
        }

        if (apellido == null) {
            apellido = "";
        }

        return estudianteRepository.filtrarP(nombre, apellido, pageable);
    }

    @Override
    @Transactional
    public Estudiante agregarEstudiante(Estudiante estudiante) {

        Optional<Estudiante> result = estudianteRepository.findByCorreo(estudiante.getCorreo());

        if (!result.isPresent()) {
            Set<Rol> roles = new HashSet<>();
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
            estudiante.setRoles(roles);
            return estudianteRepository.save(estudiante);
        } else {
            throw new RuntimeException("El correo del estudiante ya existe");
        }

    }

    @Override
    @Transactional
    public Estudiante editarEstudiante(Estudiante estudiante) {

        Optional<Estudiante> result = estudianteRepository.findById(estudiante.getIdusuario());

        if (result.isPresent()) {
            Optional<Estudiante> result2 = estudianteRepository.findByCorreo(estudiante.getCorreo());

            if (!result2.isPresent()) {
                return estudianteRepository.save(estudiante);
            } else {
                if (result2.get().getIdusuario() == estudiante.getIdusuario()) {
                    return estudianteRepository.save(estudiante);
                }
                throw new RuntimeException("El correo el estudiante a editar ya existe");
            }
        } else {
            throw new RuntimeException("El estudiante a editar no existe");
        }
    }

    @Override
    @Transactional
    public boolean eliminarEstudiante(Estudiante estudiante) {

        Optional<Estudiante> result = estudianteRepository.findById(estudiante.getIdusuario());

        if (result.isPresent()) {
            estudianteRepository.delete(estudiante);
        } else {
            throw new RuntimeException("El estudiante que intenta eliminar no existe");
        }

        return false;

    }
}
