package com.miotec.mioapp.repository;

import com.miotec.mioapp.domain.Exercicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;


@Repository
public interface ExercicioRepository extends JpaRepository<Exercicio,Long>{

    @Query("select e from Exercicio e where e.usuario.id = :idusuario")
//    @Query("select e.data_execicio, e.nota_avaliativa, e.usuario from Exercicio e where e.usuario.id = :idusuario")
    List<Exercicio> carregarExerciciosPorUsuarioId(@Param("idusuario") Long id);

    @Query("select e from Exercicio e where e.horario = :horario")
    Exercicio getHorario(@Param("horario") Timestamp horario);

}
