package com.miotec.mioapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DadosExercicioDTO {

    private LocalDate data_execicio;
    private Timestamp horario;
    private Double nota_avaliativa;
    private Long id;
    private Long id_protocolo;


}
