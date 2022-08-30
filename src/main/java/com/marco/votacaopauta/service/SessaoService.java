package com.marco.votacaopauta.service;

import com.marco.votacaopauta.domain.Sessao;
import com.marco.votacaopauta.domain.enums.StatusEnum;
import com.marco.votacaopauta.repository.SessaoRepository;
import com.marco.votacaopauta.service.dto.SessaoDTO;
import com.marco.votacaopauta.service.exception.DataIntegrityException;
import com.marco.votacaopauta.service.exception.ObjectNotFoundException;
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

        List<Sessao> sessaoList = sessaoRepository.findByPauta_Id(sessao.getPauta().getId());

        if(!sessaoList.isEmpty()){
            throw new DataIntegrityException("Já existe uma sessão para essa pauta.");
        }

        sessao = sessaoRepository.save(sessao);
        schedule(sessao);
        return sessao;
    }

    public Sessao update(Sessao sessao) {
        Sessao newSessao = find(sessao.getId());
        newSessao.setNome(sessao.getNome());
        newSessao.setDuracao(sessao.getDuracao());
        newSessao.setStatusSessao(sessao.getStatusSessao() != null ? sessao.getStatusSessao() : newSessao.getStatusSessao());
        return sessaoRepository.save(newSessao);

    }

    public void schedule(Sessao sessao) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                sessao.setStatusSessao(StatusEnum.toEnum(2));
                update(sessao);
                timer.cancel();
            }
        }, getDelay(sessao));
    }


    private int getDelay(Sessao sessao) {
        return sessao.getDuracao() * 60000;
    }

    public Sessao fromDTO(SessaoDTO sessaoDTO) {
        return new Sessao(sessaoDTO.getDuracao(), sessaoDTO.getNome(), pautaService.find(sessaoDTO.getPauta()), StatusEnum.toEnum(1));
    }
}
