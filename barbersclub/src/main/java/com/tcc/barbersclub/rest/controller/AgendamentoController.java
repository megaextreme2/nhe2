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

import com.tcc.barbersclub.model.entity.Agendamento;
import com.tcc.barbersclub.rest.exception.ResourceNotFoundException;
import com.tcc.barbersclub.service.AgendamentoService;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    public AgendamentoController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Agendamento>> listarTodos() {
        List<Agendamento> lista = agendamentoService.findAll();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Agendamento> buscarPorId(@PathVariable long id) {
        Agendamento agendamento = agendamentoService.findById(id);
        return new ResponseEntity<>(agendamento, HttpStatus.OK);
    }

    @PostMapping("save")
    public ResponseEntity<Agendamento> salvar(@RequestBody Agendamento agendamento) {
        Agendamento salvo = agendamentoService.save(agendamento);
        return new ResponseEntity<>(salvo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Agendamento> atualizar(@PathVariable long id, @RequestBody Agendamento agendamento) {
        Agendamento atualizado = agendamentoService.atualizar(id, agendamento);
        return new ResponseEntity<>(atualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable long id) {
        agendamentoService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
	@PutMapping("/inativar/{id}")
	public ResponseEntity<?> inativar(@PathVariable long id) {
		
		Agendamento _agendamento = agendamentoService.inativar(id);
		
		if(_agendamento != null) {
			
			return ResponseEntity.ok().body("Mensagem inativada com sucesso!");
		}
		
		throw new ResourceNotFoundException("Erro ao inativar o agendamento.");
	}
	
} 

