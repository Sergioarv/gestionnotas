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
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Estudiante> estudiantes;

    public void agregarEstudiante(Estudiante estudiante){
        if(this.estudiantes == null){
            this.estudiantes = new ArrayList<>();
        }

        this.estudiantes.add(estudiante);
    }

    public void removerEstudiante( Estudiante estudiante){
        this.estudiantes.remove(estudiante);
        estudiante.getAsignaturas().remove(this);
    }

    /** Constructor **/
    public Asignatura() {
    }

    public Asignatura(int idasignatura, String nombre) {
        this.idasignatura = idasignatura;
        this.nombre = nombre;
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

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }
}
