package com.marco.votacaopauta.domain;

import com.marco.votacaopauta.domain.enums.SimNaoEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
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

    @Column
    private String cpfAssociado;

    @ManyToOne
    public Pauta pauta;

    @ManyToOne
    public Sessao sessao;

    public Votacao(SimNaoEnum voto, Integer idUser, Pauta pauta, Sessao sessao, @NotEmpty(message = "O campo cpf precisa ser preenchido.") @Size(min = 11, max = 11, message = "CPF inválido, Preencha apenas com números") String cpfAssociado) {
        super();
        this.voto = voto.getValor();
        this.idUser = idUser;
        this.pauta = pauta;
        this.sessao = sessao;
        this.cpfAssociado = cpfAssociado;
    }

    public SimNaoEnum getSimNaoEnum() {
        return SimNaoEnum.toEnum(voto);
    }

    public void setSimNaoEnum(SimNaoEnum tipo) {
        this.voto = tipo.getValor();
    }
}
