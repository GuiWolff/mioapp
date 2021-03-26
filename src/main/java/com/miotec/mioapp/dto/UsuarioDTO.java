package com.miotec.mioapp.dto;

import com.miotec.mioapp.domain.Usuario;
import lombok.Data;
import org.apache.tomcat.util.codec.binary.Base64;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;

@Data
public class UsuarioDTO {



    private String nome;
    private String email;
    private boolean loginManual;
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

