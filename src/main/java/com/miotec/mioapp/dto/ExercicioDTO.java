package com.miotec.mioapp.dto;

import com.miotec.mioapp.domain.Exercicio;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.var;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExercicioDTO {

    private String usuario;
    private List<DadosExercicioDTO> dadosExercicio = new ArrayList<>();

    public static List<ExercicioDTO> create(List<Exercicio> exercicios) {

        if(exercicios.isEmpty()){
            return new ArrayList<>();
        }

        var exerciciosAgrupadosPorusuario = exercicios.stream().collect(Collectors.groupingBy(e -> e.getUsuario().getEmail()));
        var resultado = new ArrayList<ExercicioDTO>();

        exerciciosAgrupadosPorusuario.forEach((usuario, listaExercicios) -> {
            var exercicioDTO = new ExercicioDTO();
            exercicioDTO.setUsuario(listaExercicios.stream().findFirst().get().getUsuario().getNome());
            for (Exercicio exercicio : listaExercicios) {
                exercicioDTO.getDadosExercicio().add(new DadosExercicioDTO(exercicio.getData_execicio(), exercicio.getNota_avaliativa()));
            }

            resultado.add(exercicioDTO);
        });

        return resultado;
    }
}