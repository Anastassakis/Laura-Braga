package com.trab_dev_web.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "comediantes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comediante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @OneToMany(mappedBy = "comediante", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Piada> piadas;

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

    public List<Piada> getPiadas() {
        return piadas;
    }

    public void setPiadas(List<Piada> piadas) {
        this.piadas = piadas;
    }
}
