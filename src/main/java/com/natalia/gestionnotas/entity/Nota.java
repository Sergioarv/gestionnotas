package com.natalia.gestionnotas.entity;

import jakarta.persistence.*;

import java.io.Serializable;

/**
 * @Project gestionnotas
 * @Author Sergio Abelardo Rodríguez Vásquez
 * @Email ingsergiorodriguezv@gmail.com
 * @Date 03/05/2023 - 11:19
 **/

@Entity
@Table(name = "nota")
public class Nota implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idnota;

    @ManyToOne
    @JoinColumn(name = "idestudiante")
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "idasignatura")
    private Asignatura asignatura;

    /** Constructor **/

    public Nota() {
    }

    public Nota(Estudiante estudiante, Asignatura asignatura) {
        this.estudiante = estudiante;
        this.asignatura = asignatura;
    }

    /** Getter y Setter **/

    public int getIdnota() {
        return idnota;
    }

    public void setIdnota(int idnota) {
        this.idnota = idnota;
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
}
