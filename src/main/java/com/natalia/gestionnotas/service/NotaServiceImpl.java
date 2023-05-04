package com.natalia.gestionnotas.service;

import com.natalia.gestionnotas.entity.Nota;
import com.natalia.gestionnotas.repository.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public List<Nota> filtrar() {
        return notaRepository.findAll();
    }

    @Override
    @Transactional
    public Nota agregarNota(Nota nota) {
        return notaRepository.save(nota);
    }


}
