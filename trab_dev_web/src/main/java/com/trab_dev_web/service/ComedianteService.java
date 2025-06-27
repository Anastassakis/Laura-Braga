package com.trab_dev_web.service;

import com.trab_dev_web.dto.ComedianteDTO;
import com.trab_dev_web.model.Comediante;
import com.trab_dev_web.repository.ComedianteRepository;
import com.trab_dev_web.repository.PiadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ComedianteService {

    @Autowired
    private final ComedianteRepository repository;

    @Autowired
    private PiadaRepository piadaRepository;

    public ComedianteService(ComedianteRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public Page<ComedianteDTO> listarTodos(PageRequest pageRequest) {
        Page<Comediante> comediantes = repository.findAll(pageRequest);

        return comediantes.map(x -> new ComedianteDTO(x));
    }

    @Transactional(readOnly = true)
    public ComedianteDTO buscarPorId(Long id) {
        Optional<Comediante> obj = repository.findById(id);
        Comediante comediante = obj.orElseThrow(() -> new RuntimeException("Recerso não encontrado"));

        return new ComedianteDTO(comediante);
    }

    @Transactional
    public ComedianteDTO salvar(ComedianteDTO comedianteDTO) {
        Comediante comediante = new Comediante();

        comediante.setNome(comedianteDTO.getNome());
        comediante = repository.save(comediante);

        return new ComedianteDTO(comediante);
    }

    @Transactional
    public ComedianteDTO atualizar(Long id, ComedianteDTO novoComedianteDTO) {
        Comediante comediante = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Id não encontrado"));
        comediante.setNome(novoComedianteDTO.getNome());

        return new ComedianteDTO(comediante);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}

