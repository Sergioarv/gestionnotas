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

    @JoinTable(
            name = "asignatura_grupo",
            joinColumns = @JoinColumn(name = "idasignatura", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "idgrupo", nullable = false)
    )
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Grupo> grupos;

    @OneToMany(mappedBy = "asignatura")
    private List<Nota> notas;

    public void agregarGrupo(Grupo grupo) {
        if (this.grupos == null) {
            this.grupos = new ArrayList<>();
        }

        this.grupos.add(grupo);
    }

    public void removerGrupo(Grupo grupo) {
        this.grupos.remove(grupo);
        grupo.getAsignaturas().remove(this);
    }

    /**
     * Constructor
     **/
    public Asignatura() {
    }

    public Asignatura(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Getter y Setter
     **/
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

    public List<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }

    public List<Nota> getNotas() {
        return notas;
    }

    public void setNotas(List<Nota> notas) {
        this.notas = notas;
    }
}
