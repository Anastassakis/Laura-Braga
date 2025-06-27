package com.trab_dev_web.controller;

import com.trab_dev_web.dto.PiadaDTO;
import com.trab_dev_web.model.Piada;
import com.trab_dev_web.service.PiadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/piada")
public class  PiadaController {

    @Autowired
    private PiadaService service;

    @GetMapping
    public ResponseEntity<Page<PiadaDTO>> listarTodas(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "id") String orderBy
            ) {
        PageRequest pageRequest = PageRequest.of(
                page,
                linesPerPage,
                Sort.Direction.valueOf(direction.toUpperCase()),
                orderBy
        );

        Page<PiadaDTO> piadas = service.listarTodas(pageRequest);
        return ResponseEntity.ok().body(piadas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PiadaDTO> buscarPorId(@PathVariable Long id) {
        PiadaDTO piadaDTO = service.buscarPorId(id);

        return ResponseEntity.ok().body(piadaDTO);
    }

    @PostMapping
    public ResponseEntity<PiadaDTO> salvar(@RequestBody PiadaDTO piadaDTO) {
        piadaDTO = service.salvar(piadaDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(piadaDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(piadaDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PiadaDTO> atualizar(@PathVariable Long id, @RequestBody PiadaDTO piadaDTO) {
        piadaDTO = service.atualizar(id, piadaDTO);

        return ResponseEntity.ok().body(piadaDTO);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
