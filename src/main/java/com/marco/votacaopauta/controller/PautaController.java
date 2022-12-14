package com.marco.votacaopauta.controller;

import com.marco.votacaopauta.domain.Pauta;
import com.marco.votacaopauta.service.PautaService;
import com.marco.votacaopauta.service.dto.PautaDTO;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/pauta")
public class PautaController {

    private final PautaService pautaService;

    public PautaController(PautaService pautaService) {
        this.pautaService = pautaService;
    }

    @GetMapping
    public ResponseEntity<List<Pauta>> getAll() {
        return ResponseEntity.ok().body(pautaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pauta> get(@PathVariable Integer id){
        return ResponseEntity.ok().body(pautaService.find(id));
    }


    @PostMapping("/save")
    public ResponseEntity<Void> save(@Valid @RequestBody PautaDTO pautaDTO) {
        Pauta pauta = pautaService.fromDTO(pautaDTO);
        pauta = pautaService.save(pauta);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pauta.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }


}
