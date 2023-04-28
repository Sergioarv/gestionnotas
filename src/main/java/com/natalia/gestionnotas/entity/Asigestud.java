package com.natalia.gestionnotas.entity;

import jakarta.persistence.*;

import java.io.Serializable;

/**
 * @Project gestionnotas
 * @Author Sergio Abelardo Rodríguez Vásquez
 * @Email ingsergiorodriguezv@gmail.com
 * @Date 28/04/2023 - 13:39
 **/

@Entity
@Table(name = "asigestud")
public class Asigestud implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idasigestud;

    @ManyToOne
    @JoinColumn(name = "idestudiante")
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "idasignatura")
    private Asignatura asignatura;

    @Column(name = "nota")
    private float nota;

    /** Constructor **/
    public Asigestud() {
    }

    public Asigestud(Estudiante estudiante, Asignatura asignatura, float nota) {
        this.estudiante = estudiante;
        this.asignatura = asignatura;
        this.nota = nota;
    }

    /** Setter y Getter **/

    public int getIdasigestud() {
        return idasigestud;
    }

    public void setIdasigestud(int idasigestud) {
        this.idasigestud = idasigestud;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }
}
