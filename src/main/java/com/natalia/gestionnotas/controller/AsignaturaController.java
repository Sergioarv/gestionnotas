package com.natalia.gestionnotas.controller;

import com.natalia.gestionnotas.entity.Asignatura;
import com.natalia.gestionnotas.service.AsignaturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Project gestionnotas
 * @Author Sergio Abelardo Rodríguez Vásquez
 * @Email ingsergiorodriguezv@gmail.com
 * @Date 04/05/2023 - 12:02
 **/

@RestController
@RequestMapping("/asignatura")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class AsignaturaController {

    @Autowired
    private AsignaturaService asignaturaService;

    @GetMapping("/filtrar")
    public ResponseEntity<List<Asignatura>> filtrar(

    ) {
        List<Asignatura> data = asignaturaService.filtrar();

        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<Asignatura> agregarEstudiante(
            @RequestBody Asignatura asignatura) {

        Asignatura nuevoAsignatura;

        Asignatura data = asignaturaService.agregarAsignatura(asignatura);

        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
