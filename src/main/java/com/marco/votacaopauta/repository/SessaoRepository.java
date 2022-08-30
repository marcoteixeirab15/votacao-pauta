package com.marco.votacaopauta.repository;

import com.marco.votacaopauta.domain.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessaoRepository extends JpaRepository<Sessao, Integer> {

    @Query
    List<Sessao> findByPauta_Id(Integer id);
}
