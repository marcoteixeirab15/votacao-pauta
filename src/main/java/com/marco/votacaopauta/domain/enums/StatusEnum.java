package com.marco.votacaopauta.domain.enums;

import lombok.Getter;

@Getter
public enum StatusEnum {

    ATIVO(1, "Ativo"),
    INATIVO(2, "Inativo");

    private Integer valor;
    private String descricao;

    StatusEnum(Integer valor, String descricao) {
        this.valor = valor;
        this.descricao = descricao;
    }

    public static StatusEnum toEnum(Integer valor){

        if (valor == null){
            return null;
        }

        for (StatusEnum statusPautaEnum : StatusEnum.values()){
            if (valor.equals(statusPautaEnum.getValor())){
                return statusPautaEnum;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + valor);

    }
}
