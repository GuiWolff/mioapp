package com.miotec.mioapp.controller;


import com.miotec.mioapp.domain.Usuario;
import com.miotec.mioapp.dto.RequisicaoInsercaoUsuarioDTO;
import com.miotec.mioapp.dto.UsuarioDTO;
import com.miotec.mioapp.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
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

    @GetMapping()
    public ResponseEntity<List<UsuarioDTO>> buscar() {
        List<UsuarioDTO> u = service.getUsuarios();
        return ResponseEntity.ok(u);
    }

    @PostMapping("/buscar_usuario_pelo_id")
    public ResponseEntity<?> buscarUsuarioPeloId(@RequestBody Usuario usuario) {
        try {
            UsuarioDTO u = service.getUsuarioById(usuario.getId());
            return ResponseEntity.ok(u);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/buscar_usuario_pelo_email")
    public ResponseEntity<?> buscarUsuarioPeloEmail(@RequestBody Usuario usuario) {
        try {
            UsuarioDTO u = UsuarioDTO.create(service.getUsuarioByEmail(usuario.getEmail()));
            return ResponseEntity.ok(u);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
         }
    }

    @PostMapping("/verificar_email")
    public ResponseEntity<?> verificaEmail(@RequestBody Usuario usuario){
        Usuario u = service.getUsuarioByEmail(usuario.getEmail());
        try{
            if (u != null) {
                return new ResponseEntity<>(UsuarioDTO.create(service.getUsuarioByEmail(usuario.getEmail())), HttpStatus.OK);
            }else
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Email nao cadastrado");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email nao cadastrado");
        }


    }



    @PostMapping("/resetar_senha_usuario")
    public ResponseEntity<?> getEmailRecupecao(@RequestBody Usuario usuario) throws MessagingException {
        Usuario u = service.getUsuarioByEmail(usuario.getEmail());
        UsuarioDTO uDto = UsuarioDTO.create(u);
        if (uDto != null) {
            service.sendEmailDeRecuperacao(u.getEmail());
            return ResponseEntity.ok(uDto);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/criar_nova_senha_usuario")
    public ResponseEntity<?> getEmailCriacaoDeConta(@RequestBody Usuario usuario) throws MessagingException {
        Usuario u = service.getUsuarioByEmail(usuario.getEmail());
        UsuarioDTO uDto = UsuarioDTO.create(u);
        if (uDto != null) {
            service.sendEmailDeCriacaoDeConta(u.getEmail());
            return ResponseEntity.ok(uDto);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/inserir_usuario")
    public ResponseEntity<?> InserirUsuario(@RequestBody RequisicaoInsercaoUsuarioDTO requisicaoInsercaoUsuario) {
        try {
            return new ResponseEntity<>(service.insert(requisicaoInsercaoUsuario.criarUsuario()), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/buscar_usuario")
    public ResponseEntity<?> buscarUsuario(@RequestBody Usuario usuario) {
        try {
            Usuario u = (service.getUsuarioByEmail(usuario.getEmail()));
            return ResponseEntity.ok(u);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @PutMapping("/alterar_usuario")
    public ResponseEntity alterarUsuario(@RequestBody Usuario usuario) {

        UsuarioDTO u = UsuarioDTO.create(service.update(usuario, usuario.getId()));
        return u != null ? ResponseEntity.ok(u) : ResponseEntity.notFound().build();
    }

    @PutMapping("/excluir_dados_usuario")
    public ResponseEntity alterarCadastroUsuario(@RequestBody Usuario usuario) {

        UsuarioDTO u = UsuarioDTO.create(service.updateLogin(usuario, usuario.getId()));
        return u != null ? ResponseEntity.ok(u) : ResponseEntity.notFound().build();
    }



    @DeleteMapping()
    public ResponseEntity deleteUsuario(@RequestBody Usuario usuario) {
        service.delete(usuario.getId());
        return ResponseEntity.ok().build();
    }


}
