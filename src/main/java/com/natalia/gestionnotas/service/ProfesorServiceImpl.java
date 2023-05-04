package com.natalia.gestionnotas.service;

import com.natalia.gestionnotas.entity.Profesor;
import com.natalia.gestionnotas.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public Profesor agregarEstudiante(Profesor profesor) {
        return profesorRepository.save(profesor);
    }
}
