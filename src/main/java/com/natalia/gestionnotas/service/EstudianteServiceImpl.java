package com.natalia.gestionnotas.service;

import com.natalia.gestionnotas.entity.Estudiante;
import com.natalia.gestionnotas.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Override
    @Transactional(readOnly = true)
    public List<Estudiante> filtrar(String nombre, String apellido) {
        return estudianteRepository.filtrar();
    }

    @Override
    @Transactional
    public Estudiante agregarEstudiante(Estudiante estudiante) {

//        Set<Rol> roles = new HashSet<>();
//        roles.add(rolService.getByRolNombre(RolNombre.ROLE_ESTUDIANTE).get());
//        estudiante.setRoles(roles);

        return estudianteRepository.save(estudiante);
    }
}
