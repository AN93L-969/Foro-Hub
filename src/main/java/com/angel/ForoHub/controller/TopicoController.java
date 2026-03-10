package com.angel.ForoHub.controller;

import com.angel.ForoHub.domain.curso.Curso;
import com.angel.ForoHub.domain.curso.CursoRepository;
import com.angel.ForoHub.domain.topico.*;
import com.angel.ForoHub.domain.usuario.Usuario;
import com.angel.ForoHub.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Transactional
    @PostMapping
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroTopico datos){
        System.out.println("Topico Registrado!!\n " + datos);

        if (topicoRepository.existsByTitulo(datos.titulo()) || topicoRepository.existsByMensaje(datos.mensaje())){
            return ResponseEntity.badRequest().build();
        }else {
            Usuario autor = usuarioRepository.findById(datos.id_usuario())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            Curso curso = cursoRepository.findByNombre(datos.nombreCurso());

            topicoRepository.save(new Topico(datos, autor, curso));
            return  ResponseEntity.ok().build();
        }
    }

    @GetMapping
    public ResponseEntity<Page<DatosListaTopico>> listar (@PageableDefault(size = 10, sort = {"titulo"})Pageable paginacion) {
        var page = topicoRepository.findAll(paginacion).map(DatosListaTopico::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detallar (@PathVariable Long id) {
        //var topico = topicoRepository.getReferenceById(id);

        Optional<Topico> topicoBuscado = topicoRepository.findById(id);

        if (topicoBuscado.isPresent()) {
            return ResponseEntity.ok(new DatosDetalleTopico(topicoBuscado.get()));
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @Transactional
    @PutMapping
    public ResponseEntity actualizar (@RequestBody @Valid DatosActualizacionTopico datos) {
        var topico = topicoRepository.getReferenceById(datos.id_topico());
        topico.actualizarInformaciones(datos);

        return ResponseEntity.ok(new DatosDetalleTopico(topico));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminar (@PathVariable Long id) {
        //var topico = topicoRepository.getReferenceById(id);

        Optional<Topico> topicoBuscado = topicoRepository.findById(id);

        if (topicoBuscado.isPresent()) {
            topicoRepository.deleteById(topicoBuscado.get().getId());
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
