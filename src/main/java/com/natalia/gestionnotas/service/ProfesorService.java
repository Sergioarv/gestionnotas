package com.natalia.gestionnotas.service;

import com.natalia.gestionnotas.entity.Profesor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * @Project gestionnotas
 * @Author Sergio Abelardo Rodríguez Vásquez
 * @Email ingsergiorodriguezv@gmail.com
 * @Date 04/05/2023 - 11:50
 **/

public interface ProfesorService {

    Page<Profesor> filtrar(String nombre, String apellido, PageRequest pageable);

    Profesor agregarProfesor(Profesor profesor);

    Profesor editarProfesor(Profesor profesor);

    boolean eliminarProfesor(Profesor profesor);
}
