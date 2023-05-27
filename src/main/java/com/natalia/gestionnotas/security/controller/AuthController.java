package com.natalia.gestionnotas.security.controller;

import com.natalia.gestionnotas.entity.Estudiante;
import com.natalia.gestionnotas.entity.Profesor;
import com.natalia.gestionnotas.entity.Usuario;
import com.natalia.gestionnotas.security.dto.JwtDto;
import com.natalia.gestionnotas.security.dto.LoginUsuario;
import com.natalia.gestionnotas.security.jwt.JwtProvider;
import com.natalia.gestionnotas.security.service.RolServiceImpl;
import com.natalia.gestionnotas.security.service.UsuarioService;
import com.natalia.gestionnotas.service.EstudianteService;
import com.natalia.gestionnotas.service.ProfesorService;
import com.natalia.gestionnotas.utils.ResponseGeneral;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;

/**
 * @Project gestionnotas
 * @Author Sergio Abelardo Rodríguez Vásquez
 * @Email ingsergiorodriguezv@gmail.com
 * @Date 09/05/2023 - 15:11
 **/

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    private final PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EstudianteService estudianteService;

    @Autowired
    private ProfesorService profesorService;

    @Autowired
    private RolServiceImpl rolService;

    @Autowired
    private JwtProvider jwtProvider;

    public AuthController(PasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseGeneral<?>> login(@Valid @RequestBody LoginUsuario loginUsuario){

        ResponseGeneral<JwtDto> response = new ResponseGeneral<>();

        try {
            Authentication authentication =
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(loginUsuario.getCorreo(), loginUsuario.getContrasenia()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            if (jwtProvider.validateCorreo(authentication, loginUsuario.getCorreo())) {
                String jwt = jwtProvider.generateToken(authentication);
                JwtDto jwtDto = new JwtDto(jwt);

                response.setData(jwtDto);
                response.setSuccess(true);
                response.setMessage("Acceso concedido");
            } else {
                response.setData(null);
                response.setSuccess(false);
                response.setMessage("Acceso no concedido, el nombre no es valido");
            }
        }catch (BadCredentialsException bce){
            response.setData(null);
            response.setSuccess(false);
            response.setMessage("El correo o la contraseña no coinciden");
        } catch (Exception e){
            response.setData(null);
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/refresh")
    public ResponseEntity<ResponseGeneral<?>> refresh(@RequestBody JwtDto jwtDto){
        ResponseGeneral<JwtDto> response = new ResponseGeneral<>();

        try {
            String token = jwtProvider.refreshToken(jwtDto);

            JwtDto jwt = new JwtDto(token);

            response.setData(jwt);
            response.setSuccess(true);
            response.setMessage("Acceso concedido");
        }catch (ParseException pe){
            response.setData(null);
            response.setSuccess(true);
            response.setMessage(pe.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/agregarAdministrador")
    public ResponseEntity<ResponseGeneral<Usuario>> agregarAdministrador(@RequestBody Usuario admin){

        ResponseGeneral<Usuario> response = new ResponseGeneral<>();
        Usuario data;
        HttpStatus status = HttpStatus.OK;

        try {
            admin.setContrasenia(bCryptPasswordEncoder.encode(admin.getContrasenia()));
            data = usuarioService.agregarAdministrador(admin);

            if (data != null) {
                response.setData(data);
                response.setSuccess(true);
                response.setMessage("Administrador agregado con exito");
            } else {
                response.setData(null);
                response.setSuccess(false);
                response.setMessage("No se pudo agregar o ya existe el administrador");
            }
        }catch (Exception e){
            response.setData(null);
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }

        return new ResponseEntity<>(response, status);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/estudiante")
    public ResponseEntity<ResponseGeneral<Estudiante>> agregarEstudiante(
            @RequestBody Estudiante estudiante) {

        ResponseGeneral<Estudiante> response = new ResponseGeneral<>();
        Estudiante data;

        try {

            estudiante.setContrasenia(bCryptPasswordEncoder.encode(estudiante.getContrasenia()));
            data = estudianteService.agregarEstudiante(estudiante);

            if (data == null) {
                response.setData(null);
                response.setMessage("No es posible agregar al estudiante");
                response.setSuccess(false);
            } else {
                response.setData(data);
                response.setMessage("Estudiante agregado con exito");
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
    @PutMapping("/estudiante")
    public ResponseEntity<ResponseGeneral<Estudiante>> editarEstudiante(
            @RequestBody Estudiante estudiante) {

        ResponseGeneral<Estudiante> response = new ResponseGeneral<>();
        Estudiante data;

        try {
            estudiante.setContrasenia(bCryptPasswordEncoder.encode(estudiante.getContrasenia()));
            data  = estudianteService.editarEstudiante(estudiante);

            if (data == null) {
                response.setData(null);
                response.setMessage("No es posible editar al estudiante");
                response.setSuccess(false);
            } else {
                response.setData(data);
                response.setMessage("Estudiante editado con exito");
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
    @PostMapping("/profesor")
    public ResponseEntity<ResponseGeneral<Profesor>> agregarProfesor(
            @RequestBody Profesor profesor) {

        ResponseGeneral<Profesor> response = new ResponseGeneral<>();
        Profesor data;

        try {
            profesor.setContrasenia(bCryptPasswordEncoder.encode(profesor.getContrasenia()));
            data = profesorService.agregarProfesor(profesor);

            if (data == null) {
                response.setData(null);
                response.setMessage("No es posible agregar al profesor");
                response.setSuccess(false);
            } else {
                response.setData(data);
                response.setMessage("Profesor agregado con exito");
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
    @PutMapping("/profesor")
    public ResponseEntity<ResponseGeneral<Profesor>> editarProfesor(
            @RequestBody Profesor profesor) {

        ResponseGeneral<Profesor> response = new ResponseGeneral<>();
        Profesor data;

        try {
            profesor.setContrasenia(bCryptPasswordEncoder.encode(profesor.getContrasenia()));
            data = profesorService.editarProfesor(profesor);

            if (data == null) {
                response.setData(null);
                response.setMessage("No es posible editar al profesor");
                response.setSuccess(false);
            } else {
                response.setData(data);
                response.setMessage("Profesor editado con exito");
                response.setSuccess(true);
            }

        } catch (Exception e) {
            response.setData(null);
            response.setMessage(e.getMessage());
            response.setSuccess(false);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
