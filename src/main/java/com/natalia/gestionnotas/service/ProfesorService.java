package com.natalia.gestionnotas.service;

import com.natalia.gestionnotas.entity.Profesor;

import java.util.List;

/**
 * @Project gestionnotas
 * @Author Sergio Abelardo Rodríguez Vásquez
 * @Email ingsergiorodriguezv@gmail.com
 * @Date 04/05/2023 - 11:50
 **/

public interface ProfesorService {

    Profesor agregarProfesor(Profesor profesor);

    List<Profesor> filtrar(String nombre, String apellido);
}
