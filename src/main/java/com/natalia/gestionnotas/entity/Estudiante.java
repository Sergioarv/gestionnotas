package com.natalia.gestionnotas.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "estudiante")
    private List<Asigestud> asigestuds;

    /** Getter y Setter **/
    public List<Asigestud> getAsigestuds() {
        return asigestuds;
    }

    public void setAsigestuds(List<Asigestud> asigestuds) {
        this.asigestuds = asigestuds;
    }
}
