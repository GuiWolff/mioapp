package com.miotec.mioapp.service;


import com.miotec.mioapp.dto.ExercicioDTO;
import com.miotec.mioapp.domain.Exercicio;
import com.miotec.mioapp.repository.ExercicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class ExercicioService {

    @Autowired
    private ExercicioRepository repository;

    public List<ExercicioDTO> getExercicios() {
        return ExercicioDTO.create(repository.findAll());
    }

    public Optional<Exercicio> getExercicioById(Long id) {
        return repository.findById(id);
    }

    public void deleteExercicio(Long id) {
        Optional<Exercicio> exercicio = getExercicioById(id);
        if (exercicio.isPresent()) {
            repository.deleteById(id);
        }
    }

    public Exercicio insert(Exercicio exercicio) {
        Assert.isNull(exercicio.getId(), "Não foi possivil inserir o registro.");
        return repository.save(exercicio);
    }

    public List<ExercicioDTO> carregarExerciciosPorUsuarioId(Long usuarioId) {
        return ExercicioDTO.create(repository.carregarExerciciosPorUsuarioId(usuarioId));
    }
}

