package com.natalia.gestionnotas.security.service;

import com.natalia.gestionnotas.entity.Usuario;
import com.natalia.gestionnotas.security.entity.Rol;
import com.natalia.gestionnotas.security.enums.RolNombre;
import com.natalia.gestionnotas.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @Project gestionnotas
 * @Author Sergio Abelardo Rodríguez Vásquez
 * @Email ingsergiorodriguezv@gmail.com
 * @Date 09/05/2023 - 15:34
 **/

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolServiceImpl rolService;

    public Usuario getByCorreo(String correo) throws Exception {
        Optional<Usuario> u = usuarioRepository.findByCorreo(correo);
        if(u.isPresent()){
            return u.get();
        }
        throw new Exception("El usuario no existe");
    }

    public boolean existsByCorreo(String correo){
        return usuarioRepository.existsByCorreo(correo);
    }

    public Usuario agregarAdministrador(Usuario admin) {

        if (!usuarioRepository.existsByCorreo(admin.getCorreo())) {
            Set<Rol> roles = new HashSet<>();
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
            admin.setRoles(roles);

            return usuarioRepository.save(admin);
        }
        return null;
    }
}
