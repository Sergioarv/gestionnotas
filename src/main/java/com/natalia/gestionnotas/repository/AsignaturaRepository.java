package com.natalia.gestionnotas.repository;

import com.natalia.gestionnotas.entity.Asignatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Project gestionnotas
 * @Author Sergio Abelardo Rodríguez Vásquez
 * @Email ingsergiorodriguezv@gmail.com
 * @Date 04/05/2023 - 11:59
 **/

@Repository
public interface AsignaturaRepository extends JpaRepository<Asignatura, Integer> {
}
