package com.tcc.barbersclub.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tcc.barbersclub.model.entity.Mensagem;
import com.tcc.barbersclub.model.repository.MensagemRepository;

import jakarta.transaction.Transactional;

@Service
public class MensagemService {
	private MensagemRepository mensagemRepository;
	
	public MensagemService(MensagemRepository mensagemRepository) {
		super();
		this.mensagemRepository = mensagemRepository;
	}
	
	public Mensagem findById(long id) {
		Optional<Mensagem> mensagem = mensagemRepository.findById(id);
		if(mensagem.isPresent()) {
			return mensagem.get();
		}
		return null;
		}
	
	public List<Mensagem> findAll(){
		List<Mensagem> mensagens = mensagemRepository.findAll();
		return mensagens;
	}
	
	@Transactional
	public Mensagem save(Mensagem mensagem) {
		
		mensagem.setDataMensagem(LocalDateTime.now());
		mensagem.setStatusMensagem("ATIVO");
		
		return mensagemRepository.save(mensagem);
	}
	
	@Transactional
	public Mensagem inativar(long id) {
		Optional<Mensagem> _mensagem = mensagemRepository.findById(id);
		
		if (_mensagem.isPresent()) {
			Mensagem mensagemAtualizada = _mensagem.get();
			
			mensagemAtualizada.setDataMensagem(LocalDateTime.now());
			mensagemAtualizada.setStatusMensagem("INATIVO");
			
			return mensagemRepository.save(mensagemAtualizada);
		}

		return null;
	}
	
	// Método p/ quando abrir a mensagem, mostrar que ela já fpra visualizada/aberta/lida.
	@Transactional
	public Mensagem marcarComoLida(long id) {
		Optional<Mensagem> _mensagem = mensagemRepository.findById(id);
		
		if (_mensagem.isPresent()) {
			Mensagem mensagemAtualizada = _mensagem.get();
			
			mensagemAtualizada.setDataMensagem(LocalDateTime.now());
			mensagemAtualizada.setStatusMensagem("LIDA");
			
			return mensagemRepository.save(mensagemAtualizada);
		}

		return null;
	}
	
}



































