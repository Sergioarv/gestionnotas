package com.natalia.gestionnotas.service;

import com.natalia.gestionnotas.dto.AsignaturaDTO;
import com.natalia.gestionnotas.entity.Asignatura;
import com.natalia.gestionnotas.entity.Estudiante;
import com.natalia.gestionnotas.entity.Profesor;
import com.natalia.gestionnotas.repository.AsignaturaRepository;
import com.natalia.gestionnotas.repository.EstudianteRepository;
import com.natalia.gestionnotas.repository.ProfesorRepository;
import com.natalia.gestionnotas.security.entity.Rol;
import com.natalia.gestionnotas.security.enums.RolNombre;
import com.natalia.gestionnotas.security.service.RolServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @Project gestionnotas
 * @Author Sergio Abelardo Rodríguez Vásquez
 * @Email ingsergiorodriguezv@gmail.com
 * @Date 04/05/2023 - 11:51
 **/

@Service
public class ProfesorServiceImpl implements ProfesorService {

    @Autowired
    private ProfesorRepository profesorRepository;

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    @Autowired
    RolServiceImpl rolService;
    @Autowired
    private EstudianteRepository estudianteRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<Profesor> filtrar(String nombre, String apellido, PageRequest pageable) {

        if (nombre == null) {
            nombre = "";
        }

        if (apellido == null) {
            apellido = "";
        }

        return profesorRepository.filtrarP(nombre, apellido, pageable);

    }

    @Override
    @Transactional
    public Profesor agregarProfesor(Profesor profesor) {

        Optional<Profesor> resultP = profesorRepository.findByCorreo(profesor.getCorreo());
        Optional<Estudiante> resultE = estudianteRepository.findByCorreo(profesor.getCorreo());

        List<Asignatura> asigGuardadas = new ArrayList<>();
        List<Asignatura> asignaturaG = new ArrayList<>();
        List<Asignatura> asigRemover = new ArrayList<>();
        Profesor profesorG = new Profesor();

        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolNombre(RolNombre.ROLE_DOCENTE).get());
        profesor.setRoles(roles);

        asigGuardadas = profesor.getAsignaturas();
        profesor.setAsignaturas(null);

        if (!resultP.isPresent() && !resultE.isPresent()) {
            profesorG = profesorRepository.save(profesor);
            asigGuardadas = settearAsignaturas(asigGuardadas, asigRemover, profesorG);
        } else {
            throw new RuntimeException("El correo del profesor ya existe");
        }
        asignaturaG = asignaturaRepository.saveAll(asigGuardadas);
        profesorG.setAsignaturas(asignaturaG);
        return profesorRepository.save(profesorG);
    }

    private List<Asignatura> settearAsignaturas(List<Asignatura> asigGuardadas, List<Asignatura> asigRemover, Profesor profesorG) {

        for (Asignatura a : asigGuardadas) {
            Optional<Asignatura> asigR;
            Optional<AsignaturaDTO> dto = asignaturaRepository.DtoId(a.getIdasignatura());
            asigR = asignaturaRepository.findById(a.getIdasignatura());

            if (asigR.isPresent()) {
                if (dto.get().getIdusuario() == null) {
                    a.setNombre(asigR.get().getNombre());
                    a.setNotas(asigR.get().getNotas());
                    a.setProfesor(profesorG);
                } else if (Integer.parseInt(dto.get().getIdusuario()) == profesorG.getIdusuario()) {
                    a.setNombre(asigR.get().getNombre());
                    a.setNotas(asigR.get().getNotas());
                    a.setProfesor(profesorG);
                } else {
                    asigRemover.add(a);
                }
            }
        }

        if (asigRemover.size() != 0) {
            for (Asignatura a : asigRemover) {
                if (asigGuardadas.contains(a)) asigGuardadas.remove(a);
            }
        }

        return asigGuardadas;
    }


    @Override
    @Transactional
    public Profesor editarProfesor(Profesor profesor) {

        Optional<Profesor> result = profesorRepository.findById(profesor.getIdusuario());

        List<Asignatura> asigGuardadas = new ArrayList<>();
        List<Asignatura> asignaturaG = new ArrayList<>();
        List<Asignatura> asigRemover = new ArrayList<>();
        List<Asignatura> asigAnteriores = new ArrayList<>();
        List<Asignatura> asigOriginal = new ArrayList<>();
        Profesor profesorG = new Profesor();

        if (result.isPresent()) {

            Optional<Profesor> resultP = profesorRepository.findByCorreo(profesor.getCorreo());
            Optional<Estudiante> resultE = estudianteRepository.findByCorreo(profesor.getCorreo());

            asigOriginal = result.get().getAsignaturas();
            asigGuardadas = profesor.getAsignaturas();
            profesor.setAsignaturas(null);

            if(!resultP.isPresent() && !resultE.isPresent()){
                profesorG = profesorRepository.save(profesor);
                asigGuardadas = settearAsignaturas(asigGuardadas, asigRemover, profesorG);
            } else if (resultP.isPresent() && resultP.get().getIdusuario() == profesor.getIdusuario()) {
                profesorG = profesorRepository.save(profesor);
                asigGuardadas = settearAsignaturas(asigGuardadas, asigRemover, profesorG);
            }else{
                throw new RuntimeException("El correo el profesor a editar ya existe");
            }

            if (asigOriginal != null) {
                for (Asignatura a : asigOriginal) {
                    if (!asigGuardadas.contains(a)) {
                        a.setProfesor(null);
                        asigAnteriores.add(a);
                    }
                }
            }
            asignaturaRepository.saveAll(asigAnteriores);
            asignaturaG = asignaturaRepository.saveAll(asigGuardadas);
            profesorG.setAsignaturas(asignaturaG);
            return profesorRepository.save(profesorG);

        } else {
            throw new RuntimeException("El profesor a editar no existe");
        }
    }

    @Override
    @Transactional
    public boolean eliminarProfesor(Profesor profesor) {

        Optional<Profesor> result = profesorRepository.findById(profesor.getIdusuario());

        if (result.isPresent()) {
            for (Asignatura a : result.get().getAsignaturas()) {
                a.setProfesor(null);
            }
            asignaturaRepository.saveAll(result.get().getAsignaturas());
            profesorRepository.delete(profesor);
            return true;
        } else {
            throw new RuntimeException("El profesor que intenta eliminar no existe");
        }
    }
}
