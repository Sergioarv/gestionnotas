package com.natalia.gestionnotas.repository;

import com.natalia.gestionnotas.dto.NotasDTO;
import com.natalia.gestionnotas.entity.Nota;
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
 * @Date 04/05/2023 - 12:26
 **/

@Repository
public interface NotaRepository extends JpaRepository<Nota, Integer> {

    @Query(value = "select n.idnota, n.idusuario from nota as n where n.idnota = :idnota", nativeQuery = true)
    Optional<NotasDTO> dtoId(int idnota);

    @Query(value = "select * from (select u.idusuario, u.nombre, u.apellido, u.correo, n.calificacion, n.idnota, a.nombre as materia, a.idasignatura from nota as n inner join estudiante as u on u.idusuario = n.idusuario inner join asignatura as a on a.idasignatura = n.idasignatura) c1 where lower(c1.nombre) like lower(concat('%',:nombre,'%')) and lower(c1.apellido) like lower(concat('%',:apellido,'%')) and lower(c1.materia) like lower(concat('%',:materia,'%'))", nativeQuery = true)
    Page<NotasDTO> filtrarNAM(String nombre, String apellido, String materia, Pageable pageable);

    @Query(value = "select * from (select u.idusuario, u.nombre, u.apellido, u.correo, n.calificacion, n.idnota, a.nombre as materia, a.idasignatura from nota as n inner join estudiante as u on u.idusuario = n.idusuario inner join asignatura as a on a.idasignatura = n.idasignatura) c1 where c1.idusuario = :idusuario and c1.idasignatura = :idasignatura", nativeQuery = true)
    NotasDTO obtenerEstAsig(int idusuario, int idasignatura);
}
