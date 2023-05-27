package com.natalia.gestionnotas.service;

import com.natalia.gestionnotas.dto.AsignaturaDTO;
import com.natalia.gestionnotas.entity.Asignatura;
import com.natalia.gestionnotas.entity.Nota;
import com.natalia.gestionnotas.repository.AsignaturaRepository;
import com.natalia.gestionnotas.repository.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @Project gestionnotas
 * @Author Sergio Abelardo Rodríguez Vásquez
 * @Email ingsergiorodriguezv@gmail.com
 * @Date 04/05/2023 - 12:01
 **/

@Service
public class AsignaturaServiceImpl implements AsignaturaService {

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    @Autowired
    private NotaRepository notaRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<Asignatura> filtrar(String nombre, PageRequest pageable) {

        if (nombre == null) {
            nombre = "";
        }

        return asignaturaRepository.filtrarP(nombre, pageable);
    }

    @Override
    @Transactional
    public Asignatura agregarAsignatura(Asignatura asignatura) {

        Optional<Asignatura> result = asignaturaRepository.findByNombre(asignatura.getNombre());

        if (!result.isPresent()) {
            return asignaturaRepository.save(asignatura);
        } else {
            throw new RuntimeException("El nombre de la asignatura ya existe");
        }
    }

    @Override
    @Transactional
    public Asignatura editarAsignatura(Asignatura asignatura) {

        Optional<Asignatura> result = asignaturaRepository.findById(asignatura.getIdasignatura());
        List<Nota> notasGuardadas;
        List<Nota> notasG;
        Asignatura asignaturaG;

        if (result.isPresent()) {
            Optional<AsignaturaDTO> dto = asignaturaRepository.DtoId(asignatura.getIdasignatura());
            Optional<Asignatura> result2 = asignaturaRepository.findByNombre(asignatura.getNombre());

            if (!result2.isPresent()) {
                if (dto.get().getIdusuario() == null) {
                    if (result.get().getNotas().size() == 0) {
                        return asignaturaRepository.save(asignatura);
                    }
                    throw new RuntimeException("La asignatura ya tiene notas y no se púede cambiar se nomnbre");
                }
                throw new RuntimeException("La asignatura ya tiene un profesor y no puede cambiarse su nombre");
            } else {
                if (result2.get().getIdasignatura() == asignatura.getIdasignatura()) {
                    if (dto.get().getIdusuario() == null) {
                        if (result.get().getNotas().size() == 0) {
                            return asignaturaRepository.save(asignatura);
                        }
                        throw new RuntimeException("La asignatura ya tiene notas y no se púede cambiar se nomnbre");
                    }
                    throw new RuntimeException("La asignatura ya tiene un profesor y no puede cambiarse su nombre");
                }
                throw new RuntimeException("El nuevo nombre de la asignatura ya existe");
            }
        } else {
            throw new RuntimeException("La asignatura que intenta editar no existe");
        }
    }

    @Override
    @Transactional
    public boolean eliminarAsignatura(Asignatura asignatura) {

        try {
            Optional<Asignatura> result = asignaturaRepository.findById(asignatura.getIdasignatura());

            if (result.isPresent()) {
                Optional<AsignaturaDTO> dto = asignaturaRepository.DtoId(asignatura.getIdasignatura());
                if (dto.get().getIdusuario() == null) {
                    if (result.get().getNotas().size() == 0) {
                        asignaturaRepository.delete(asignatura);
                        return true;
                    }
                    throw new RuntimeException("No se puede eliminar, la asignatura ya contiene notas de estudiantes");
                } else {
                    throw new RuntimeException("No se puede eliminar, la asignatura ya que un profesor tiene asignado a esta materia");
                }
            } else {
                throw new RuntimeException("La asignatura que intenta eliminar no existe");
            }
        } catch (Exception ce) {
            throw new RuntimeException(ce.getMessage());
        }
    }
}
