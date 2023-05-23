package com.natalia.gestionnotas.repository;

import com.natalia.gestionnotas.entity.Estudiante;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @Project gestionnotas
 * @Author Sergio Abelardo Rodríguez Vásquez
 * @Email ingsergiorodriguezv@gmail.com
 * @Date 02/05/2023 - 15:47
 **/

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Integer> {

    @Query(value = "select e from Estudiante e")
    List<Estudiante> filtrar();

    @Query(value = "select * from estudiante where lower(nombre) like lower(concat('%',:nombre,'%')) and lower(apellido) like lower(concat('%',:apellido,'%'))", nativeQuery = true)
    Page<Estudiante> filtrarP(String nombre, String apellido, Pageable pageable);

    Optional<Estudiante> findByCorreo(String estudiante);

    boolean existsByCorreo(String correo);
}
