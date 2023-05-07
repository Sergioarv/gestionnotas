package com.natalia.gestionnotas.controller;

import com.natalia.gestionnotas.entity.Estudiante;
import com.natalia.gestionnotas.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/filtrar")
    public ResponseEntity<Page<Estudiante>> filtrar(
            @RequestParam(value = "nombre", required = false) String nombre,
            @RequestParam(value = "apellido", required = false) String apellido,
            @RequestParam(value = "pagina", defaultValue = "0", required = false) int pagina,
            @RequestParam(value = "cantPagina", defaultValue = "10", required = false) int cantPagina

    ) {
        Page<Estudiante> data;
        try {
            PageRequest pageable = PageRequest.of(pagina, cantPagina);

            data = estudianteService.filtrar(nombre, apellido, pageable);

        } catch (Exception e) {
            data = null;
        }

        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<Estudiante> agregarEstudiante(
            @RequestBody Estudiante estudiante) {

        Estudiante nuevoEstudiante;

        Estudiante data = estudianteService.agregarEstudiante(estudiante);

        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PutMapping
    private ResponseEntity<Estudiante> editarEstudiante(
            @RequestBody Estudiante estudiante) {

        Estudiante data = estudianteService.editarEstudiante(estudiante);

        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @DeleteMapping
    private ResponseEntity<Boolean> eliminarEstudiante(
            @RequestBody Estudiante estudiante){

        boolean data = estudianteService.eliminarEstudiante(estudiante);

        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
