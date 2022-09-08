package com.marco.votacaopauta.service;

import com.marco.votacaopauta.domain.Pauta;
import com.marco.votacaopauta.domain.Sessao;
import com.marco.votacaopauta.domain.Votacao;
import com.marco.votacaopauta.domain.enums.SimNaoEnum;
import com.marco.votacaopauta.domain.enums.StatusEnum;
import com.marco.votacaopauta.repository.VotacaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
class VotacaoServiceTest {

    @Autowired
    private VotacaoRepository repository;
    private Votacao votacao;

    public static Votacao createEntity() {
        Votacao votacao = new Votacao();
        votacao.setCpfAssociado("26644355072");
        votacao.setVoto(SimNaoEnum.SIM.getValor());
        votacao.setIdUser(123);
        return votacao;
    }


    @BeforeEach
    public void initTest() {
        votacao = createEntity();
    }

    @Test
    void save() {
        Votacao votacao = new Votacao(SimNaoEnum.SIM, 123, new Pauta(), new Sessao(), "26644355072");
        this.repository.save(votacao);
        assertThat(votacao.getId()).isNotNull();
    }


}