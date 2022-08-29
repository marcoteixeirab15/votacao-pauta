package com.marco.votacaopauta.service.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
public class PautaDTO implements Serializable {

    @NotEmpty(message = "O campo Titulo precisa ser preenchido.")
    @Length(min = 5, max = 80, message = "O tamanho deve ser entre 5 e 80 caracteres.")
    private String titulo;

    @NotEmpty(message = "O campo Descrição precisa ser preenchido.")
    @Length(min = 5, max = 80, message = "O tamanho deve ser entre 5 e 80 caracteres.")
    private String descricao;

    @NotNull(message = "O campo Status precisa ser preenchido.")
    private Integer status;

}
