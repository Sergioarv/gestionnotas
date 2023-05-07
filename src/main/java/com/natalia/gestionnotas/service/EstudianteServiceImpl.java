package com.natalia.gestionnotas.service;

import com.natalia.gestionnotas.entity.Estudiante;
import com.natalia.gestionnotas.entity.Profesor;
import com.natalia.gestionnotas.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
    public Page<Estudiante> filtrar(String nombre, String apellido, PageRequest pageable) {

        if(nombre == null && apellido == null){
            return estudianteRepository.findAll(pageable);
        }else {
            if (nombre == null) {
                nombre = "";
            }

            if (apellido == null) {
                apellido = "";
            }

            return estudianteRepository.filtrarP(nombre, apellido, pageable);
        }
    }

    @Override
    @Transactional
    public Estudiante agregarEstudiante(Estudiante estudiante) {

//        Set<Rol> roles = new HashSet<>();
//        roles.add(rolService.getByRolNombre(RolNombre.ROLE_ESTUDIANTE).get());
//        estudiante.setRoles(roles);

        Optional<Profesor> result = estudianteRepository.findAllByCorreo(estudiante);

        if(!result.isPresent()){
            return estudianteRepository.save(estudiante);
        }

        return null;
    }

    @Override
    public Estudiante editarEstudiante(Estudiante estudiante) {

        Optional<Estudiante> result = estudianteRepository.findById(estudiante.getIdusuario());

        if(result.isPresent()){
            estudianteRepository.save(estudiante);
        }

        return null;
    }

    @Override
    public boolean eliminarEstudiante(Estudiante estudiante) {

        Optional<Estudiante> result = estudianteRepository.findById(estudiante.getIdusuario());

        if(result.isPresent()){
            estudianteRepository.delete(estudiante);
        }

        return false;

    }
}
