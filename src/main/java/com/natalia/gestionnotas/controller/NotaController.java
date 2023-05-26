package com.natalia.gestionnotas.controller;

import com.natalia.gestionnotas.dto.NotasDTO;
import com.natalia.gestionnotas.entity.Nota;
import com.natalia.gestionnotas.service.NotaService;
import com.natalia.gestionnotas.utils.ResponseGeneral;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping("/verificar")
    public ResponseEntity<ResponseGeneral<NotasDTO>> verificar(
            @RequestParam(value = "idusuario", required = true) int idusuario,
            @RequestParam(value = "idasignatura", required = true) int idasignatura
    ){
        ResponseGeneral<NotasDTO> response = new ResponseGeneral<>();
        NotasDTO data;

        data = notaService.verficar(idusuario, idasignatura);

        if (data != null) {
            response.setData(data);
            response.setMessage("Este usuario ya tiene una nota en la asignatura");
            response.setSuccess(false);
        } else {
            response.setData(null);
            response.setMessage("Este estudiante aun no tiene nota");
            response.setSuccess(true);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('PROFESOR') or hasRole('ESTUDIANTE')")
    @GetMapping("/filtrar")
    public ResponseEntity<ResponseGeneral<Page<NotasDTO>>> filtrar(
            @RequestParam(value = "nombre", required = false) String nombre,
            @RequestParam(value = "apellido", required = false) String apellido,
            @RequestParam(value = "materia", required = false) String materia,
            @RequestParam(value = "pagina", defaultValue = "0", required = false) int pagina,
            @RequestParam(value = "cantPagina", defaultValue = "10", required = false) int cantPagina
    ) {

        ResponseGeneral<Page<NotasDTO>> response = new ResponseGeneral<>();
        Page<NotasDTO> data;

        try {
            PageRequest pageable = PageRequest.of(pagina, cantPagina);

            data = notaService.filtrar(nombre, apellido, materia, pageable);

            if (data == null) {
                response.setData(null);
                response.setMessage("Error al obtener la lista");
                response.setSuccess(false);
            } else {
                if (data.getContent().size() == 0) {
                    response.setData(data);
                    response.setMessage("La lista de notas esta vacia");
                    response.setSuccess(false);
                } else {
                    response.setData(data);
                    response.setMessage("Lista de notas obtenida con exito");
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

    @PreAuthorize("hasRole('ADMIN') or hasRole('PROFESOR')")
    @PostMapping
    public ResponseEntity<ResponseGeneral<Nota>> agregarNota(
            @RequestBody Nota nota
    ) {

        ResponseGeneral<Nota> response = new ResponseGeneral<>();
        Nota data;

        try {

            data = notaService.agregarNota(nota);

            if (data == null) {
                response.setData(null);
                response.setMessage("No es posible agregar la nota");
                response.setSuccess(false);
            } else {
                response.setData(data);
                response.setMessage("Nota agregada con exito");
                response.setSuccess(true);
            }
        } catch (Exception e) {
            response.setData(null);
            response.setMessage(e.getMessage());
            response.setSuccess(false);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('PROFESOR')")
    @PutMapping
    public ResponseEntity<ResponseGeneral<Nota>> editarNota(
            @RequestBody Nota nota
    ) {

        ResponseGeneral<Nota> response = new ResponseGeneral();
        Nota data;

        try {

            data = notaService.editarNota(nota);

            if (data == null) {
                response.setData(null);
                response.setMessage("No es posible editar la nota");
                response.setSuccess(false);
            } else {
                response.setData(data);
                response.setMessage("Nota editada con exito");
                response.setSuccess(true);
            }

        } catch (Exception e) {
            response.setData(null);
            response.setMessage(e.getMessage());
            response.setSuccess(false);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('PROFESOR')")
    @DeleteMapping
    private ResponseEntity<ResponseGeneral<Boolean>> eliminarNota(
            @RequestBody Nota nota
    ) {
        ResponseGeneral<Boolean> response = new ResponseGeneral<>();
        Boolean data;

        try {

            data = notaService.eliminarNota(nota);

            if (data == false) {
                response.setData(null);
                response.setMessage("No es posible eliminar la nota");
                response.setSuccess(false);
            } else {
                response.setData(true);
                response.setMessage("Nota eliminada con exito");
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
