package com.miotec.mioapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DadosExercicioDTO {

    private LocalDate data_execicio;
    private Double nota_avaliativa;
    private Long id;

}
