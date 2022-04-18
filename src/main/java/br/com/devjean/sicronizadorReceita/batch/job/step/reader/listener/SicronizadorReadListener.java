package br.com.devjean.sicronizadorReceita.batch.job.step.reader.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.stereotype.Component;

import br.com.devjean.sicronizadorReceita.model.Conta;

@Component
public class SicronizadorReadListener implements ItemReadListener<Conta>{
	
	public static final Logger LOG = LoggerFactory.getLogger(SicronizadorReadListener.class);

	@Override
	public void beforeRead() {
		LOG.debug("[Sicronizador READER] Iniciando..");
	}

	@Override
	public void afterRead(Conta item) {
		LOG.info("[Sicronizador READER] Leitura do Item: {}", item.getConta());
	}

	@Override
	public void onReadError(Exception ex) {
		LOG.error("[Sicronizador READER] Erro: {}", ex);
	}

}
