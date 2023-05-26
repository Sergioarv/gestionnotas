package com.natalia.gestionnotas.controller;

import com.natalia.gestionnotas.entity.Asignatura;
import com.natalia.gestionnotas.service.AsignaturaService;
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
 * @Date 04/05/2023 - 12:02
 **/

@RestController
@RequestMapping("/asignatura")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class AsignaturaController {

    @Autowired
    private AsignaturaService asignaturaService;

    @PreAuthorize("hasRole('ADMIN') or hasRole('PROFESOR') or hasRole('ESTUDIANTE')")
    @GetMapping("/filtrar")
    public ResponseEntity<ResponseGeneral<Page<Asignatura>>> filtrar(
            @RequestParam(value = "nombre", required = false) String nombre,
            @RequestParam(value = "pagina", defaultValue = "0", required = false) int pagina,
            @RequestParam(value = "cantPagina", defaultValue = "10", required = false) int cantPagina
    ) {

        ResponseGeneral<Page<Asignatura>> response = new ResponseGeneral<>();
        Page<Asignatura> data;

        try {

            PageRequest pageable = PageRequest.of(pagina, cantPagina);

            data = asignaturaService.filtrar(nombre, pageable);

            if (data == null) {
                response.setData(null);
                response.setMessage("Erro al obtener la lista");
                response.setSuccess(false);
            } else {
                if(data.getContent().size() == 0){
                    response.setData(data);
                    response.setMessage("La lista de asignaturas esta vacia");
                    response.setSuccess(false);
                }else{
                    response.setData(data);
                    response.setMessage("Lista de asignaturas obtenida con exito");
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
    @PostMapping
    public ResponseEntity<ResponseGeneral<Asignatura>> agregarAsignatura(
            @RequestBody Asignatura asignatura) {

        ResponseGeneral<Asignatura> response = new ResponseGeneral<>();
        Asignatura data;

        try {

            data = asignaturaService.agregarAsignatura(asignatura);

            if (data == null) {
                response.setData(null);
                response.setMessage("No es posible agregar la asignatura");
                response.setSuccess(false);
            } else {
                response.setData(data);
                response.setMessage("Asignatura agregada con exito");
                response.setSuccess(true);
            }
        } catch (Exception e) {
            response.setData(null);
            response.setMessage(e.getMessage());
            response.setSuccess(false);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public ResponseEntity<ResponseGeneral<Asignatura>> editarAsignatura(
            @RequestBody Asignatura asignatura) {

        ResponseGeneral<Asignatura> response = new ResponseGeneral<>();
        Asignatura data;

        try {
            data = asignaturaService.editarAsignatura(asignatura);

            if (data == null) {
                response.setData(null);
                response.setMessage("No es posible editar la asignatura");
                response.setSuccess(false);
            } else {
                response.setData(data);
                response.setMessage("Asignatura editada con exito");
                response.setSuccess(true);
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
    public ResponseEntity<ResponseGeneral<Boolean>> eliminarAsignatura(
            @RequestBody Asignatura asignatura) {

        ResponseGeneral<Boolean> response = new ResponseGeneral<>();
        boolean data;

        try {
            data = asignaturaService.eliminarAsignatura(asignatura);

            if (data == false) {
                response.setData(null);
                response.setMessage("No es posible eliminar la asignatura");
                response.setSuccess(false);
            } else {
                response.setData(true);
                response.setMessage("Asignatura eliminado con exito");
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
