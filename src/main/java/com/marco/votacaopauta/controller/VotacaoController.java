package com.marco.votacaopauta.controller;

import com.marco.votacaopauta.domain.Votacao;
import com.marco.votacaopauta.service.VotacaoService;
import com.marco.votacaopauta.service.dto.VotacaoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/votacao")
public class VotacaoController {

    private final VotacaoService votacaoService;

    public VotacaoController(VotacaoService votacaoService) {
        this.votacaoService = votacaoService;
    }


    @PostMapping("/save")
    public ResponseEntity<Void> save(@Validated @RequestBody VotacaoDTO votacaoDTO) {
        Votacao votacao = votacaoService.fromDTO(votacaoDTO);
        votacao = votacaoService.save(votacao);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(votacao.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }


}
