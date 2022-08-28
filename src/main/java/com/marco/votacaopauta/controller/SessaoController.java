package com.marco.votacaopauta.controller;

import com.marco.votacaopauta.domain.Sessao;
import com.marco.votacaopauta.service.SessaoService;
import com.marco.votacaopauta.service.dto.SessaoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/sessao")
public class SessaoController {

    private final SessaoService sessaoService;

    public SessaoController(SessaoService sessaoService) {
        this.sessaoService = sessaoService;
    }

    @GetMapping
    public List<Sessao> getAll() {
        return sessaoService.findAll();
    }

    @PostMapping("/save")
    public ResponseEntity<Void> save(@Validated @RequestBody SessaoDTO sessaoDTO) {
        Sessao sessao = sessaoService.fromDTO(sessaoDTO);
        sessao = sessaoService.save(sessao);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(sessao.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }


}
