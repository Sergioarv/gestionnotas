package com.natalia.gestionnotas.entity;

import com.natalia.gestionnotas.security.enums.RolNombre;
import jakarta.persistence.*;

/**
 * @Project gestionnotas
 * @Author Sergio Abelardo Rodríguez Vásquez
 * @Email ingsergiorodriguezv@gmail.com
 * @Date 28/04/2023 - 11:55
 **/

@Entity
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idrol;

    @Enumerated(EnumType.STRING)
    private RolNombre rolnombre;

    /**
     *  COnstructor
     */

    public Rol() {
    }

    public Rol(RolNombre rolnombre) {
        this.rolnombre = rolnombre;
    }

    /**
     * Getter y Setter
     */

    public int getIdrol() {
        return idrol;
    }

    public void setIdrol(int idrol) {
        this.idrol = idrol;
    }

    public RolNombre getRolnombre() {
        return rolnombre;
    }

    public void setRolnombre(RolNombre rolnombre) {
        this.rolnombre = rolnombre;
    }
}
