package com.miotec.mioapp;

import com.miotec.mioapp.exercicios.Exercicio;
import com.miotec.mioapp.exercicios.ExercicioService;
import com.miotec.mioapp.usuarios.Usuario;
import com.miotec.mioapp.usuarios.UsuarioService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
class MioappApplicationTests {

    @Autowired
    private UsuarioService serviceUsuario;

    @Autowired
    private ExercicioService serviceExercicio;

    @Test
    void testeUsuario() {

        Usuario usuario = new Usuario();
        usuario.setNome("Seu Jorge");
        usuario.setEmail("seujorge_music_man@gmail.com");
        usuario.setSenha("meunomenaoehjonny");

        serviceUsuario.insert(usuario);
    }

    @Test
    void testeExercicio(){

        Usuario usuario = new Usuario();
        Exercicio exercicio = new Exercicio();
        exercicio.setData_execicio(LocalDate.now());
        exercicio.setNota_avaliativa(9.9);

        serviceExercicio.insert(exercicio);
    }


}
