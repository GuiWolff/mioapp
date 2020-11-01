package com.miotec.mioapp.usuarios;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

@Autowired
    private UsuarioRepository repository;

    @GetMapping()
    public Iterable<Usuario> getUsuarios(){
        return repository.findAll();
    }

    public Optional<Usuario> getUsuarioById(Long id) {
        return repository.findById(id);
    }

    public List<Usuario> postByEmail(String paciente_email) {
        return repository.findByEmail(paciente_email);
    }

    public Usuario update(Usuario usuario, Long id) {
        Assert.notNull(id, "Não foi possivil atualizar o registro.");
        Optional<Usuario> optional = getUsuarioById(id);
        if(optional.isPresent()){
            Usuario db = optional.get();
            db.setNome(usuario.getNome());
            db.setEmail(usuario.getEmail());
            db.setSenha(usuario.getSenha());
            System.out.println("Paciente id" + db.getNome());
            repository.save(db);
            return db;
        } else {
            throw new RuntimeException("Não foi possivel atualizar o registro");
        }
    }

    public void deleteUsuario(Long id) {
        Optional<Usuario> paciente = getUsuarioById(id);
        if(paciente.isPresent()){
            repository.deleteById(id);
        }
    }

    public Usuario insert(Usuario usuario) {
        Assert.isNull(usuario.getId(), "Não foi possivil inserir o registro.");
        return repository.save(usuario);
    }
}
