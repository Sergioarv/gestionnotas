package com.natalia.gestionnotas.controller;

import com.natalia.gestionnotas.entity.Profesor;
import com.natalia.gestionnotas.service.ProfesorService;
import com.natalia.gestionnotas.utils.ResponseGeneral;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/filtrar")
    public ResponseEntity<ResponseGeneral<Page<Profesor>>> filtrar(
            @RequestParam(value = "nombre", required = false) String nombre,
            @RequestParam(value = "apellido", required = false) String apellido,
            @RequestParam(value = "pagina", defaultValue = "0", required = false) int pagina,
            @RequestParam(value = "cantPagina", defaultValue = "10", required = false) int cantPagina
    ) {

        ResponseGeneral<Page<Profesor>> response = new ResponseGeneral<>();
        Page<Profesor> data;

        try {

            PageRequest pageable = PageRequest.of(pagina, cantPagina);

            data = profesorService.filtrar(nombre, apellido, pageable);

            if (data == null) {
                response.setData(null);
                response.setMessage("Erro al obtener la lista");
                response.setSuccess(false);
            } else {
                if (data.getContent().size() == 0) {
                    response.setData(data);
                    response.setMessage("La lista de profesores esta vacia");
                    response.setSuccess(false);
                } else {
                    response.setData(data);
                    response.setMessage("Lista de profesores obtenida con exito");
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


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping
    public ResponseEntity<ResponseGeneral<Boolean>> eliminarProfesor(
            @RequestBody Profesor profesor) {

        ResponseGeneral<Boolean> response = new ResponseGeneral<>();
        boolean data;

        try {

            data = profesorService.eliminarProfesor(profesor);

            if (data == false) {
                response.setData(null);
                response.setMessage("No es posible eliminar al profesor");
                response.setSuccess(false);
            } else {
                response.setData(true);
                response.setMessage("Profesor eliminado con exito");
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
