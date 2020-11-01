package com.miotec.mioapp.usuarios;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuariosController {

    @Autowired
    private UsuarioService service;

    @PostMapping()
    public ResponseEntity<Iterable<Usuario>> getPacientes(){
        return ResponseEntity.ok(service.getUsuarios());
    }

    @PostMapping("/id")
    public Optional<Usuario> getUsuariosById(@RequestBody Usuario usuario){
        return service.getUsuarioById(usuario.getId());

    }

    @PostMapping("/email")
    public List<Usuario> getUsuarioByEmail(@RequestBody Usuario usuario ){
        return service.postByEmail(usuario.getEmail());
    }

    @PostMapping("/insert")
    public String InsertUsuario(@RequestBody Usuario usuario) {
        Usuario u = service.insert(usuario);
        return "Dados do usuario " + u.getNome() + " salvos com sucesso";
    }

    @PutMapping("/alterar")
    public String setUsuario(@RequestBody Usuario usuario){
        Usuario u =  service.update(usuario, usuario.getId());
        return MessageFormat.format("Paciente {0} alterado(a) com sucesso.", u.getNome());
    }

    @DeleteMapping()
    public void deleteUsuario(@RequestBody Usuario usuario){
        service.deleteUsuario(usuario.getId());

    }


}
