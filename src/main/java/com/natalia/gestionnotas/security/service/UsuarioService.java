package com.natalia.gestionnotas.security.service;

import com.natalia.gestionnotas.entity.Estudiante;
import com.natalia.gestionnotas.entity.Profesor;
import com.natalia.gestionnotas.entity.Usuario;
import com.natalia.gestionnotas.repository.EstudianteRepository;
import com.natalia.gestionnotas.repository.ProfesorRepository;
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
    @Autowired
    private EstudianteRepository estudianteRepository;
    @Autowired
    private ProfesorRepository profesorRepository;

    public Usuario getByCorreo(String correo) throws Exception {
        Optional<Estudiante> e = estudianteRepository.findByCorreo(correo);
        Optional<Profesor> p = profesorRepository.findByCorreo(correo);
        if(e.isPresent()){
            return e.get();
        }
        if(p.isPresent()){
            return p.get();
        }
        throw new Exception("El usuario no existe");
    }

    public boolean existsByCorreo(String correo){

        if(estudianteRepository.existsByCorreo(correo)){
            return true;
        }
        if(profesorRepository.existsByCorreo(correo)){
            return true;
        }
        return false;
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
