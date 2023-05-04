package com.natalia.gestionnotas.service;

import com.natalia.gestionnotas.entity.Asignatura;
import com.natalia.gestionnotas.repository.AsignaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public List<Asignatura> filtrar() {
        return asignaturaRepository.findAll();
    }

    @Override
    @Transactional
    public Asignatura agregarAsignatura(Asignatura asignatura) {
        return asignaturaRepository.save(asignatura);
    }


}
