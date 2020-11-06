package com.miotec.mioapp.security;

import com.miotec.mioapp.domain.Usuario;
import com.miotec.mioapp.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service(value = "userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByEmail(username);

        if(usuario == null){
            throw new UsernameNotFoundException("Usuario não encontrado.");
        } else {
            return User.withUsername(username).password(usuario.getSenha()).roles("USER").build();
        }


    }
}
