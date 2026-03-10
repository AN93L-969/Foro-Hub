package com.angel.ForoHub.controller;

import com.angel.ForoHub.domain.infra.security.DatosTokenJWT;
import com.angel.ForoHub.domain.infra.security.TokenService;
import com.angel.ForoHub.domain.usuario.DatosAutenticacion;
import com.angel.ForoHub.domain.usuario.Usuario;
import com.angel.ForoHub.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AutenticacionController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private UsuarioRepository repository;

    @PostMapping
    public ResponseEntity iniciarSesion (@RequestBody @Valid DatosAutenticacion datos) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(datos.correoElectronico(), datos.contrasenia());
        var autenticacion = manager.authenticate(authenticationToken);

        var tokenJWT = tokenService.generarToken((Usuario) autenticacion.getPrincipal());

        Optional<Usuario> usuarioBuscado = repository.findByCorreoElectronico(datos.correoElectronico());

        if (!usuarioBuscado.isPresent()) {
            repository.save(usuarioBuscado.get());
        }

        return ResponseEntity.ok(new DatosTokenJWT(tokenJWT));
    }
}
