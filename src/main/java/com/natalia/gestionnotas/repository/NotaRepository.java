package com.natalia.gestionnotas.repository;

import com.natalia.gestionnotas.entity.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Project gestionnotas
 * @Author Sergio Abelardo Rodríguez Vásquez
 * @Email ingsergiorodriguezv@gmail.com
 * @Date 04/05/2023 - 12:26
 **/

@Repository
public interface NotaRepository extends JpaRepository<Nota, Integer> {
}
