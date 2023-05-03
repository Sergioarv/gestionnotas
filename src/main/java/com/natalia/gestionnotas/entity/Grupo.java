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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idgrupo;

    @Column(nullable = false)
    private String numenclatura;

    @OneToOne
    @JoinColumn(name = "idprofesor")
    private Profesor profesor;

    @OneToMany(mappedBy = "grupo")
    private List<Estudiante> estudiantes;

    @ManyToMany(mappedBy = "grupos")
    private List<Asignatura> asignaturas;

    /**
     * Constructor
     **/
    public Grupo() {
    }

    public Grupo(String numenclatura, Profesor profesor, List<Estudiante> estudiantes) {
        this.numenclatura = numenclatura;
        this.profesor = profesor;
        this.estudiantes = estudiantes;
    }

    /**
     * Setter y getter
     **/
    public int getIdgrupo() {
        return idgrupo;
    }

    public void setIdgrupo(int idgrupo) {
        this.idgrupo = idgrupo;
    }

    public String getNumenclatura() {
        return numenclatura;
    }

    public void setNumenclatura(String numenclatura) {
        this.numenclatura = numenclatura;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public List<Asignatura> getAsignaturas() {
        return asignaturas;
    }

    public void setAsignaturas(List<Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }
}
