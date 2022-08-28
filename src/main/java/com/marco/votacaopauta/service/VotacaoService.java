package com.marco.votacaopauta.service;

import com.marco.votacaopauta.domain.Votacao;
import com.marco.votacaopauta.domain.enums.SimNaoEnum;
import com.marco.votacaopauta.domain.enums.StatusEnum;
import com.marco.votacaopauta.repository.VotacaoRepository;
import com.marco.votacaopauta.service.Exception.ObjectNotFoundException;
import com.marco.votacaopauta.service.dto.VotacaoDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

@Service
public class VotacaoService {

    private final VotacaoRepository votacaoRepository;
    private final PautaService pautaService;

    public VotacaoService(VotacaoRepository votacaoRepository, PautaService pautaService) {
        this.votacaoRepository = votacaoRepository;
        this.pautaService = pautaService;
    }

    public Votacao find(Integer id) {
        Optional<Votacao> pauta = votacaoRepository.findById(id);
        return pauta.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado. Id: " + id + ", Tipo: " + Votacao.class.getName()));
    }

    public List<Votacao> findAll() {
        return votacaoRepository.findAll();
    }

    public Votacao save(Votacao votacao) {
        votacao =  votacaoRepository.save(votacao);
        return votacao;
    }


    public Votacao fromDTO(VotacaoDTO votacaoDTO) {
        return new Votacao(SimNaoEnum.toEnum(votacaoDTO.getVoto()), votacaoDTO.getIdUser(), pautaService.find(votacaoDTO.getPauta()));
    }
}
