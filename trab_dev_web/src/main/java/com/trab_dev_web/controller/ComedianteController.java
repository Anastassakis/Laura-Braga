package com.trab_dev_web.controller;

import com.trab_dev_web.dto.ComedianteDTO;
import com.trab_dev_web.dto.PiadaDTO;
import com.trab_dev_web.model.Comediante;
import com.trab_dev_web.service.ComedianteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/comediante")
public class ComedianteController {

    private final ComedianteService service;

    public ComedianteController(ComedianteService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<ComedianteDTO>> listarTodos(
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

        Page<ComedianteDTO> comediantes = service.listarTodos(pageRequest);
        return ResponseEntity.ok().body(comediantes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComedianteDTO> buscarPorId(@PathVariable Long id) {
        ComedianteDTO comedianteDTO = service.buscarPorId(id);

        return ResponseEntity.ok().body(comedianteDTO);
    }

    @PostMapping
    public ResponseEntity<ComedianteDTO> salvar(@RequestBody ComedianteDTO comedianteDTO){
        comedianteDTO = service.salvar(comedianteDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(comedianteDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(comedianteDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComedianteDTO> atualizar(@PathVariable Long id, @RequestBody ComedianteDTO comedianteDTO) {
        comedianteDTO = service.atualizar(id, comedianteDTO);

        return ResponseEntity.ok().body(comedianteDTO);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
