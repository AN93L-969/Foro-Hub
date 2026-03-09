package com.angel.ForoHub.controller;

import com.angel.ForoHub.domain.topico.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @Transactional
    @PostMapping
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroTopico datos){
        System.out.println("Topico Registrado!!\n " + datos);

//        if (repository.existsByTitulo(datos.titulo()) || repository.existsByMensaje(datos.mensaje())){
//            return ResponseEntity.badRequest().build();
//        }else {
//            repository.save(new Topico(datos));
            return  ResponseEntity.ok().build();
//        }
    }

    @GetMapping
    public ResponseEntity<Page<DatosListaTopico>> listar (@PageableDefault(size = 10, sort = {"titulo"})Pageable paginacion) {
        //var page = repository.findAll(paginacion).map(DatosListaTopico::new);
        //return ResponseEntity.ok(page);
        return null; //Quitar linea al eliminar los comentarios anteriores
    }

    @GetMapping("/{id}")
    public ResponseEntity detallar (@PathVariable Long id) {
//        var topico = repository.getReferenceById(id);
//
//        return ResponseEntity.ok(new DatosDetalleTopico(topico));
        return null; //Quitar linea al eliminar los comentarios anteriores
    }

    @Transactional
    @PutMapping
    public ResponseEntity actualizar (@RequestBody @Valid DatosActualizacionTopico datos) {
//        var topico = repository.getReferenceById(datos.id_usuario());
//        topico.actualizarInformaciones(datos);
//
//        return ResponseEntity.ok(new DatosDetalleTopico(topico));
        return null; //Quitar linea al eliminar los comentarios anteriores
    }
}
