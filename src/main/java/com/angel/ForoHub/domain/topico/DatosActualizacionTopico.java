package com.angel.ForoHub.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizacionTopico(@NotNull Long id_topico,
                                       String mensaje,
                                       String nombreCurso,
                                       String titulo) {
}
