package com.miotec.mioapp.controller;

import com.miotec.mioapp.domain.Exercicio;
import com.miotec.mioapp.repository.ExercicioRepository;
import com.miotec.mioapp.service.ExercicioService;
import com.miotec.mioapp.domain.Usuario;
import com.miotec.mioapp.service.UsuarioService;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/exercicios")
public class ExerciciosController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ExercicioService exercicioService;

    @Autowired
    private ExercicioRepository exercicioRepository;


    @PostMapping()
    public ResponseEntity<?> get(){
        var exercicios = exercicioService.getExercicios();
        return ResponseEntity.ok(exercicios);
    }

    @PostMapping("/get")
    public ResponseEntity<?> carregarExerciciosPorUsuarioId(@RequestBody Usuario usuario) {
        Usuario user = (usuarioService.getUsuarioByEmail(usuario.getEmail()));
        Long usuario_id = user.getId();
        var exercicios = exercicioService.carregarExerciciosPorUsuarioId(usuario_id);
        return ResponseEntity.ok(exercicios);
    }

    @PostMapping("/horario")
    public ResponseEntity<?> conferirSeHorarioExiste(@RequestBody Exercicio exercio) {
        Exercicio e = (exercicioService.getExerxicioByHorario(exercio.getHorario()));
        return ResponseEntity.ok(e);
    }

    @PostMapping("/id")
    public Optional<Exercicio> getExercicioById(@RequestBody Exercicio exercicio) {
        return exercicioService.getExercicioById(exercicio.getId());

    }

    @PostMapping("/inserir")
    public Long InsertExercicio(@RequestBody Exercicio exercicio) {
        Exercicio e = exercicioService.insert(exercicio);
        return e.getId();
    }

    @DeleteMapping()
    public void deleteExercicio(@RequestBody Exercicio exercicio) {
        exercicioService.deleteExercicio(exercicio.getId());

    }


}
