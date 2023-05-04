package com.natalia.gestionnotas.controller;

import com.natalia.gestionnotas.entity.Estudiante;
import com.natalia.gestionnotas.entity.Profesor;
import com.natalia.gestionnotas.service.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<Profesor>> filtrar(
            @RequestParam(value = "nombre", required = false) String nombre,
            @RequestParam(value = "apellido", required = false) String apellido
    ) {
        List<Profesor> data = profesorService.filtrar(nombre, apellido);

        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<Profesor> agregarProfesor(
            @RequestBody Profesor profesor) {

        Profesor nuevoEstudiante;

        Profesor data = profesorService.agregarProfesor(profesor);

        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
