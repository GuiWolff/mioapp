package com.miotec.mioapp.service;


import com.miotec.mioapp.domain.Usuario;
import com.miotec.mioapp.dto.UsuarioDTO;
import com.miotec.mioapp.repository.UsuarioRepository;
import javassist.tools.rmi.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmailService emailService;


    public List<UsuarioDTO> getUsuarios() {
        return usuarioRepository.findAll().stream().map(UsuarioDTO::create).collect(Collectors.toList());
    }

    public UsuarioDTO getUsuarioById(Long id) throws ObjectNotFoundException {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.map(UsuarioDTO::create).orElseThrow(() -> new ObjectNotFoundException("Carro não encontrado"));
    }

    public Usuario update(Usuario usuario, Long id) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Assert.notNull(id, "Não foi possivil atualizar o registro.");
        Optional<Usuario> optional = usuarioRepository.findById(id);
        ModelMapper m = new ModelMapper();
        Usuario u = m.map(usuario, Usuario.class);
        if (optional != null) {
            Usuario db = optional.get();
            if (u.getNome() == null) u.setNome(db.getNome());
            if (u.getDataDeNascimento() == null) u.setDataDeNascimento(db.getDataDeNascimento());
            if (u.getEmail() == null) u.setEmail(db.getEmail());
            if (u.getSenha() == null) {
                u.setSenha(db.getSenha());
            } else {
                u.setSenha(encoder.encode(u.getSenha()));
            }
            System.out.println("Usuario id" + u.getNome());
            usuarioRepository.save(u);
            return u;
        } else {
            throw new RuntimeException("Não foi possivel atualizar o registro");
        }
    }

    public UsuarioDTO insert(Usuario usuario) throws Exception {
        if (usuario.getSenha().isEmpty()) {
            throw new Exception("A senha não pode estar vazia");
        }
        if (getUsuarioByEmail(usuario.getEmail()) != null) {
            throw new Exception("e-mail já cadastrado");
        }
        return UsuarioDTO.create(usuarioRepository.save(usuario));
    }

    public Usuario getUsuarioByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }

    public void sendEmailDeRecuperacao(String email) throws MessagingException {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String nova_senha = UUID.randomUUID().toString().replace("-", "").substring(0, 6);
        Usuario u = getUsuarioByEmail(email);
        u.setSenha(encoder.encode(nova_senha));
        usuarioRepository.save(u);
        emailService.enviarEmailDeResetarSenha(u,nova_senha);
    }

    public void sendEmailDeCriacaoDeConta(String email) throws MessagingException {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String nova_senha = UUID.randomUUID().toString().replace("-", "").substring(0, 6);
        Usuario u = getUsuarioByEmail(email);
        u.setSenha(encoder.encode(nova_senha));
        usuarioRepository.save(u);
        emailService.enviarEmailDeCriarConta(u,nova_senha);
    }

    public Usuario updateLogin(Usuario usuario, Long id) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Assert.notNull(id, "Não foi possivil atualizar o registro.");
        Optional<Usuario> optional = usuarioRepository.findById(id);
        ModelMapper m = new ModelMapper();
        Usuario u = m.map(usuario, Usuario.class);
        if (optional != null) {
            Usuario db = optional.get();
            u.setNome("Anônimo-"+db.getId());
            if (u.getDataDeNascimento() == null) u.setDataDeNascimento(db.getDataDeNascimento());
//            u.setEmail(UUID.randomUUID().toString().replace("-", "").substring(0, 65) + "e-mail@excluido");
            u.setEmail("e-mail@excluido");
            if (u.getSenha() == null) {
                u.setSenha(db.getSenha());
            } else {
                u.setSenha(encoder.encode(u.getSenha()));
            }
            System.out.println("Usuario id" + u.getNome());
            usuarioRepository.save(u);
            return u;
        } else {
            throw new RuntimeException("Não foi possivel atualizar o registro");
        }
    }
}
