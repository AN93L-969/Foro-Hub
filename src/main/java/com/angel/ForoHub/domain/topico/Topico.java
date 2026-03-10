package com.angel.ForoHub.domain.topico;

import com.angel.ForoHub.domain.curso.Curso;
import com.angel.ForoHub.domain.respuesta.Respuesta;
import com.angel.ForoHub.domain.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "Topico")
@Table(name = "topicos")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String mensaje;

    @Column(nullable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Column(nullable = false)
    private String status = "ABIERTO";

    @ManyToOne
    private Usuario autor;

    @ManyToOne
    private Curso curso;

    @OneToMany
    private List<Respuesta> respuestas;

    public Topico(DatosRegistroTopico datos, Usuario autor, Curso curso) {
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
        this.autor = autor;
        this.curso = curso;
    }

    public void actualizarInformaciones(@Valid DatosActualizacionTopico datos) {
        if (datos.mensaje() != null) {
            this.mensaje = datos.mensaje();
        }
        if (datos.nombreCurso() != null) {
            this.curso = new Curso();
        }
        if (datos.titulo() != null) {
            this.titulo =  datos.titulo();
        }
    }
}
