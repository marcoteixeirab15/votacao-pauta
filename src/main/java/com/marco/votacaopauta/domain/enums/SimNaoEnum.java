package com.marco.votacaopauta.domain.enums;

import lombok.Getter;

@Getter
public enum SimNaoEnum {

    SIM(1, "Sim"),
    NAO(2, "Não");

    private Integer valor;
    private String descricao;

    SimNaoEnum(Integer valor, String descricao) {
        this.valor = valor;
        this.descricao = descricao;
    }

    public static SimNaoEnum toEnum(Integer valor){

        if (valor == null){
            return null;
        }

        for (SimNaoEnum simNaoEnum : SimNaoEnum.values()){
            if (valor.equals(simNaoEnum.getValor())){
                return simNaoEnum;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + valor);

    }
}
