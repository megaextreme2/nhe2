package com.tcc.barbersclub.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tcc.barbersclub.model.entity.Agendamento;
import com.tcc.barbersclub.model.repository.AgendamentoRepository;
import com.tcc.barbersclub.rest.exception.ResourceNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;

    public AgendamentoService(AgendamentoRepository agendamentoRepository) {
        this.agendamentoRepository = agendamentoRepository;
    }

    public List<Agendamento> findAll() {
        return agendamentoRepository.findAll();
    }

    public Agendamento findById(long id) {
        return agendamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agendamento com ID " + id + " não encontrado"));
    }

    @Transactional
    public Agendamento save(Agendamento agendamento) {
        return agendamentoRepository.save(agendamento);
    }

    @Transactional
    public Agendamento atualizar(long id, Agendamento dados) {
        Agendamento agendamento = findById(id);
        agendamento.setDataAgendada(dados.getDataAgendada());
        agendamento.setDescricao(dados.getDescricao());
        agendamento.setStatusAgendamento(dados.getStatusAgendamento());
        agendamento.setUsuario(dados.getUsuario());
        agendamento.setAgendamentoPai(dados.getAgendamentoPai());
        return agendamentoRepository.save(agendamento);
    }

    @Transactional
    public void deletar(long id) {
        Agendamento agendamento = findById(id);
        agendamentoRepository.delete(agendamento);
    }
    
	@Transactional
	public Agendamento inativar(long id) {
		Optional<Agendamento> _agendamento = agendamentoRepository.findById(id);
		
		if (_agendamento.isPresent()) {
			Agendamento agendamentoAtualizado = _agendamento.get();
			agendamentoAtualizado.setStatusAgendamento("INATIVO");
			
			return agendamentoRepository.save(agendamentoAtualizado);
		}

		return null;
	}
}

