package com.natalia.gestionnotas.controller;

import com.natalia.gestionnotas.entity.Asignatura;
import com.natalia.gestionnotas.entity.Estudiante;
import com.natalia.gestionnotas.service.AsignaturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public ResponseEntity<Page<Asignatura>> filtrar(
            @RequestParam(value = "nombre", required = false) String nombre,
            @RequestParam(value = "pagina", defaultValue = "0", required = false) int pagina,
            @RequestParam(value = "cantPagina", defaultValue = "10", required = false) int cantPagina

    ) {
        Page<Asignatura> data;
        try {
            PageRequest pageable = PageRequest.of(pagina, cantPagina);

            data = asignaturaService.filtrar(nombre, pageable);

        } catch (Exception e) {
            data = null;
        }

        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<Asignatura> agregarAsignatura(
            @RequestBody Asignatura asignatura) {

        Asignatura data = asignaturaService.agregarAsignatura(asignatura);

        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PutMapping
    private ResponseEntity<Asignatura> editarAsignatura(
            @RequestBody Asignatura asignatura) {

        Asignatura data = asignaturaService.editarAsignatura(asignatura);

        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @DeleteMapping
    private ResponseEntity<Boolean> eliminarAsignatura(
            @RequestBody Asignatura asignatura){

        boolean data = asignaturaService.eliminarAsignatura(asignatura);

        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
