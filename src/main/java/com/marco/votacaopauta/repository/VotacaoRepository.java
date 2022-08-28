package com.marco.votacaopauta.repository;

import com.marco.votacaopauta.domain.Votacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotacaoRepository extends JpaRepository<Votacao, Integer> {
}
