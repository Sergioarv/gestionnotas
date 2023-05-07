package com.natalia.gestionnotas.service;

import com.natalia.gestionnotas.entity.Asignatura;

import java.util.List;

/**
 * @Project gestionnotas
 * @Author Sergio Abelardo Rodríguez Vásquez
 * @Email ingsergiorodriguezv@gmail.com
 * @Date 04/05/2023 - 12:00
 **/
public interface AsignaturaService {

    Asignatura agregarAsignatura(Asignatura asignatura);

    List<Asignatura> filtrar();
}
