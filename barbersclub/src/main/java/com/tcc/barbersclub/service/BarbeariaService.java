package com.tcc.barbersclub.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tcc.barbersclub.model.entity.Barbearia;
import com.tcc.barbersclub.model.repository.BarbeariaRepository;
import com.tcc.barbersclub.rest.exception.ResourceNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class BarbeariaService {

    private final BarbeariaRepository barbeariaRepository;

    public BarbeariaService(BarbeariaRepository barbeariaRepository) {
        this.barbeariaRepository = barbeariaRepository;
    }

    public List<Barbearia> findAll() {
        return barbeariaRepository.findAll();
    }

    public Barbearia findById(long id) {
        return barbeariaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Barbearia com ID " + id + " não encontrada"));
    }

    @Transactional
    public Barbearia save(Barbearia barbearia) {
        return barbeariaRepository.save(barbearia);
    }

    @Transactional
    public Barbearia atualizar(long id, Barbearia dados) {
        Barbearia barbearia = findById(id);
        barbearia.setNome(dados.getNome());
        barbearia.setDescricao(dados.getDescricao());
        barbearia.setEndereco(dados.getEndereco());
        barbearia.setUsuario(dados.getUsuario());
        barbearia.setStatusBarbearia(dados.getStatusBarbearia());
        return barbeariaRepository.save(barbearia);
    }

    @Transactional
    public void deletar(long id) {
        Barbearia barbearia = findById(id);
        barbeariaRepository.delete(barbearia);
    }
    
	@Transactional
	public Barbearia inativar(long id) {
		Optional<Barbearia> _barbearia = barbeariaRepository.findById(id);
		
		if (_barbearia.isPresent()) {
			Barbearia barbeariaAtualizada = _barbearia.get();
			barbeariaAtualizada.setStatusBarbearia("INATIVO");
			
			return barbeariaRepository.save(barbeariaAtualizada);
		}

		return null;
	}
}

