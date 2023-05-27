package com.natalia.gestionnotas.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * @Project gestionnotas
 * @Author Sergio Abelardo Rodríguez Vásquez
 * @Email ingsergiorodriguezv@gmail.com
 * @Date 28/04/2023 - 12:35
 **/

@Entity
public class Estudiante extends Usuario {

    @OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL)
    private List<Nota> notas = new ArrayList<>();

    /**
     * Getter y Setter
     **/

    public List<Nota> getNotas() {
        return notas;
    }

    public void setNotas(List<Nota> notas) {
        this.notas = notas;
    }
}
