package com.natalia.gestionnotas.controller;

import com.natalia.gestionnotas.dto.NotasDTO;
import com.natalia.gestionnotas.entity.Estudiante;
import com.natalia.gestionnotas.entity.Nota;
import com.natalia.gestionnotas.service.NotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Project gestionnotas
 * @Author Sergio Abelardo Rodríguez Vásquez
 * @Email ingsergiorodriguezv@gmail.com
 * @Date 04/05/2023 - 12:48
 **/

@RestController
@RequestMapping("/nota")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class NotaController {

    @Autowired
    private NotaService notaService;

    @GetMapping("/filtrar")
    public ResponseEntity<List<NotasDTO>> filtrar(
            @RequestParam(value = "nombre", required = false) String nombre,
            @RequestParam(value = "apellido", required = false) String apellido,
            @RequestParam(value = "materia", required = false) String materia,
            @RequestParam(value = "pagina", defaultValue = "0", required = false) int pagina,
            @RequestParam(value = "cantPagina", defaultValue = "10", required = false) int cantPagina
    ) {

        Pageable pageable = PageRequest.of(pagina, cantPagina);

        List<NotasDTO> data = notaService.filtrar(nombre, apellido, materia, pageable);

        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<Nota> agregarNota(
            @RequestBody Nota nota
    ) {
        Nota data = notaService.agregarNota(nota);

        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PutMapping
    private ResponseEntity<Nota> editarNota(
            @RequestBody Nota nota
    ) {
        Nota data = notaService.editarNota(nota);

        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @DeleteMapping
    private ResponseEntity<Boolean> eliminarNota(
            @RequestBody Nota nota
    ) {
        Boolean data;

        data = notaService.eliminarNota(nota);

        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
