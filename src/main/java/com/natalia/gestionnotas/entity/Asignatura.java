package com.natalia.gestionnotas.entity;


import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Project gestionnotas
 * @Author Sergio Abelardo Rodríguez Vásquez
 * @Email ingsergiorodriguezv@gmail.com
 * @Date 28/04/2023 - 12:36
 **/

@Entity
@Table(name = "asignatura")
public class Asignatura implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idasignatura")
    private int idasignatura;

    @Column(name = "nombre")
    private String nombre;

    @OneToMany(mappedBy="asignatura")
    private List<Nota> notas = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "idusuario")
    private Profesor profesor;

    /**
     * Getter y Setter
     **/


    public int getIdasignatura() {
        return idasignatura;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Nota> getNotas() {
        return notas;
    }

    public void setIdasignatura(int idasignatura) {
        this.idasignatura = idasignatura;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNotas(List<Nota> notas) {
        this.notas = notas;
    }

}
