package com.miotec.mioapp.dto;

import com.miotec.mioapp.domain.Usuario;
import lombok.Data;
import lombok.var;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Data
public class RequisicaoInsercaoUsuarioDTO {

    private String nome;
    private String email;
    private String senha;

    public Usuario criarUsuario(){

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        var usuario = new Usuario();
        usuario.setNome(this.nome);
        usuario.setEmail(this.email);
        usuario.setSenha(encoder.encode(this.senha));

        return usuario;
    }
}
