package com.angel.ForoHub.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizacionTopico(@NotNull Long id_usuario,
                                       @NotBlank String mensaje,
                                       @NotBlank String nombreCurso,
                                       @NotBlank String titulo) {
}
