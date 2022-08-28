package com.marco.votacaopauta.service;

import com.marco.votacaopauta.domain.Pauta;
import com.marco.votacaopauta.domain.Votacao;
import com.marco.votacaopauta.domain.enums.StatusEnum;
import com.marco.votacaopauta.repository.PautaRepository;
import com.marco.votacaopauta.service.Exception.ObjectNotFoundException;
import com.marco.votacaopauta.service.dto.PautaDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PautaService {

    private final PautaRepository pautaRepository;

    public PautaService(PautaRepository pautaRepository) {
        this.pautaRepository = pautaRepository;
    }

    public Pauta find(Integer id) {
        Optional<Pauta> pauta = pautaRepository.findById(id);
        return pauta.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado. Id: " + id + ", Tipo: " + Pauta.class.getName()));
    }

    public List<Pauta> findAll() {
        return pautaRepository.findAll();
    }

    public Pauta save(Pauta pauta) {
        return pautaRepository.save(pauta);
    }

    public void contadorVotos(Votacao votacao) {
        Pauta pauta = votacao.getPauta();
        if(votacao.getVoto() == 1){
            pauta.setQtdVotosSim(pauta.getQtdVotosSim() + 1);
        }else {
            pauta.setQtdVotosNao(pauta.getQtdVotosNao() + 1);
        }

        save(pauta);
    }

    public Pauta fromDTO(PautaDTO pautaDTO) {
        return new Pauta(pautaDTO.getTitulo(), pautaDTO.getDescricao(), StatusEnum.toEnum(pautaDTO.getStatus()));
    }

}
