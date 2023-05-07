package com.natalia.gestionnotas.controller;

import com.natalia.gestionnotas.entity.Profesor;
import com.natalia.gestionnotas.service.ProfesorService;
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
 * @Date 04/05/2023 - 11:52
 **/

@RestController
@RequestMapping("/profesor")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ProfesorController {

    @Autowired
    private ProfesorService profesorService;

    @GetMapping("/filtrar")
    public ResponseEntity<Page<Profesor>> filtrar(
            @RequestParam(value = "nombre", required = false) String nombre,
            @RequestParam(value = "apellido", required = false) String apellido,
            @RequestParam(value = "pagina", defaultValue = "0", required = false) int pagina,
            @RequestParam(value = "cantPagina", defaultValue = "10", required = false) int cantPagina
    ) {

        Page<Profesor> data;
        try {
            PageRequest pageable = PageRequest.of(pagina, cantPagina);

            data = profesorService.filtrar(nombre, apellido, pageable);

        } catch (Exception e) {
            data = null;
        }

        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<Profesor> agregarProfesor(
            @RequestBody Profesor profesor) {

        Profesor data = profesorService.agregarProfesor(profesor);

        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PutMapping
    private ResponseEntity<Profesor> editarProfesor(
            @RequestBody Profesor profesor) {

        Profesor data = profesorService.editarProfesor(profesor);

        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @DeleteMapping
    private ResponseEntity<Boolean> eliminarProfesor(
            @RequestBody Profesor profesor){

        boolean data = profesorService.eliminarProfesor(profesor);

        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
