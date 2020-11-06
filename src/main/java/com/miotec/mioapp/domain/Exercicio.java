package com.miotec.mioapp.domain;

import com.miotec.mioapp.domain.Usuario;
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_usuario", nullable = true)
    private Usuario usuario;

}
