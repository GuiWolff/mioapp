package com.miotec.mioapp.service;


import com.miotec.mioapp.domain.Usuario;
import com.miotec.mioapp.dto.UsuarioDTO;
import com.miotec.mioapp.repository.UsuarioRepository;
import javassist.tools.rmi.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JavaMailSender mailSender;


    public List<UsuarioDTO> getUsuarios() {
        List<UsuarioDTO> list = usuarioRepository.findAll().stream().map(UsuarioDTO::create).collect(Collectors.toList());
        return list;
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
            if(u.getNome() == null) u.setNome(db.getNome());
            if(u.getEmail() == null) u.setEmail(db.getEmail());
            if(u.getSenha() == null) {
                u.setSenha(encoder.encode(db.getNome()));
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


    public void sendEmail(String email) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String nova_senha = UUID.randomUUID().toString().replace("-", "").substring(0, 6);
        Usuario u = getUsuarioByEmail(email);
        u.setSenha(encoder.encode(nova_senha));
        usuarioRepository.save(u);

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);
        msg.setSubject("Senha temporária para efetuar login" );
        msg.setText("Olá " + u.getNome() + ", sua senha temporária para logar em sua conta Miotec é " + nova_senha + "\nAté mais!\nAtt, \nMiotec "+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
        mailSender.send(msg);




/*            <h2>&nbsp;</h2>
<table style="width: 948px;">
<tbody>
<tr>
<td style="width: 942px;">
<h2>Recupera&ccedil;&atilde;o de senha!</h2>
</td>
</tr>
</tbody>
</table>
<p style="font-size: 1.5em;">Ol&aacute; Guilherme Wolff, este &eacute; um e-mail de recupera&ccedil;&atilde;o de senha para o aplicativo Miotec PelviFit Treiner.</p>
<p style="font-size: 1.5em;">Sua senha tempor&aacute;ria ser&aacute;&nbsp;<span style="background-color: #33cccc;"><strong style="padding: 0px 5px; color: #ffffff;">73bbe1</strong></span>&nbsp;at&eacute; o momento que voc&ecirc; atualiz&aacute;-la.</p>
<p style="font-size: 1.5em;">Para atualizar esta senha para sua nova senha, basta acessar o campo de dados cadastrais em configura&ccedil;&otilde;es, ap&oacute;s logar no aplicativo!<br /><br />Tenha &oacute;timos treinos!</p>
<p style="font-size: 1.5em;"><a href="http://www.miotec.com.b"><img src="https://www.miotec.com.br/wp-content/uploads/2020/01/logo.png" alt="" width="250" height="70" /></a></p>
<p style="font-size: 1.5em;">&nbsp;</p>
<p style="font-size: 1.5em;">&nbsp;</p>
<p>&nbsp;</p>*/

    }
}
