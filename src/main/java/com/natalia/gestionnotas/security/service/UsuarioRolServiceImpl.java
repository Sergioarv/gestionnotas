package com.natalia.gestionnotas.security.service;

import com.natalia.gestionnotas.entity.Usuario;
import com.natalia.gestionnotas.security.entity.UsuarioPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Project gestionnotas
 * @Author Sergio Abelardo Rodríguez Vásquez
 * @Email ingsergiorodriguezv@gmail.com
 * @Date 09/05/2023 - 15:10
 **/

@Service
@Transactional
public class UsuarioRolServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {

        try {
            Usuario usuario = usuarioService.getByCorreo(correo);
            return UsuarioPrincipal.build(usuario);
        }catch (UsernameNotFoundException e){
            throw  new UsernameNotFoundException(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
