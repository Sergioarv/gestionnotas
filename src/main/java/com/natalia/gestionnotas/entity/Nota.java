package com.natalia.gestionnotas.entity;


import javax.persistence.*;
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

    @Column(name = "calificacion")
    private float calificacion;

    @ManyToOne
    @JoinColumn(name = "idusuario")
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "idasignatura")
    private Asignatura asignatura;


    /**
     * Getter y Setter
     **/

    public int getIdnota() {
        return idnota;
    }

    public float getCalificacion() {
        return calificacion;
    }

    public void setIdnota(int idnota) {
        this.idnota = idnota;
    }

    public void setCalificacion(float calificacion) {
        this.calificacion = calificacion;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

}
