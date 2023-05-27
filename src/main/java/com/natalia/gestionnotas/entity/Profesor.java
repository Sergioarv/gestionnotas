package com.natalia.gestionnotas.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * @Project gestionnotas
 * @Author Sergio Abelardo Rodríguez Vásquez
 * @Email ingsergiorodriguezv@gmail.com
 * @Date 28/04/2023 - 12:08
 **/

@Entity
public class Profesor extends Usuario{

    @OneToMany(mappedBy = "profesor", cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Asignatura> asignaturas = new ArrayList<>();

    /** Getter y Setter **/

    public List<Asignatura> getAsignaturas() {
        return asignaturas;
    }

    public void setAsignaturas(List<Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }
}
