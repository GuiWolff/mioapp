package com.miotec.mioapp.dto;

import com.miotec.mioapp.domain.Usuario;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.Date;

@Data
public class UsuarioDTO {

    private String nome;
    private String email;
    private long id;
    private Date dataDeNascimento;


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
