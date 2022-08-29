package com.marco.votacaopauta.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marco.votacaopauta.domain.enums.StatusEnum;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Sessao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String nome;

    @Column()
    private Integer duracao = 1;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Pauta pauta;

    @Column
    private Integer statusSessao;


    public Sessao(Integer duracao, String nome, Pauta pauta, StatusEnum statusSessao) {
        super();
        this.nome = nome;
        this.duracao = duracao == null ? 1 : duracao ;
        this.pauta = pauta;
        this.statusSessao = statusSessao.getValor();
    }

    public StatusEnum getStatusSessao() {
        return StatusEnum.toEnum(statusSessao);
    }

    public void setStatusSessao(StatusEnum tipo) {
        this.statusSessao = tipo.getValor();
    }
}
