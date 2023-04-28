package com.natalia.gestionnotas.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;

import java.util.List;

/**
 * @Project gestionnotas
 * @Author Sergio Abelardo Rodríguez Vásquez
 * @Email ingsergiorodriguezv@gmail.com
 * @Date 28/04/2023 - 12:35
 **/

@Entity
@PrimaryKeyJoinColumn(name = "idusuario")
public class Estudiante extends Usuario{

    @ManyToMany(mappedBy = "estudiantes")
    private List<Asignatura> asignaturas;

    /** Getter y Setter **/
    public List<Asignatura> getAsignaturas() {
        return asignaturas;
    }

    public void setAsignaturas(List<Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }
}
