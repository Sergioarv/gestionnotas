package com.natalia.gestionnotas.service;

import com.natalia.gestionnotas.entity.Estudiante;

import java.util.List;

/**
 * @Project gestionnotas
 * @Author Sergio Abelardo Rodríguez Vásquez
 * @Email ingsergiorodriguezv@gmail.com
 * @Date 02/05/2023 - 15:45
 **/
public interface EstudianteService {

    public List<Estudiante> filtrar(String nombre, String apellido);
}
