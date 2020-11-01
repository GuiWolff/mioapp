package com.miotec.mioapp.exercicios;

import com.miotec.mioapp.usuarios.Usuario;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Exercicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate data_execicio;
    private Double nota_avaliativa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_usuario", nullable = true)
    private Usuario usuario;

}
