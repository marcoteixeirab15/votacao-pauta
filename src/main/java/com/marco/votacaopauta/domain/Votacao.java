package com.marco.votacaopauta.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marco.votacaopauta.domain.enums.SimNaoEnum;
import com.marco.votacaopauta.domain.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Votacao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer voto;

    @Column
    private Integer idUser;

    @ManyToOne
    public Pauta pauta;

    public Votacao(SimNaoEnum voto, Integer idUser, Pauta pauta) {
        super();
        this.voto = voto.getValor();
        this.idUser = idUser;
        this.pauta = pauta;
    }

    public SimNaoEnum getSimNaoEnum() {
        return SimNaoEnum.toEnum(voto);
    }

    public void setSimNaoEnum(SimNaoEnum tipo) {
        this.voto = tipo.getValor();
    }
}
