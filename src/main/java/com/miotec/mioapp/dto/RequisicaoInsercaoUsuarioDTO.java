package com.miotec.mioapp.dto;

import com.miotec.mioapp.domain.Usuario;
import lombok.Data;
import lombok.var;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;

@Data
public class RequisicaoInsercaoUsuarioDTO {

    private String nome;
    private String email;
    private String senha;
    private LocalDate dataDeNascimento;

    public Usuario criarUsuario(){

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        var usuario = new Usuario();
        usuario.setNome(this.nome);
        usuario.setEmail(this.email);
        usuario.setSenha(encoder.encode(this.senha));
        usuario.setDataDeNascimento(this.dataDeNascimento);

        return usuario;
    }
}
