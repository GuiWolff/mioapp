package com.miotec.mioapp.repository;

import com.miotec.mioapp.domain.Exercicio;
import com.miotec.mioapp.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    Usuario findByEmail(String email);

//    @Query("select e from Exercicio e where e.data_execicio = :data_execicio")
//    List<Exercicio> findByData(@Param("data_execicio") Date data_exercicio);
}
