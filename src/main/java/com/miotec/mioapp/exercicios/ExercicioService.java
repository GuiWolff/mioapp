package com.miotec.mioapp.exercicios;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Service
public class ExercicioService {

@Autowired
    private ExercicioRepository repository;

    @GetMapping()
    public Iterable<Exercicio> getExercicios(){
        return repository.findAll();
    }

    public Optional<Exercicio> getExercicioById(Long id) {
        return repository.findById(id);
    }

    public void deleteExercicio(Long id) {
        Optional<Exercicio> exercicio = getExercicioById(id);
        if(exercicio.isPresent()){
            repository.deleteById(id);
        }
    }

    public Exercicio insert(Exercicio exercicio) {
        Assert.isNull(exercicio.getId(), "Não foi possivil inserir o registro.");
        return repository.save(exercicio);
    }
}
