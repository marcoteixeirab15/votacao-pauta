package com.marco.votacaopauta.domain;

import com.marco.votacaopauta.domain.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@Getter
@Setter
public class Pauta implements Serializable {
    private static final long serialVersionUID = 1L;
    @OneToOne(mappedBy = "pauta", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Sessao sessao;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String titulo;
    @Column
    private String descricao;
    @Column
    private Integer qtdVotosSim = 0;
    @Column
    private Integer qtdVotosNao = 0;
    @Column
    private Integer statusPauta;

    public Pauta() {
    }

    public Pauta(String titulo, String descricao, StatusEnum statusEnum) {
        super();
        this.titulo = titulo;
        this.descricao = descricao;
        this.statusPauta = statusEnum.getValor();
    }

    public StatusEnum getStatusPauta() {
        return StatusEnum.toEnum(statusPauta);
    }

    public void setStatusPauta(StatusEnum tipo) {
        this.statusPauta = tipo.getValor();
    }
}
