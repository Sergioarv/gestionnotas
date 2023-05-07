package com.natalia.gestionnotas.repository;

import com.natalia.gestionnotas.entity.Profesor;
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
 * @Date 04/05/2023 - 11:49
 **/

@Repository
public interface ProfesorRepository extends JpaRepository<Profesor, Integer> {

//    @Query(value = "select p from Profesor p")
//    Page<Profesor> filtrar();

    Optional<Profesor> findAllByCorreo(Profesor profesor);

    @Query(value = "select * from usuario where lower(nombre) like lower(concat('%',:nombre,'%')) and lower(apellido) like lower(concat('%',:apellido,'%'))", nativeQuery = true)
    Page<Profesor> filtrarP(String nombre, String apellido, Pageable pageable);


}
