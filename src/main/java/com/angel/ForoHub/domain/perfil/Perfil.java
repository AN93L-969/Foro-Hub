package com.angel.ForoHub.domain.perfil;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Perfil")
@Table(name = "perfiles")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String nombre;
}
