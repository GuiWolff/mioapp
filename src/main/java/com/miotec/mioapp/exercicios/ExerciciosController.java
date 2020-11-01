package com.miotec.mioapp.exercicios;

import com.miotec.mioapp.usuarios.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/exercicios")
public class ExerciciosController {

    @Autowired
    private ExercicioService service;

    @PostMapping()
    public ResponseEntity<Iterable<Exercicio>> getExercicios(){
        return ResponseEntity.ok(service.getExercicios());
    }

    @PostMapping("/id")
    public Optional<Exercicio> getExercicioById(@RequestBody Exercicio exercicio){
        return service.getExercicioById(exercicio.getId());

    }

    @PostMapping("/insert")
    public String InsertExercicio(@RequestBody Exercicio exercicio) {
        Exercicio e = service.insert(exercicio);
        return "Dados do usuario " + e.getData_execicio() + " - " + e.getNota_avaliativa() + " salvos com sucesso";
    }

    @DeleteMapping()
    public void deleteExercicio(@RequestBody Exercicio exercicio){
        service.deleteExercicio(exercicio.getId());

    }


}
