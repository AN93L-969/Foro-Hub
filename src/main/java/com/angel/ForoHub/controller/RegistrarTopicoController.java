package com.angel.ForoHub.controller;

import com.angel.ForoHub.domain.topico.DatosRegistroTopico;
import com.angel.ForoHub.domain.topico.Topico;
import com.angel.ForoHub.domain.topico.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topicos")
public class RegistrarTopicoController {

    @Autowired
    private TopicoRepository repository;

    @PostMapping
    public void registrarTopico(@RequestBody DatosRegistroTopico datos){
        System.out.println("Topico Registrado!!\n " + datos);
        repository.save(new Topico(datos));
    }
}
