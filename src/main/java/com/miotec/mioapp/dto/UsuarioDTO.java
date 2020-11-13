package com.miotec.mioapp.dto;

import com.miotec.mioapp.domain.Usuario;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class UsuarioDTO {

    private Long id;
    private String nome;
    private String email;
//    private String senha;


    public static UsuarioDTO create(Usuario u){
        if(u == null){
            u.setEmail("Email não encontrado");
            ModelMapper m = new ModelMapper();
            return m.map(u, UsuarioDTO.class);
        }else{
            ModelMapper m = new ModelMapper();
            return m.map(u, UsuarioDTO.class);
        }
    }
}
