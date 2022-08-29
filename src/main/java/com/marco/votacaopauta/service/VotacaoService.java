package com.marco.votacaopauta.service;

import com.marco.votacaopauta.domain.Votacao;
import com.marco.votacaopauta.domain.enums.SimNaoEnum;
import com.marco.votacaopauta.domain.enums.StatusEnum;
import com.marco.votacaopauta.repository.VotacaoRepository;
import com.marco.votacaopauta.service.exception.ObjectNotFoundException;
import com.marco.votacaopauta.service.dto.VotacaoDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VotacaoService {

    private final VotacaoRepository votacaoRepository;
    private final PautaService pautaService;
    private final SessaoService sessaoService;

    public VotacaoService(VotacaoRepository votacaoRepository, PautaService pautaService, SessaoService sessaoService) {
        this.votacaoRepository = votacaoRepository;
        this.pautaService = pautaService;
        this.sessaoService = sessaoService;
    }

    public Votacao find(Integer id) {
        Optional<Votacao> pauta = votacaoRepository.findById(id);
        return pauta.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado. Id: " + id + ", Tipo: " + Votacao.class.getName()));
    }

    public List<Votacao> findAll() {
        return votacaoRepository.findAll();
    }

    public Votacao save(Votacao votacao) throws Exception {
        validaVoto(votacao);
        votacao =  votacaoRepository.save(votacao);
        pautaService.contadorVotos(votacao);
        return votacao;
    }

    private void validaVoto(Votacao votacao) throws Exception {

        if(votacao.getSessao().getStatusSessao() == StatusEnum.toEnum(2)){
            throw new Exception("A sessão está encerrada!.");
        }

        List<Votacao> votacaoList = votacaoRepository.findVotacaoByIdUserAndPauta_Id(votacao.getIdUser(), votacao.getPauta().getId());

        if(!votacaoList.isEmpty()){
            throw new Exception("A pauta já foi votada");
        }

    }


    public Votacao fromDTO(VotacaoDTO votacaoDTO) {
        return new Votacao(SimNaoEnum.toEnum(votacaoDTO.getVoto()), votacaoDTO.getIdUser(), pautaService.find(votacaoDTO.getPauta()), sessaoService.find(votacaoDTO.getSessao()));
    }
}
