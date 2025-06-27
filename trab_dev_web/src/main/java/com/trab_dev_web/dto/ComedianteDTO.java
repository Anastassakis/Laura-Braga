package com.trab_dev_web.dto;

import com.trab_dev_web.model.Comediante;
import com.trab_dev_web.model.Piada;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ComedianteDTO {

    private Long id;

    private String nome;

    private List<PiadaDTO> piadas;

    public ComedianteDTO(Comediante comediante) {
        this.id = comediante.getId();
        this.nome = comediante.getNome();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<PiadaDTO> getPiadas() {
        return piadas;
    }

    public void setPiadas(List<PiadaDTO> piadas) {
        this.piadas = piadas;
    }
}
