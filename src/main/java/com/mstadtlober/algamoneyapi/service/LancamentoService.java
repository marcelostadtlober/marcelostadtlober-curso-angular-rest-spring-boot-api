package com.mstadtlober.algamoneyapi.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mstadtlober.algamoneyapi.model.Lancamento;
import com.mstadtlober.algamoneyapi.model.Pessoa;
import com.mstadtlober.algamoneyapi.repository.LancamentoRepository;
import com.mstadtlober.algamoneyapi.repository.PessoaRepository;
import com.mstadtlober.algamoneyapi.service.exception.PessoaInexistenteOuInativaException;

@Service
public class LancamentoService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private LancamentoRepository lancamentoRepository;

	public Lancamento save(@Valid Lancamento lancamento) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(lancamento.getPessoa().getCodigo());
		
		if (!pessoa.isPresent() || pessoa.get().isInativo()) {
			throw new PessoaInexistenteOuInativaException();
		}
		
		return lancamentoRepository.save(lancamento);
	}	

}
