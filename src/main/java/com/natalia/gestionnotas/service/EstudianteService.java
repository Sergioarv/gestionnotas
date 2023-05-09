package com.natalia.gestionnotas.service;

import com.natalia.gestionnotas.entity.Estudiante;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 * @Project gestionnotas
 * @Author Sergio Abelardo Rodríguez Vásquez
 * @Email ingsergiorodriguezv@gmail.com
 * @Date 02/05/2023 - 15:45
 **/
public interface EstudianteService {

    public Page<Estudiante> filtrar(String nombre, String apellido, Pageable pageable);

    Estudiante agregarEstudiante(Estudiante estudiante);

    Estudiante editarEstudiante(Estudiante estudiante);

    boolean eliminarEstudiante(Estudiante estudiante);
}
