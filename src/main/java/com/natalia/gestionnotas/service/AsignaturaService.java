package com.natalia.gestionnotas.service;

import com.natalia.gestionnotas.entity.Asignatura;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * @Project gestionnotas
 * @Author Sergio Abelardo Rodríguez Vásquez
 * @Email ingsergiorodriguezv@gmail.com
 * @Date 04/05/2023 - 12:00
 **/
public interface AsignaturaService {

    Page<Asignatura> filtrar(String nombre, PageRequest pageable);

    Asignatura agregarAsignatura(Asignatura asignatura);

    Asignatura editarAsignatura(Asignatura asignatura);

    boolean eliminarAsignatura(Asignatura asignaturaService);
}
