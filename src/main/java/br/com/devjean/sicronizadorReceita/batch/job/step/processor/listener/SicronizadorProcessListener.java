package br.com.devjean.sicronizadorReceita.batch.job.step.processor.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemProcessListener;
import org.springframework.stereotype.Component;

import br.com.devjean.sicronizadorReceita.model.Conta;

@Component
public class SicronizadorProcessListener implements ItemProcessListener<Conta, Conta>{
	
	public static final Logger LOG = LoggerFactory.getLogger(SicronizadorProcessListener.class);
	
	@Override
	public void beforeProcess(Conta item) {
		LOG.debug("[Sicronizador PROCESSOR] Iniciando..");
	}

	@Override
	public void afterProcess(Conta item, Conta result) {
		LOG.info("[Sicronizador PROCESSOR] Item {} Processado com Sucesso", item.getConta());
	}

	@Override
	public void onProcessError(Conta item, Exception e) {
		LOG.error("[Sicronizador PROCESSOR] Erro: {}", e);
	}

}
