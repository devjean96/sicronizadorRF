package br.com.devjean.sicronizadorReceita.batch.job.step.reader.mapper;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import br.com.devjean.sicronizadorReceita.model.Conta;
import br.com.devjean.sicronizadorReceita.util.ConstantesUtil;

public class ContaFieldSetMapper implements FieldSetMapper<Conta>{

	@Override
	public Conta mapFieldSet(FieldSet fieldSet) throws BindException {
		Conta conta = new Conta();
		conta.setAgencia(fieldSet.readString(ConstantesUtil.AGENCIA));
		conta.setConta(fieldSet.readString(ConstantesUtil.CONTA));
		conta.setSaldo(Double.parseDouble(fieldSet.readString(ConstantesUtil.SALDO).replace(',', '.')));
		conta.setStatus(fieldSet.readString(ConstantesUtil.STATUS));
		return conta;
	}
	

	
}

