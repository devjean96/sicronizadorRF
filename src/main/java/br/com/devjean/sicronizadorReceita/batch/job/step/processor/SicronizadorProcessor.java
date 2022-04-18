package br.com.devjean.sicronizadorReceita.batch.job.step.processor;

import org.springframework.batch.item.ItemProcessor;

import br.com.devjean.sicronizadorReceita.model.Conta;
import br.com.devjean.sicronizadorReceita.service.ReceitaService;

public class SicronizadorProcessor implements ItemProcessor<Conta, Conta>{
	
	private ReceitaService service;
	
	public SicronizadorProcessor(ReceitaService service) {
		this.service = service; 
	}

	@Override
	public Conta process(Conta item) throws Exception {
		boolean resultado = service.atualizarConta(item.getAgencia(), item.getConta(), item.getSaldo(), item.getStatus());
		item.setResultadoReceita(resultado);
		return item;
	}

}
