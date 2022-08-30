package com.marco.votacaopauta.service;

import com.marco.votacaopauta.domain.Votacao;
import com.marco.votacaopauta.domain.enums.SimNaoEnum;
import com.marco.votacaopauta.domain.enums.StatusEnum;
import com.marco.votacaopauta.repository.VotacaoRepository;
import com.marco.votacaopauta.service.dto.StatusVoteDTO;
import com.marco.votacaopauta.service.dto.VotacaoDTO;
import com.marco.votacaopauta.service.exception.DataIntegrityException;
import com.marco.votacaopauta.service.exception.ObjectNotFoundException;
import com.marco.votacaopauta.service.exception.VotacaoException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class VotacaoService {

    private final String ABLE_TO_VOTE = "ABLE_TO_VOTE";
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

    public Votacao save(Votacao votacao) {
        validarVoto(votacao);
        votacao = votacaoRepository.save(votacao);
        pautaService.contadorVotos(votacao);
        return votacao;
    }

    private void validarVoto(Votacao votacao) {

        if (!cpfValido(votacao.getCpfAssociado())) {
            throw new VotacaoException("CPF inválido");
        }

        if (getCPFAptoParaVotar(votacao.getCpfAssociado())) {
            throw new VotacaoException("CPF não está apto a votar");
        }

        if (votacao.getSessao().getStatusSessao() == StatusEnum.toEnum(2)) {
            throw new VotacaoException("A sessão está encerrada!.");
        }

        List<Votacao> votacaoList = votacaoRepository.findVotacaoByIdUserAndPauta_Id(votacao.getIdUser(), votacao.getPauta().getId());

        if (!votacaoList.isEmpty()) {
            throw new VotacaoException("A pauta já foi votada");
        }

    }

    private Boolean getCPFAptoParaVotar(String cpfAssociado) {
        StatusVoteDTO statusVoteDTO = null;
        String url = "https://user-info.herokuapp.com/users/";
        RestTemplate restTemplate = new RestTemplate();
        try {
            statusVoteDTO = restTemplate.getForObject(url + cpfAssociado, StatusVoteDTO.class);
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new ObjectNotFoundException("CPF não foi encontrado na base de associados");
            }
            throw new ObjectNotFoundException("API: " + url + " , não está disponível");
        }

        assert statusVoteDTO != null;
        return ABLE_TO_VOTE.equals(statusVoteDTO.getStatus());
    }

    private boolean cpfValido(String cpfAssociado) {
        return !cpfAssociado.equals("00000000000")
                && !cpfAssociado.equals("11111111111") && !cpfAssociado.equals("22222222222")
                && !cpfAssociado.equals("33333333333") && !cpfAssociado.equals("44444444444")
                && !cpfAssociado.equals("55555555555") && !cpfAssociado.equals("66666666666")
                && !cpfAssociado.equals("77777777777") && !cpfAssociado.equals("88888888888")
                && !cpfAssociado.equals("99999999999");
    }


    public Votacao fromDTO(VotacaoDTO votacaoDTO) {

        if(votacaoDTO.getVoto() != 1 && votacaoDTO.getVoto() != 2){
            throw new DataIntegrityException("Informar no campo status apenas o valor 1 caso a resposta seja SIM ou 2 caso a resposta seja NÃO");
        }

        return new Votacao(SimNaoEnum.toEnum(votacaoDTO.getVoto()), votacaoDTO.getIdUser(), pautaService.find(votacaoDTO.getPauta()), sessaoService.find(votacaoDTO.getSessao()), votacaoDTO.getCpfAssociado());
    }
}
