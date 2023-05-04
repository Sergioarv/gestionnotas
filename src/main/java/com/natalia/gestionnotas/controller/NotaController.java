package com.natalia.gestionnotas.controller;

import com.natalia.gestionnotas.entity.Estudiante;
import com.natalia.gestionnotas.entity.Nota;
import com.natalia.gestionnotas.service.NotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    private ResponseEntity<Nota> agregarNota(
            @RequestBody Nota nota
    ){

        Nota data = notaService.agregarNota(nota);

        return new ResponseEntity<>(data, HttpStatus.OK);

    }
}
