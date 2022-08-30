package com.marco.votacaopauta.service.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SessaoDTO {

    @NotEmpty(message = "O campo Nome precisa ser preenchido.")
    @Length(min = 5, max = 80, message = "O tamanho deve ser entre 5 e 80 caracteres.")
    private String nome;

    private Integer duracao;

    @NotNull(message = "O campo Pauta precisa ser preenchido.")
    private Integer pauta;

}
