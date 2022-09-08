package com.marco.votacaopauta.service;

import com.marco.votacaopauta.domain.Pauta;
import com.marco.votacaopauta.domain.enums.StatusEnum;
import com.marco.votacaopauta.repository.PautaRepository;
import com.marco.votacaopauta.service.dto.PautaDTO;
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
class PautaServiceTest {

    @Autowired
    private PautaRepository repository;

    private Pauta pauta;

    public static Pauta createEntity() {
        Pauta pauta = new Pauta();
        pauta.setTitulo("Titulo Pauta");
        pauta.setDescricao("Descricao Pauta");
        pauta.setStatusPauta(StatusEnum.ATIVO);
        return pauta;
    }


    @BeforeEach
    public void initTest() {
        pauta = createEntity();
    }

    @Test
    void save() {
        Pauta pauta = new Pauta("Pauta Teste", "Pauta Teste Descricao", StatusEnum.ATIVO);
        this.repository.save(pauta);
        assertThat(pauta.getId()).isNotNull();
        assertThat(pauta.getDescricao()).isEqualTo("Pauta Teste Descricao");
    }

    @Test
    void findAll() {
        repository.saveAndFlush(pauta);
        List<Pauta> pautaList = this.repository.findAll();
        assertThat(pautaList).isNotEmpty();
    }

    @Test
    void find() {
        Integer id = 1;
        repository.saveAndFlush(pauta);
        Optional<Pauta> pauta = this.repository.findById(id);
        assertThat(pauta).isNotNull();
    }

}