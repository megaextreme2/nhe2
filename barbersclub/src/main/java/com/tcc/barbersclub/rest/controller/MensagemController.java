package com.tcc.barbersclub.rest.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcc.barbersclub.model.entity.Mensagem;
import com.tcc.barbersclub.rest.exception.ResourceNotFoundException;
import com.tcc.barbersclub.service.MensagemService;

@RestController
@RequestMapping("/mensagem")
public class MensagemController {
	
	private MensagemService mensagemService; 
	// Source => Generate Constructor using Fields...

	public MensagemController(MensagemService mensagemService) {
		super();
		this.mensagemService = mensagemService;
	}
	
	@GetMapping("/test")
	public String getTest() {
		return "Olá, Mensagem!";
	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<Mensagem> findById(@PathVariable long id){
		
		Mensagem mensagem = mensagemService.findById(id);
		if(mensagem != null) {
			return new ResponseEntity<Mensagem>(mensagem, HttpStatus.OK);
		}
		throw new ResourceNotFoundException("Mensagem não encontrada!");
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<List<Mensagem>> findAll(){
		List<Mensagem> mensagens = mensagemService.findAll();
		return new ResponseEntity<List<Mensagem>>(mensagens, HttpStatus.OK);	
	}
	
	@PostMapping("save")
	public ResponseEntity<?> save(@RequestBody Mensagem mensagem){
		
		Mensagem _mensagem = mensagemService.save(mensagem);
		return ResponseEntity.ok()
				.body("Mensagem cadastrada com sucesso!");
	}
	
	@PutMapping("/inativar/{id}")
	public ResponseEntity<?> inativar(@PathVariable long id) {
		
		Mensagem _mensagem = mensagemService.inativar(id);
		
		if(_mensagem != null) {
			
			return ResponseEntity.ok().body("Mensagem inativada com sucesso!");
		}
		
		throw new ResourceNotFoundException("Erro ao inativar a mensagem.");
	}
	
	@PutMapping("/marcarComoLida/{id}")
	public ResponseEntity<?> marcarComoLida(@PathVariable long id) {
		
		Mensagem _mensagem = mensagemService.marcarComoLida(id);
		
		if(_mensagem != null) {
			
			return ResponseEntity.ok().body(_mensagem);
		}
		
		throw new ResourceNotFoundException("Erro ao ler a mensagem.");
	}

}
