package com.natalia.gestionnotas.repository;

import com.natalia.gestionnotas.dto.AsignaturaDTO;
import com.natalia.gestionnotas.entity.Asignatura;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @Project gestionnotas
 * @Author Sergio Abelardo Rodríguez Vásquez
 * @Email ingsergiorodriguezv@gmail.com
 * @Date 04/05/2023 - 11:59
 **/

@Repository
public interface AsignaturaRepository extends JpaRepository<Asignatura, Integer> {

    @Query(value = "select * from asignatura where lower(nombre) like lower(concat('%',:nombre,'%'))", nativeQuery = true)
    Page<Asignatura> filtrarP(String nombre, Pageable pageable);

    Optional<Asignatura> findByNombre(String asignatura);

    @Query(value = "select * from asignatura where idasignatura = :idasignatura",nativeQuery = true)
    Optional<AsignaturaDTO> DtoId(int idasignatura);
}
