package com.miotec.mioapp.service;


import com.miotec.mioapp.domain.Protocolo;
import com.miotec.mioapp.domain.Usuario;
import com.miotec.mioapp.dto.UsuarioDTO;
import com.miotec.mioapp.repository.ProtocoloRepository;
import javassist.tools.rmi.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class ProtocoloService {

    @Autowired
    private ProtocoloRepository protocoloRepository;



    public List<Protocolo> getProtocolos() {
        return protocoloRepository.findAll();
    }

    public Optional<Protocolo> getProtocoloById(Long id) throws ObjectNotFoundException {
        Optional<Protocolo> protocolo = protocoloRepository.findById(id);
        return protocolo;
    }

    public Protocolo update(Protocolo protocolo, Long id) {

        Assert.notNull(id, "Não foi possivil atualizar o registro.");
        Optional<Protocolo> optional = protocoloRepository.findById(id);
        ModelMapper m = new ModelMapper();
        Protocolo p = m.map(protocolo, Protocolo.class);
        if (optional != null) {
            Protocolo db = optional.get();
            if(p.getCodigo() == null) p.setCodigo(db.getCodigo());
            if(p.getProtocolo() == null) p.setProtocolo(db.getProtocolo());
            System.out.println("Protocolo id" + p.getCodigo() +" - "+ p.getProtocolo());
            protocoloRepository.save(p);
            return p;
        } else {
            throw new RuntimeException("Não foi possivel atualizar o registro");
        }
    }

    public Protocolo insert(Protocolo protocolo) throws Exception {

        return protocoloRepository.save(protocolo);
    }

    public Protocolo getProtocoloByCodigo(String codigo) {
        return protocoloRepository.findByCodigo(codigo);
    }

    public void delete(Long id) {
        protocoloRepository.deleteById(id);
    }
}



