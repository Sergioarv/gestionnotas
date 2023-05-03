package com.natalia.gestionnotas.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;

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

    @OneToOne(mappedBy = "profesor")
    private Grupo grupo;

    /** Getter y Setter **/
    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }
}
