package com.marco.votacaopauta.repository;

import com.marco.votacaopauta.domain.Votacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VotacaoRepository extends JpaRepository<Votacao, Integer> {
    @Query
    public List<Votacao> findVotacaoByIdUserAndPauta_Id(Integer idUser, Integer pautaId);
}
