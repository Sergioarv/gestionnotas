package com.natalia.gestionnotas.service;

import com.natalia.gestionnotas.entity.Nota;

import java.util.List;

/**
 * @Project gestionnotas
 * @Author Sergio Abelardo Rodríguez Vásquez
 * @Email ingsergiorodriguezv@gmail.com
 * @Date 04/05/2023 - 12:47
 **/
public interface NotaService {

    List<Nota> filtrar();


    Nota agregarNota(Nota nota);
}
