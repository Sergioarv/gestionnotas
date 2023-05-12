package com.natalia.gestionnotas.security.entity;

import com.natalia.gestionnotas.security.enums.RolNombre;
import javax.persistence.*;


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
    private RolNombre rolNombre;

    /**
     *  COnstructor
     */

    public Rol() {
    }

    public Rol(RolNombre rolnombre) {
        this.rolNombre = rolnombre;
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

    public RolNombre getRolNombre() {
        return rolNombre;
    }

    public void setRolNombre(RolNombre rolNombre) {
        this.rolNombre = rolNombre;
    }
}
