package com.miotec.mioapp.repository;

import com.miotec.mioapp.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    Usuario findByEmail(String email);

//    @Query("select e from Exercicio e where e.data_execicio = :data_execicio")
//    List<Exercicio> findByData(@Param("data_execicio") Date data_exercicio);
}
