package com.marco.votacaopauta.service.dto;

import com.marco.votacaopauta.domain.Pauta;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PautaDTO {

    private String titulo;
    private String descricao;
    private Integer status;

}
