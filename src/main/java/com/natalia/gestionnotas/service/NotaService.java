package com.natalia.gestionnotas.service;

import com.natalia.gestionnotas.dto.NotasDTO;
import com.natalia.gestionnotas.entity.Nota;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Project gestionnotas
 * @Author Sergio Abelardo Rodríguez Vásquez
 * @Email ingsergiorodriguezv@gmail.com
 * @Date 04/05/2023 - 12:47
 **/
public interface NotaService {

    Page<NotasDTO> filtrar(String nombre, String apellido, String materia, Pageable pageable);


    Nota agregarNota(Nota nota);

    Nota editarNota(Nota nota);

    Boolean eliminarNota(Nota nota);
}
