package com.natalia.gestionnotas.service;

import com.natalia.gestionnotas.dto.NotasDTO;
import com.natalia.gestionnotas.entity.Nota;
import com.natalia.gestionnotas.repository.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @Project gestionnotas
 * @Author Sergio Abelardo Rodríguez Vásquez
 * @Email ingsergiorodriguezv@gmail.com
 * @Date 04/05/2023 - 12:47
 **/

@Service
public class NotaServiceImpl implements NotaService {

    @Autowired
    private NotaRepository notaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<NotasDTO> filtrar(String nombre, String apellido, String materia, Pageable pageable) {

        if (nombre == null) {
            nombre = "";
        }

        if (apellido == null) {
            apellido = "";
        }

        if (materia == null) {
            materia = "";
        }

        return notaRepository.filtrarNAM(nombre, apellido, materia, pageable);
    }

    @Override
    @Transactional
    public Nota agregarNota(Nota nota) {

//        Optional<Nota> result = notaRepository.findByEstudianteAndAsignatura();

//        if (!result.isPresent()) {
            return notaRepository.save(nota);
//        }

//        return null;
    }

    @Override
    @Transactional
    public Nota editarNota(Nota nota) {

        Optional<Nota> result = notaRepository.findById(nota.getIdnota());

        if (result.isPresent()) {
            return notaRepository.save(nota);
        }

        return null;
    }

    @Override
    @Transactional
    public Boolean eliminarNota(Nota nota) {

        Optional<Nota> result = notaRepository.findById(nota.getIdnota());

        if (result.isPresent()) {
            notaRepository.delete(nota);
        }

        return null;
    }

}
