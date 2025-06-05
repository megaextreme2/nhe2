package com.tcc.barbersclub.rest.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcc.barbersclub.model.entity.Servico;
import com.tcc.barbersclub.rest.exception.ResourceNotFoundException;
import com.tcc.barbersclub.service.ServicoService;

@RestController
@RequestMapping("/servicos")
public class ServicoController {

    private final ServicoService servicoService;

    public ServicoController(ServicoService servicoService) {
        this.servicoService = servicoService;
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Servico>> listarTodos() {
        List<Servico> servicos = servicoService.findAll();
        return new ResponseEntity<>(servicos, HttpStatus.OK);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Servico> buscarPorId(@PathVariable long id) {
        Servico servico = servicoService.findById(id);
        return new ResponseEntity<>(servico, HttpStatus.OK);
    }

    @PostMapping("save")
    public ResponseEntity<Servico> salvar(@RequestBody Servico servico) {
        Servico novoServico = servicoService.save(servico);
        return new ResponseEntity<>(novoServico, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Servico> atualizar(@PathVariable long id, @RequestBody Servico servico) {
        Servico atualizado = servicoService.atualizar(id, servico);
        return new ResponseEntity<>(atualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable long id) {
        servicoService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
	@PutMapping("/inativar/{id}")
	public ResponseEntity<?> inativar(@PathVariable long id) {
		
		Servico _servico = servicoService.inativar(id);
		
		if(_servico != null) {
			
			return ResponseEntity.ok().body("Mensagem inativada com sucesso!");
		}
		
		throw new ResourceNotFoundException("Erro ao inativar o servico.");
	}
	
} 

