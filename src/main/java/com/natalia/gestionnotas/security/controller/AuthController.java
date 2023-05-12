package com.natalia.gestionnotas.security.controller;

import com.natalia.gestionnotas.entity.Usuario;
import com.natalia.gestionnotas.security.dto.JwtDto;
import com.natalia.gestionnotas.security.dto.LoginUsuario;
import com.natalia.gestionnotas.security.jwt.JwtProvider;
import com.natalia.gestionnotas.security.service.RolServiceImpl;
import com.natalia.gestionnotas.security.service.UsuarioService;
import com.natalia.gestionnotas.utils.ResponseGeneral;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioService usuarioService;


    @Autowired
    RolServiceImpl rolService;

    @Autowired
    JwtProvider jwtProvider;

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
        }catch (Exception e){
            response.setData(null);
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/agregarAdministrador")
    public ResponseEntity<ResponseGeneral<Usuario>> agregarAdministrador(@RequestBody Usuario admin){

        ResponseGeneral<Usuario> response = new ResponseGeneral<>();
        Usuario nuevoEstudiante;
        HttpStatus status = HttpStatus.OK;

        admin.setContrasenia(passwordEncoder.encode(admin.getContrasenia()));

        nuevoEstudiante = usuarioService.agregarAdministrador(admin);

        if(nuevoEstudiante != null){
            response.setData(nuevoEstudiante);
            response.setSuccess(true);
            response.setMessage("Administrador agregado con exito");
        }else{
            response.setData(null);
            response.setSuccess(false);
            response.setMessage("No se pudo agregar o ya existe el administrador");
        }

        return new ResponseEntity<>(response, status);
    }
}
