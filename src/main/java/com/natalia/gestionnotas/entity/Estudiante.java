package com.natalia.gestionnotas.entity;

import jakarta.persistence.*;

import java.util.List;

/**
 * @Project gestionnotas
 * @Author Sergio Abelardo Rodríguez Vásquez
 * @Email ingsergiorodriguezv@gmail.com
 * @Date 28/04/2023 - 12:35
 **/

@Entity
@PrimaryKeyJoinColumn(name = "idusuario")
public class Estudiante extends Usuario {

    @ManyToOne
    @JoinColumn(name = "idgrupo")
    private Grupo grupo;

    @OneToMany(mappedBy = "estudiante")
    private List<Nota> notas;

    /** Getter y Setter **/

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public List<Nota> getNotas() {
        return notas;
    }

    public void setNotas(List<Nota> notas) {
        this.notas = notas;
    }
}
