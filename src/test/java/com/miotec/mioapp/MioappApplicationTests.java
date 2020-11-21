package com.miotec.mioapp;

import com.miotec.mioapp.domain.Exercicio;
import com.miotec.mioapp.repository.ExercicioRepository;
import com.miotec.mioapp.domain.Usuario;
import com.miotec.mioapp.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
class MioappApplicationTests {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ExercicioRepository exercicioRepositor;

    @Test
    void testeUsuario() {

        List<String> nomes_teste = new ArrayList<String>();
        nomes_teste.add("Guilherme Wolff");
        nomes_teste.add("Tony Stark");
        nomes_teste.add("Michelangello Turtle");
        nomes_teste.add("Pikachu");
        nomes_teste.add("Grampeador");
        nomes_teste.add("Silvio Santos");
        nomes_teste.add("Fausto Silva");
        nomes_teste.add("Gugu Liberato");
        nomes_teste.add("Cachorro Louco");
        nomes_teste.add("Fresno");

        List<String> emails_teste = new ArrayList<String>();
        emails_teste.add("guilherme.wolff@edu.pucrs.br.com");
        emails_teste.add("tony.stark@teste.com");
        emails_teste.add("michelangello.turtle@teste.com");
        emails_teste.add("pikachu@teste.com");
        emails_teste.add("grampeador@teste.com");
        emails_teste.add("silvio.santos@teste.com");
        emails_teste.add("fausto.silva@teste.com");
        emails_teste.add("gugu.liberato@teste.com");
        emails_teste.add("cachorro.louco@teste.com");
        emails_teste.add("fresno@teste.com");

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        for (int i = 0; i < 10; i++) {
            Usuario usuario = new Usuario();
            usuario.setNome(nomes_teste.get(i));
            usuario.setEmail(emails_teste.get(i));
            usuario.setSenha(encoder.encode("0123"));
            usuarioRepository.save(usuario);

            Optional<Usuario> u = usuarioRepository.findById(Long.valueOf(i + 1));
        }

        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < 10; i++) {
                Optional<Usuario> u = usuarioRepository.findById(Long.valueOf(i + 1));
                Usuario usuario = u.get();
                Exercicio exercicio = new Exercicio();
                exercicio.setData_execicio(LocalDate.now());
                Random random = new Random();
                exercicio.setNota_avaliativa(random.nextDouble());
                exercicio.setUsuario(usuario);
                exercicioRepositor.save(exercicio);
            }
        }
    }

    @Test
    void testeExercicios(){
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 10; i++) {
                Optional<Usuario> u = usuarioRepository.findById(Long.valueOf(i + 1));
                Usuario usuario = u.get();
                Exercicio exercicio = new Exercicio();
                exercicio.setData_execicio(LocalDate.now());
                Random random = new Random();
                exercicio.setNota_avaliativa(random.nextDouble());
                exercicio.setUsuario(usuario);
                exercicioRepositor.save(exercicio);
            }
        }
    }

}
