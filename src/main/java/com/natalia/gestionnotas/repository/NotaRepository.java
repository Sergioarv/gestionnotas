package com.natalia.gestionnotas.repository;

import com.natalia.gestionnotas.dto.NotasDTO;
import com.natalia.gestionnotas.entity.Nota;
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
 * @Date 04/05/2023 - 12:26
 **/

@Repository
public interface NotaRepository extends JpaRepository<Nota, Integer> {

    Optional<Nota> findByEstudianteAndAsignatura(int estudiante, int asignatura);

    @Query(value = "select u.nombre, u.apellido, u.correo, a.nombre as materia, n.calificacion from nota n inner join usuario u on n.idusuario = u.idusuario inner join asignatura a on n.idasignatura = a.idasignatura;", nativeQuery = true)
    List<NotasDTO> filtrar();

    @Query(value = "select * from (select  u.idusuario, a.idasignatura, u.nombre, u.apellido, u.correo, a.nombre as materia, n.calificacion from nota n inner join usuario u on n.idusuario = u.idusuario inner join asignatura a on n.idasignatura = a.idasignatura) c1 where lower(c1.nombre) like lower(concat('%',:nombre,'%')) and lower(c1.apellido) like lower(concat('%',:apellido,'%')) and lower(c1.materia) like lower(concat('%',:materia,'%'));", nativeQuery = true)
    Page<NotasDTO> filtrarNAM(String nombre, String apellido, String materia, Pageable pageable);

}
