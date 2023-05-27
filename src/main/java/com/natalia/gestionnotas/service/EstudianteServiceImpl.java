package com.natalia.gestionnotas.service;

import com.natalia.gestionnotas.dto.NotasDTO;
import com.natalia.gestionnotas.entity.*;
import com.natalia.gestionnotas.repository.AsignaturaRepository;
import com.natalia.gestionnotas.repository.EstudianteRepository;
import com.natalia.gestionnotas.repository.NotaRepository;
import com.natalia.gestionnotas.repository.ProfesorRepository;
import com.natalia.gestionnotas.security.entity.Rol;
import com.natalia.gestionnotas.security.enums.RolNombre;
import com.natalia.gestionnotas.security.repository.UsuarioRepository;
import com.natalia.gestionnotas.security.service.RolServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @Project gestionnotas
 * @Author Sergio Abelardo Rodríguez Vásquez
 * @Email ingsergiorodriguezv@gmail.com
 * @Date 02/05/2023 - 15:46
 **/

@Service
public class EstudianteServiceImpl implements EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;
    @Autowired
    private RolServiceImpl rolService;
    @Autowired
    private ProfesorRepository profesorRepository;
    @Autowired
    private NotaRepository notaRepository;
    @Autowired
    private AsignaturaRepository asignaturaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Estudiante> listar() {
        return estudianteRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Estudiante> filtrar(String nombre, String apellido, Pageable pageable) {

        if (nombre == null) {
            nombre = "";
        }

        if (apellido == null) {
            apellido = "";
        }

        return estudianteRepository.filtrarP(nombre, apellido, pageable);
    }

    @Override
    @Transactional
    public Estudiante agregarEstudiante(Estudiante estudiante) {

        Optional<Estudiante> resultE = estudianteRepository.findByCorreo(estudiante.getCorreo());
        Optional<Profesor> resultP = profesorRepository.findByCorreo(estudiante.getCorreo());
        Optional<Usuario> resultA = usuarioRepository.findByCorreo(estudiante.getCorreo());

        if (!resultE.isPresent() && !resultP.isPresent() && !resultA.isPresent()) {
            Set<Rol> roles = new HashSet<>();
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ESTUDIANTE).get());
            estudiante.setRoles(roles);

            return estudianteRepository.save(estudiante);
        } else {
            throw new RuntimeException("El correo del estudiante ya existe");
        }
    }

    @Override
    @Transactional
    public Estudiante editarEstudiante(Estudiante estudiante) {

        Optional<Estudiante> result = estudianteRepository.findById(estudiante.getIdusuario());
        List<Nota> notasG;
        List<Nota> notasGuardadas;
        Estudiante estudianteG;

        if (result.isPresent()) {
            Optional<Estudiante> resultE = estudianteRepository.findByCorreo(estudiante.getCorreo());
            Optional<Profesor> resultP = profesorRepository.findByCorreo(estudiante.getCorreo());
            Optional<Usuario> resultA = usuarioRepository.findByCorreo(estudiante.getCorreo());

            notasGuardadas = estudiante.getNotas();
            estudiante.setNotas(null);

            if (!resultE.isPresent() && !resultP.isPresent() && !resultA.isPresent()) {
                estudianteG = estudianteRepository.save(estudiante);
                notasGuardadas = settearNotas(notasGuardadas, estudianteG);
            } else if (resultE.isPresent() && resultE.get().getIdusuario() == estudiante.getIdusuario()) {
                estudianteG = estudianteRepository.save(estudiante);
                notasGuardadas = settearNotas(notasGuardadas, estudianteG);
            } else {
                throw new RuntimeException("El correo el estudiante a editar ya existe");
            }
        } else {
            throw new RuntimeException("El estudiante a editar no existe");
        }
        notasG = notaRepository.saveAll(notasGuardadas);
        estudianteG.setNotas(notasG);
        return estudianteRepository.save(estudianteG);
    }

    private List<Nota> settearNotas(List<Nota> notasGuardadas, Estudiante estudianteG) {

        for (Nota n : notasGuardadas) {
            Optional<Nota> notaR;
            Optional<NotasDTO> dto = notaRepository.dtoId(n.getIdnota());
            notaR = notaRepository.findById(n.getIdnota());

            if (notaR.isPresent()) {
                if (dto.get().getIdusuario() == estudianteG.getIdusuario()) {
                    Optional<Asignatura> asigR = asignaturaRepository.findById(n.getIdnota());
                    n.setEstudiante(estudianteG);
                    n.setAsignatura(asigR.get());
                }
            }
        }

        return notasGuardadas;
    }

    @Override
    @Transactional
    public boolean eliminarEstudiante(Estudiante estudiante) {

        Optional<Estudiante> result = estudianteRepository.findById(estudiante.getIdusuario());

        if (result.isPresent()) {
            for (Nota n : result.get().getNotas()) {
                n.setEstudiante(null);
                n.setAsignatura(null);
            }
            notaRepository.saveAll(result.get().getNotas());
            notaRepository.deleteAll(result.get().getNotas());
            estudianteRepository.delete(estudiante);
            return true;
        } else {
            throw new RuntimeException("El estudiante que intenta eliminar no existe");
        }
    }
}
