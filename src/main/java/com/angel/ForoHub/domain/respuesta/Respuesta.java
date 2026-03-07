package com.angel.ForoHub.domain.respuesta;

import com.angel.ForoHub.domain.topico.Topico;
import com.angel.ForoHub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "Respuesta")
@Table(name = "respuestas")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mensaje;

    @ManyToOne
    private Topico topico;

    private LocalDateTime fechaCreacion;

    @ManyToOne
    private Usuario autor;

    private String solucion;
}
