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

import com.tcc.barbersclub.model.entity.Barbearia;
import com.tcc.barbersclub.rest.exception.ResourceNotFoundException;
import com.tcc.barbersclub.service.BarbeariaService;

@RestController
@RequestMapping("/barbearias")
public class BarbeariaController {

    private final BarbeariaService barbeariaService;

    public BarbeariaController(BarbeariaService barbeariaService) {
        this.barbeariaService = barbeariaService;
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Barbearia>> listarTodos() {
        List<Barbearia> lista = barbeariaService.findAll();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Barbearia> buscarPorId(@PathVariable long id) {
        Barbearia barbearia = barbeariaService.findById(id);
        return new ResponseEntity<>(barbearia, HttpStatus.OK);
    }

    @PostMapping("save")
    public ResponseEntity<Barbearia> salvar(@RequestBody Barbearia barbearia) {
        Barbearia salvo = barbeariaService.save(barbearia);
        return new ResponseEntity<>(salvo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Barbearia> atualizar(@PathVariable long id, @RequestBody Barbearia barbearia) {
        Barbearia atualizado = barbeariaService.atualizar(id, barbearia);
        return new ResponseEntity<>(atualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable long id) {
        barbeariaService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
	@PutMapping("/inativar/{id}")
	public ResponseEntity<?> inativar(@PathVariable long id) {
		
		Barbearia _barbearia = barbeariaService.inativar(id);
		
		if(_barbearia != null) {
			
			return ResponseEntity.ok().body("Mensagem inativada com sucesso!");
		}
		
		throw new ResourceNotFoundException("Erro ao inativar a barbearia.");
	}
	
} 
