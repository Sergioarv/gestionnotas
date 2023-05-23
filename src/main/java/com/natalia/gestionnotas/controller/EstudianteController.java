package com.natalia.gestionnotas.controller;

import com.natalia.gestionnotas.entity.Estudiante;
import com.natalia.gestionnotas.service.EstudianteService;
import com.natalia.gestionnotas.utils.ResponseGeneral;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Project gestionnotas
 * @Author Sergio Abelardo Rodríguez Vásquez
 * @Email ingsergiorodriguezv@gmail.com
 * @Date 02/05/2023 - 15:44
 **/

@RestController
@RequestMapping("/estudiante")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping
    public ResponseEntity<ResponseGeneral<List<Estudiante>>> listar(){
        ResponseGeneral<List<Estudiante>> response = new ResponseGeneral<>();
        List<Estudiante> data;

        data = estudianteService.listar();

        if (data == null) {
            response.setData(null);
            response.setMessage("Erro al obtener la lista");
            response.setSuccess(false);
        } else {
            if (data.size() == 0) {
                response.setData(data);
                response.setMessage("La lista de estudiantes esta vacia");
                response.setSuccess(false);
            } else {
                response.setData(data);
                response.setMessage("Lista de estudiantes obtenida con exito");
                response.setSuccess(true);
            }
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/filtrar")
    public ResponseEntity<ResponseGeneral<Page<Estudiante>>> filtrar(
            @RequestParam(value = "nombre", required = false) String nombre,
            @RequestParam(value = "apellido", required = false) String apellido,
            @RequestParam(value = "pagina", defaultValue = "0", required = false) int pagina,
            @RequestParam(value = "cantPagina", defaultValue = "10", required = false) int cantPagina

    ) {
        ResponseGeneral<Page<Estudiante>> response = new ResponseGeneral<>();
        Page<Estudiante> data;

        try {

            PageRequest pageable = PageRequest.of(pagina, cantPagina);

            data = estudianteService.filtrar(nombre, apellido, pageable);

            if (data == null) {
                response.setData(null);
                response.setMessage("Erro al obtener la lista");
                response.setSuccess(false);
            } else {
                if (data.getContent().size() == 0) {
                    response.setData(data);
                    response.setMessage("La lista de estudiantes esta vacia");
                    response.setSuccess(false);
                } else {
                    response.setData(data);
                    response.setMessage("Lista de estudiantes obtenida con exito");
                    response.setSuccess(true);
                }
            }

        } catch (Exception e) {
            response.setData(null);
            response.setMessage(e.getMessage());
            response.setSuccess(false);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<ResponseGeneral<Estudiante>> agregarEstudiante(
            @RequestBody Estudiante estudiante) {

        ResponseGeneral<Estudiante> response = new ResponseGeneral();
        Estudiante data;

        try {

            estudiante.setContrasenia(passwordEncoder.encode(estudiante.getContrasenia()));

            data = estudianteService.agregarEstudiante(estudiante);

            if (data == null) {
                response.setData(null);
                response.setMessage("No es posible agregar al estudiante");
                response.setSuccess(false);
            } else {
                response.setData(data);
                response.setMessage("Estudiante agregado con exito");
                response.setSuccess(true);
            }
        } catch (Exception e) {
            response.setData(null);
            response.setMessage(e.getMessage());
            response.setSuccess(false);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping
    private ResponseEntity<ResponseGeneral<Estudiante>> editarEstudiante(
            @RequestBody Estudiante estudiante) {

        ResponseGeneral<Estudiante> response = new ResponseGeneral<>();
        Estudiante data;

        try {
            estudiante.setContrasenia(passwordEncoder.encode(estudiante.getContrasenia()));
            data  = estudianteService.editarEstudiante(estudiante);

            if (data == null) {
                response.setData(null);
                response.setMessage("No es posible editar al estudiante");
                response.setSuccess(false);
            } else {
                response.setData(data);
                response.setMessage("Estudiante editado con exito");
                response.setSuccess(true);
            }

        } catch (Exception e) {
            response.setData(null);
            response.setMessage(e.getMessage());
            response.setSuccess(false);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping
    private ResponseEntity<ResponseGeneral<Boolean>> eliminarEstudiante(
            @RequestBody Estudiante estudiante) {

        ResponseGeneral<Boolean> response = new ResponseGeneral();
        boolean data;

        try {
            data = estudianteService.eliminarEstudiante(estudiante);

            if (data == false) {
                response.setData(null);
                response.setMessage("No es posible eliminar al estudiante");
                response.setSuccess(false);
            } else {
                response.setData(true);
                response.setMessage("Estudiante eliminado con exito");
                response.setSuccess(true);
            }

        } catch (Exception e) {
            response.setData(false);
            response.setMessage(e.getMessage());
            response.setSuccess(false);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
