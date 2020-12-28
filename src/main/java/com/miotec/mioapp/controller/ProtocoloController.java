package com.miotec.mioapp.controller;


import com.miotec.mioapp.domain.Protocolo;
import com.miotec.mioapp.domain.Usuario;
import com.miotec.mioapp.dto.RequisicaoInsercaoUsuarioDTO;
import com.miotec.mioapp.dto.UsuarioDTO;
import com.miotec.mioapp.service.ProtocoloService;
import com.miotec.mioapp.service.UsuarioService;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/protocolos")
public class ProtocoloController {

    @Autowired
    private ProtocoloService service;

    @PostMapping()
    public ResponseEntity<List<Protocolo>> get() {
        List<Protocolo> p = service.getProtocolos();
        return ResponseEntity.ok(p);
    }

    @PostMapping("/id")
    public ResponseEntity<?> get(@RequestBody Protocolo protocolo) throws ObjectNotFoundException {
        try {
            Optional<Protocolo> p = service.getProtocoloById(protocolo.getId());
            return ResponseEntity.ok(p);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/codigo")
    public ResponseEntity<?> getProtocoloByCodigo(@RequestBody Protocolo protocolo) {
        try {
            Protocolo p = service.getProtocoloByCodigo(protocolo.getCodigo());
            return ResponseEntity.ok(p);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/inserir")
    public ResponseEntity InsertProtocolo(@RequestBody Protocolo protocolo) {
        try {
            Protocolo p = (service.insert(protocolo));
            return ResponseEntity.created(null).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/alterar")
    public ResponseEntity setProtocolo(@RequestBody Protocolo protocolo) {
        Protocolo p = service.update(protocolo, protocolo.getId());
        return p != null ? ResponseEntity.ok(p) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deletar")
    public ResponseEntity deleteProtocolo(@RequestBody Protocolo protocolo) {
        service.delete(protocolo.getId());
        return ResponseEntity.ok().build();
    }


}
