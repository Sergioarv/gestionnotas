package com.natalia.gestionnotas.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @Project gestionnotas
 * @Author Sergio Abelardo Rodríguez Vásquez
 * @Email ingsergiorodriguezv@gmail.com
 * @Date 28/04/2023 - 11:50
 **/

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario implements Serializable {

    @Id
    @Column(name = "idusuario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idusuario;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false, unique = true)
    private String correo;

    @Column(nullable = false)
    private String contrasenia;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "idusuario", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "idrol", nullable = false))
    private Set<Rol> roles = new HashSet<>();

    /**
     *  COnstructor
     */

    public Usuario() {
    }

    public Usuario(String nombre, String apellido, String correo, String contrasenia) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.contrasenia = contrasenia;
    }

    /** Getter y Setter **/

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }
}
