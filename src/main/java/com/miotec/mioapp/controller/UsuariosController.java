package com.miotec.mioapp.controller;


import com.miotec.mioapp.domain.Usuario;
import com.miotec.mioapp.dto.RequisicaoInsercaoUsuarioDTO;
import com.miotec.mioapp.dto.UsuarioDTO;
import com.miotec.mioapp.service.UsuarioService;
import javassist.tools.rmi.ObjectNotFoundException;
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

    @PostMapping()
    public ResponseEntity<List<UsuarioDTO>> buscarTodosUsuarios() {
        List<UsuarioDTO> u = service.getUsuarios();
        return ResponseEntity.ok(u);
    }

    @PostMapping("/buscar_usuario_pelo_id")
    public ResponseEntity<?> buscarUsuarioPeloId(@RequestBody Usuario usuario) throws ObjectNotFoundException {
        try {
            UsuarioDTO u = service.getUsuarioById(usuario.getId());
            return ResponseEntity.ok(u);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/buscar_usuario_por_email")
    public ResponseEntity<?> buscarUsuarioPeloEmail(@RequestBody Usuario usuario) {
        try {
            UsuarioDTO u = UsuarioDTO.create(service.getUsuarioByEmail(usuario.getEmail()));
            return ResponseEntity.ok(u);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @PostMapping("/resetar_senha_usuario")
    public ResponseEntity<?> resetarSenhaUsuario(@RequestBody Usuario usuario) {
        Usuario u = service.getUsuarioByEmail(usuario.getEmail());
        UsuarioDTO uDto = UsuarioDTO.create(u);
        if (u != null) {
            service.sendEmail(u.getEmail());
            return ResponseEntity.ok(uDto);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/inserir_usuario")
    public ResponseEntity InserirUsuario(@RequestBody RequisicaoInsercaoUsuarioDTO requisicaoInsercaoUsuario) {
        try {
            UsuarioDTO u = (service.insert(requisicaoInsercaoUsuario.criarUsuario()));
            return ResponseEntity.created(null).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/alterar_usuario")
    public ResponseEntity alterarUsuario(@RequestBody Usuario usuario) {
        UsuarioDTO u = UsuarioDTO.create(service.update(usuario, usuario.getId()));
        return u != null ? ResponseEntity.ok(u) : ResponseEntity.notFound().build();
    }

    @DeleteMapping()
    public ResponseEntity deleteUsuario(@RequestBody Usuario usuario) {
        service.delete(usuario.getId());
        return ResponseEntity.ok().build();
    }


}
