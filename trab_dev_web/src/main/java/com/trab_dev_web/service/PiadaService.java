package com.trab_dev_web.service;

import com.trab_dev_web.dto.PiadaDTO;
import com.trab_dev_web.model.Piada;
import com.trab_dev_web.repository.PiadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PiadaService {

    @Autowired
    private final PiadaRepository repository;

    public PiadaService(PiadaRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public Page<PiadaDTO> listarTodas(PageRequest pageRequest) {
        Page<Piada> piadas = repository.findAll(pageRequest);

        return piadas.map(x -> new PiadaDTO(x));
    }

    @Transactional(readOnly = true)
    public PiadaDTO buscarPorId(Long id) {
        Optional<Piada> piada = repository.findById(id);
        Piada piada1 = piada.orElseThrow(() -> new RuntimeException("Recurso não encontrado"));

        return new PiadaDTO(piada1);
    }

    @Transactional
    public PiadaDTO salvar(PiadaDTO piadaDTO) {
        Piada piada = new Piada();

        piada.setTexto(piadaDTO.getTexto());
        piada.setComediante(piada.getComediante());
        piada = repository.save(piada);

        return new PiadaDTO(piada);
    }

    @Transactional
    public PiadaDTO atualizar(Long id, PiadaDTO novaPiada) {
        Piada piada = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Id não encontrado"));
        piada.setTexto(novaPiada.getTexto());
        piada.setComediante(piada.getComediante());

        return new PiadaDTO(piada);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}

