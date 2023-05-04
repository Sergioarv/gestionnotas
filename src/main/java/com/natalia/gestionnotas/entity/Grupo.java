package com.natalia.gestionnotas.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @Project gestionnotas
 * @Author Sergio Abelardo Rodríguez Vásquez
 * @Email ingsergiorodriguezv@gmail.com
 * @Date 03/05/2023 - 10:57
 **/

@Entity
@Table(name = "grupo")
public class Grupo implements Serializable {

    @Id
    @Column(name = "idgrupo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idgrupo;

    @Column(nullable = false)
    private String numenclatura;

    /**
     * Setter y getter
     **/

}
