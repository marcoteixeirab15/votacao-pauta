package com.marco.votacaopauta.service;

import com.marco.votacaopauta.domain.Pauta;
import com.marco.votacaopauta.domain.Sessao;
import com.marco.votacaopauta.domain.enums.StatusEnum;
import com.marco.votacaopauta.repository.SessaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
class SessaoServiceTest {

    @Autowired
    private SessaoRepository repository;
    private Sessao sessao;

    public static Sessao createEntity() {
        Sessao sessao = new Sessao();
        sessao.setNome("Sessão Teste");
        sessao.setStatusSessao(StatusEnum.ATIVO);
        return sessao;
    }

    @BeforeEach
    public void initTest() {
        sessao = createEntity();
    }

    @Test
    void save() {
        Sessao sessao = new Sessao(1, "Sessao Teste", new Pauta(), StatusEnum.ATIVO);
        this.repository.save(sessao);
        assertThat(sessao.getId()).isNotNull();
        assertThat(sessao.getNome()).isEqualTo("Sessao Teste");
    }

    @Test
    void findAll() {
        repository.saveAndFlush(sessao);
        List<Sessao> sessaoList = this.repository.findAll();
        assertThat(sessaoList).isNotEmpty();
    }

    @Test
    void find() {
        Integer id = 1;
        repository.saveAndFlush(sessao);
        Optional<Sessao> sessao = this.repository.findById(id);
        assertThat(sessao).isNotNull();
    }

}