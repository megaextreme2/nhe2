package com.tcc.barbersclub.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tcc.barbersclub.model.entity.Servico;
import com.tcc.barbersclub.model.repository.ServicoRepository;
import com.tcc.barbersclub.rest.exception.ResourceNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class ServicoService {

    private final ServicoRepository servicoRepository;

    public ServicoService(ServicoRepository servicoRepository) {
        this.servicoRepository = servicoRepository;
    }

    public List<Servico> findAll() {
        return servicoRepository.findAll();
    }

    public Servico findById(long id) {
        return servicoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Serviço com ID " + id + " não encontrado"));
    }

    @Transactional
    public Servico save(Servico servico) {
        return servicoRepository.save(servico);
    }

    @Transactional
    public Servico atualizar(long id, Servico servicoAtualizado) {
        Servico servico = findById(id);
        servico.setNome(servicoAtualizado.getNome());
        servico.setDescricao(servicoAtualizado.getDescricao());
        servico.setDuracao(servicoAtualizado.getDuracao());
        servico.setPreco(servicoAtualizado.getPreco());
        servico.setAvaliacao(servicoAtualizado.getAvaliacao());
        servico.setFeedback(servicoAtualizado.getFeedback());
        servico.setCategoria(servicoAtualizado.getCategoria());
        servico.setPontosGanhos(servicoAtualizado.getPontosGanhos());
        servico.setStatusServico(servicoAtualizado.isStatusServico());
        servico.setBarbearia(servicoAtualizado.getBarbearia());
        return servicoRepository.save(servico);
    }

    @Transactional
    public void deletar(long id) {
        Servico servico = findById(id);
        servicoRepository.delete(servico);
    }
	@Transactional
	public Servico inativar(long id) {
		Optional<Servico> _servico = servicoRepository.findById(id);
		
		if (_servico.isPresent()) {
			Servico servicoAtualizado = _servico.get();
			servicoAtualizado.setStatusServico("INATIVO");
			
			return servicoRepository.save(servicoAtualizado);
		}

		return null;
	}
}
