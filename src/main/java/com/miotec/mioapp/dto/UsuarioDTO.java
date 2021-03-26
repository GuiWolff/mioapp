package com.miotec.mioapp.dto;

import com.miotec.mioapp.domain.Usuario;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;

@Data
public class UsuarioDTO {

    private String nome;
    private String email;
    private String senha;
    private long id;
    private LocalDate dataDeNascimento;


    public static UsuarioDTO create(Usuario u){
        if(u == null){
            u.setNome("Email não encontrado");
            ModelMapper m = new ModelMapper();
            return m.map(u, UsuarioDTO.class);
        }else{
            ModelMapper m = new ModelMapper();
            return m.map(u, UsuarioDTO.class);
        }
    }
}
