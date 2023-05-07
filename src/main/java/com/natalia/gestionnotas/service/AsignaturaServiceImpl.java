package com.natalia.gestionnotas.service;

import com.natalia.gestionnotas.entity.Asignatura;
import com.natalia.gestionnotas.entity.Estudiante;
import com.natalia.gestionnotas.entity.Profesor;
import com.natalia.gestionnotas.repository.AsignaturaRepository;
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
 * @Date 04/05/2023 - 12:01
 **/

@Service
public class AsignaturaServiceImpl implements AsignaturaService {

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<Asignatura> filtrar(String nombre, PageRequest pageable) {

        if (nombre == null) {
            return asignaturaRepository.findAll(pageable);
        } else {
            return asignaturaRepository.filtrarP(nombre, pageable);
        }

    }

    @Override
    @Transactional
    public Asignatura agregarAsignatura(Asignatura asignatura) {

        Optional<Asignatura> result = asignaturaRepository.findByNombre(asignatura.getNombre());

        if (!result.isPresent()) {
            return asignaturaRepository.save(asignatura);
        }

        return null;

    }

    @Override
    public Asignatura editarAsignatura(Asignatura asignatura) {

        Optional<Asignatura> result = asignaturaRepository.findById(asignatura.getIdasignatura());

        if (result.isPresent()) {
            Optional<Asignatura> result2 = asignaturaRepository.findByNombre(asignatura.getNombre());

            if (!result2.isPresent()) {
                asignaturaRepository.save(asignatura);
            }
        }

        return null;

    }

    @Override
    public boolean eliminarAsignatura(Asignatura asignatura) {

        Optional<Asignatura> result = asignaturaRepository.findById(asignatura.getIdasignatura());

        if (result.isPresent()) {
            asignaturaRepository.delete(asignatura);
        }

        return false;
    }


}
