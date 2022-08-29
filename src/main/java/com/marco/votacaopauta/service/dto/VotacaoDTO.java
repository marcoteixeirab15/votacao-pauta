package com.marco.votacaopauta.service.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class VotacaoDTO {

    @NotNull(message = "O campo Voto precisa ser preenchido.")
    private Integer voto;

    @NotNull(message = "O campo idUser precisa ser preenchido.")
    private Integer idUser;

    @NotNull(message = "O campo Pauta precisa ser preenchido.")
    private Integer pauta;

    @NotNull(message = "O campo Sessao precisa ser preenchido.")
    private Integer sessao;

    @NotEmpty(message = "O campo cpf precisa ser preenchido.")
    @Size(min = 11, max = 11, message = "CPF inválido, Preencha apenas com números")
    private String cpfAssociado;

}
