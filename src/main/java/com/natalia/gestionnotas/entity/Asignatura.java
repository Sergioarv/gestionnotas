package com.natalia.gestionnotas.entity;

import jakarta.persistence.*;

import java.io.Serializable;
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
    private int idasignatura;

    @Column(name = "nombre")
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "idusuario")
    private Profesor profesor;

    @JoinTable(
            name = "asig_estudiante",
            joinColumns = @JoinColumn(name = "idasignatura", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "idestudiante", nullable = false)
    )
    @OneToMany(mappedBy = "asignatura")
    private List<Asigestud> asigestuds;


    /** Constructor **/
    public Asignatura() {
    }

    public Asignatura(int idasignatura, String nombre, Profesor profesor, List<Asigestud> asigestuds) {
        this.nombre = nombre;
        this.profesor = profesor;
    }

/** Getter y Setter **/
    public int getIdasignatura() {
        return idasignatura;
    }

    public void setIdasignatura(int idasignatura) {
        this.idasignatura = idasignatura;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public List<Asigestud> getAsigestuds() {
        return asigestuds;
    }

    public void setAsigestuds(List<Asigestud> asigestuds) {
        this.asigestuds = asigestuds;
    }
}
