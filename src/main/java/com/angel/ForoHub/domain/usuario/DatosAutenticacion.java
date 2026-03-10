package com.angel.ForoHub.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record DatosAutenticacion(@NotBlank String nombre,
                                 @NotBlank String correoElectronico,
                                 @NotBlank String contrasenia) {
}
