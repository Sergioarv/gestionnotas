package com.natalia.gestionnotas.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;

import java.util.ArrayList;
import java.util.List;

/**
 * @Project gestionnotas
 * @Author Sergio Abelardo Rodríguez Vásquez
 * @Email ingsergiorodriguezv@gmail.com
 * @Date 28/04/2023 - 12:08
 **/

@Entity
@PrimaryKeyJoinColumn(name = "idusuario")
public class Profesor extends Usuario{

    @OneToMany(mappedBy = "profesor")
    private List<Asignatura> asignaturas = new ArrayList<>();

    /** Getter y Setter **/

    public List<Asignatura> getAsignaturas() {
        return asignaturas;
    }

    public void setAsignaturas(List<Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }
}
