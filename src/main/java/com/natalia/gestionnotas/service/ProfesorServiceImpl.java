package com.natalia.gestionnotas.service;

import com.natalia.gestionnotas.entity.Profesor;
import com.natalia.gestionnotas.repository.ProfesorRepository;
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
 * @Date 04/05/2023 - 11:51
 **/

@Service
public class ProfesorServiceImpl implements ProfesorService {

    @Autowired
    private ProfesorRepository profesorRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<Profesor> filtrar(String nombre, String apellido, PageRequest pageable) {

        if(nombre == null && apellido == null){
        return profesorRepository.findAll(pageable);
        }else{
            if(nombre == null){
                nombre = "";
            }

            if(apellido == null){
                apellido = "";
            }

            return profesorRepository.filtrarP(nombre, apellido, pageable);
        }
    }

    @Override
    @Transactional
    public Profesor agregarProfesor(Profesor profesor) {

        Optional<Profesor> result = profesorRepository.findByCorreo(profesor.getCorreo());

        if(!result.isPresent()){
            return profesorRepository.save(profesor);
        }

        return null;
    }

    @Override
    @Transactional
    public Profesor editarProfesor(Profesor profesor) {

        Optional<Profesor> result = profesorRepository.findById(profesor.getIdusuario());

        if(result.isPresent()) {
            Optional<Profesor> result2 = profesorRepository.findByCorreo(profesor.getCorreo());

            if (!result2.isPresent()) {
                profesorRepository.save(profesor);
            }
        }

        return null;
    }

    @Override
    public boolean eliminarProfesor(Profesor profesor) {

        Optional<Profesor> result = profesorRepository.findById(profesor.getIdusuario());

        if(result.isPresent()){
            profesorRepository.delete(profesor);
        }

        return false;
    }
}
