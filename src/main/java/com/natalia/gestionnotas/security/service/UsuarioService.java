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
        Optional<Usuario> a = usuarioRepository.findByCorreo(correo);

        if(e.isPresent()){
            return e.get();
        }
        if(p.isPresent()){
            return p.get();
        }
        if(a.isPresent()){
            return a.get();
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

        Optional<Estudiante> resultE = estudianteRepository.findByCorreo(admin.getCorreo());
        Optional<Profesor> resultP = profesorRepository.findByCorreo(admin.getCorreo());
        Optional<Usuario> resultA = usuarioRepository.findByCorreo(admin.getCorreo());

        if (!resultE.isPresent() && !resultP.isPresent() && !resultA.isPresent()) {
            Set<Rol> roles = new HashSet<>();
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
            admin.setRoles(roles);

            return usuarioRepository.save(admin);
        }else{
            throw new RuntimeException("El correo del administrador ya existe");
        }
    }
}
