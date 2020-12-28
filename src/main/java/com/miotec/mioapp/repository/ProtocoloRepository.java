package com.miotec.mioapp.repository;

import com.miotec.mioapp.domain.Protocolo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProtocoloRepository extends JpaRepository<Protocolo,Long> {


    Protocolo findByCodigo(String codigo);
}
