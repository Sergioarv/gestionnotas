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
            nombre = "";
        }

        return asignaturaRepository.filtrarP(nombre, pageable);
    }

    @Override
    @Transactional
    public Asignatura agregarAsignatura(Asignatura asignatura) {

        Optional<Asignatura> result = asignaturaRepository.findByNombre(asignatura.getNombre());

        if (!result.isPresent()) {
            return asignaturaRepository.save(asignatura);
        } else {
            throw new RuntimeException("El nombre de la asignatura ya existe");
        }

    }

    @Override
    @Transactional
    public Asignatura editarAsignatura(Asignatura asignatura) {

        Optional<Asignatura> result = asignaturaRepository.findById(asignatura.getIdasignatura());

        if (result.isPresent()) {
            Optional<Asignatura> result2 = asignaturaRepository.findByNombre(asignatura.getNombre());

            if (!result2.isPresent()) {
                asignaturaRepository.save(asignatura);
            } else {
                throw new RuntimeException("El nuevo nombre de la asignatura ya existe");
            }
        } else {
            throw new RuntimeException("La asignatura que intenta editar no existe");
        }

        return null;

    }

    @Override
    @Transactional
    public boolean eliminarAsignatura(Asignatura asignatura) {

        Optional<Asignatura> result = asignaturaRepository.findById(asignatura.getIdasignatura());

        if (result.isPresent()) {
            asignaturaRepository.delete(asignatura);
        } else {
            throw new RuntimeException("La asignatura que intenta eliminar no existe");
        }

        return false;
    }


}
