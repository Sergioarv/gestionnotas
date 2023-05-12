package com.natalia.gestionnotas.security.entity;

import com.natalia.gestionnotas.entity.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Project gestionnotas
 * @Author Sergio Abelardo Rodríguez Vásquez
 * @Email ingsergiorodriguezv@gmail.com
 * @Date 09/05/2023 - 15:08
 **/
public class UsuarioPrincipal implements UserDetails {

    private int idusuario;

    private String nombre;

    private String apellido;

    private String correo;

    private String contrasenia;

    private Collection<? extends GrantedAuthority> autoridades;

    /**
     * Constructor
     **/

    public UsuarioPrincipal(int idusuario, String nombre, String apellido, String correo, String contrasenia, Collection<? extends GrantedAuthority> autoridades) {
        this.idusuario = idusuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.autoridades = autoridades;
    }

    public static UsuarioPrincipal build(Usuario usuario) {
        List<GrantedAuthority> authorities =
                usuario.getRoles().stream().map(rol -> new SimpleGrantedAuthority(
                        rol.getRolNombre().name())).collect(Collectors.toList());
        return new UsuarioPrincipal(usuario.getIdusuario(), usuario.getNombre(), usuario.getApellido(),
                usuario.getCorreo(), usuario.getContrasenia(), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return autoridades;
    }

    @Override
    public String getPassword() {
        return contrasenia;
    }

    @Override
    public String getUsername() {
        return correo;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * Getter y Setter
     **/

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
}
