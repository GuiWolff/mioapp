package com.miotec.mioapp.controller;

import com.miotec.mioapp.domain.Exercicio;
import com.miotec.mioapp.dto.UsuarioDTO;
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
    private ExercicioService service;

    @Autowired
    private ExercicioRepository exercicioRepository;


    @PostMapping()
    public ResponseEntity<?> get(){
        var exercicios = service.getExercicios();
        return ResponseEntity.ok(exercicios);
    }

    @PostMapping("/get")
    public ResponseEntity<?> carregarExerciciosPorUsuarioId(@RequestBody Usuario usuario) {
        Usuario user = (usuarioService.getUsuarioByEmail(usuario.getEmail()));
        Long usuario_id = user.getId();
        var exercicios = service.carregarExerciciosPorUsuarioId(usuario_id);
        return ResponseEntity.ok(exercicios);
    }

    @PostMapping("/id")
    public Optional<Exercicio> getExercicioById(@RequestBody Exercicio exercicio) {
        return service.getExercicioById(exercicio.getId());

    }

    @PostMapping("/insert")
    public String InsertExercicio(@RequestBody Exercicio exercicio) {
        Exercicio e = service.insert(exercicio);
        return "Dados do usuario " + e.getData_execicio() + " - " + e.getNota_avaliativa() + " salvos com sucesso";
    }

    @DeleteMapping()
    public void deleteExercicio(@RequestBody Exercicio exercicio) {
        service.deleteExercicio(exercicio.getId());

    }


}
