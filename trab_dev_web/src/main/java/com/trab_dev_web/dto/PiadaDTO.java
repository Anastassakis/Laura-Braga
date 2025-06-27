package com.trab_dev_web.dto;

import com.trab_dev_web.model.Piada;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class PiadaDTO {

    private Long id;
    private String texto;
    private Long comedianteId;

    public PiadaDTO(Piada piada) {
        this.id = piada.getId();
        this.texto = piada.getTexto();
        this.comedianteId = piada.getComediante() != null ? piada.getComediante().getId() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Long getComedianteId() {
        return comedianteId;
    }

    public void setComedianteId(Long comedianteId) {
        this.comedianteId = comedianteId;
    }
}
