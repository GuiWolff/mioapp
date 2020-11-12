package com.miotec.mioapp.controller;


import com.miotec.mioapp.domain.Usuario;
import com.miotec.mioapp.dto.RequisicaoInsercaoUsuarioDTO;
import com.miotec.mioapp.dto.UsuarioDTO;
import com.miotec.mioapp.service.UsuarioService;
import javassist.tools.rmi.ObjectNotFoundException;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuariosController {

    @Autowired
    private UsuarioService service;




    @GetMapping()
    public ResponseEntity<List<UsuarioDTO>> get() {
        List<UsuarioDTO> u = service.getUsuarios();
        return ResponseEntity.ok(u);
    }

    @PostMapping("/id")
    public ResponseEntity<?> get(@RequestBody Usuario usuario) throws ObjectNotFoundException {
        try {
            UsuarioDTO u = service.getUsuarioById(usuario.getId());
            return ResponseEntity.ok(u);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/recuperar_senha")
    public ResponseEntity<?> getEmailRecupecao(@RequestBody Usuario usuario) {
        Usuario u = service.getUsuarioByEmail(usuario.getEmail());
        UsuarioDTO uDto = UsuarioDTO.create(u);
        if (u != null) {
            service.sendEmail(u.getEmail());
            return ResponseEntity.ok(uDto);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/insert")
    public ResponseEntity InsertUsuario(@RequestBody RequisicaoInsercaoUsuarioDTO requisicaoInsercaoUsuario) {
        try {
            UsuarioDTO u = service.insert(requisicaoInsercaoUsuario.criarUsuario());
            return ResponseEntity.created(null).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/alterar")
    public ResponseEntity setUsuario(@RequestBody Usuario usuario) {
        UsuarioDTO u = service.update(usuario, usuario.getId());
        return u != null ? ResponseEntity.ok(u) : ResponseEntity.notFound().build();
    }

    @DeleteMapping()
    public ResponseEntity deleteUsuario(@RequestBody Usuario usuario) {
        service.delete(usuario.getId());
        return ResponseEntity.ok().build();
    }


}
