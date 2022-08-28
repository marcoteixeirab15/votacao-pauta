package com.marco.votacaopauta.service;

import com.marco.votacaopauta.domain.Sessao;
import com.marco.votacaopauta.domain.enums.StatusEnum;
import com.marco.votacaopauta.repository.SessaoRepository;
import com.marco.votacaopauta.service.Exception.ObjectNotFoundException;
import com.marco.votacaopauta.service.dto.SessaoDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

@Service
public class SessaoService {

    private final SessaoRepository sessaoRepository;
    private final PautaService pautaService;

    public SessaoService(SessaoRepository sessaoRepository, PautaService pautaService) {
        this.sessaoRepository = sessaoRepository;
        this.pautaService = pautaService;
    }

    public Sessao find(Integer id) {
        Optional<Sessao> pauta = sessaoRepository.findById(id);
        return pauta.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado. Id: " + id + ", Tipo: " + Sessao.class.getName()));
    }

    public List<Sessao> findAll() {
        return sessaoRepository.findAll();
    }

    public Sessao save(Sessao sessao) {
        sessao =  sessaoRepository.save(sessao);
        return sessao;
    }

    public Sessao update(Sessao sessao) {
        Sessao newSessao = find(sessao.getId());
        newSessao.setNome(sessao.getNome());
        newSessao.setDuracao(sessao.getDuracao());
        newSessao.setStatusSessao(sessao.getStatusSessao() != null ? sessao.getStatusSessao() : newSessao.getStatusSessao());
        return save(newSessao);

    }




    private int getDelay(Sessao sessao) {
        return sessao.getDuracao() * 60000;
    }

    public Sessao fromDTO(SessaoDTO sessaoDTO) {
        return new Sessao(sessaoDTO.getDuracao(), sessaoDTO.getNome(), pautaService.find(sessaoDTO.getPauta()), StatusEnum.toEnum(1));
    }
}
