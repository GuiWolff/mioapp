package com.miotec.mioapp.controller;

import com.miotec.mioapp.domain.Exercicio;
import com.miotec.mioapp.dto.UsuarioDTO;
import com.miotec.mioapp.repository.ExercicioRepository;
import com.miotec.mioapp.service.ExercicioService;
import com.miotec.mioapp.domain.Usuario;
import com.miotec.mioapp.service.UsuarioService;
import javassist.tools.rmi.ObjectNotFoundException;
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

    @PostMapping()
    public ResponseEntity<?> buscarTodosExercicios(){
        var exercicios = exercicioService.getExercicios();
        return ResponseEntity.ok(exercicios);
    }

    @PostMapping("/carregar_exercicio_por_email_de_usuario")
    public ResponseEntity<?> carregarExercicioPorEmailDeUsuario(@RequestBody Usuario usuario) {
        Usuario user = (usuarioService.getUsuarioByEmail(usuario.getEmail()));
        Long usuario_id = user.getId();
        var exercicios = exercicioService.carregarExerciciosPorUsuarioId(usuario_id);
        return ResponseEntity.ok(exercicios);
    }

    @PostMapping("/buscar_exercicio_por_horario")
    public ResponseEntity<?> buscarExercicioPorHorario(@RequestBody Exercicio exercio) {
        Exercicio e = (exercicioService.getExerxicioByHorario(exercio.getHorario()));
        return ResponseEntity.ok(e);
    }

    @PostMapping("/buscar_exercicio_por_id")
    public Optional<Exercicio> buscarExercicioPorId(@RequestBody Exercicio exercicio) {
        return exercicioService.getExercicioById(exercicio.getId());

    }

    @PostMapping("/inserir_exercicio")
    public Long inserirExercicio(@RequestBody Exercicio exercicio){
        Exercicio e = exercicioService.insert(exercicio);
        return e.getId();
    }

    @DeleteMapping()
    public void deletarExercicio(@RequestBody Exercicio exercicio) {
        exercicioService.deleteExercicio(exercicio.getId());

    }


}
