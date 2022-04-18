package br.com.devjean.sicronizadorReceita.batch.job.step.reader;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import br.com.devjean.sicronizadorReceita.batch.job.step.reader.mapper.ContaFieldSetMapper;
import br.com.devjean.sicronizadorReceita.model.Conta;
import br.com.devjean.sicronizadorReceita.util.ConstantesUtil;

@Configuration
public class SicronizadorReader {
	
	@StepScope
	@Bean("fileItemReader")
	public FlatFileItemReader<Conta> fileItemReader(@Value("#{jobParameters['arquivo']}") Resource resource) {
	   return new FlatFileItemReaderBuilder<Conta>()
	   .resource(resource)
	   .encoding("ISO-8859-3")
	   .name("CSV-Reader")
	   .linesToSkip(1)
	   .lineMapper(lineMapper())
	   .build();
	}
	
	@Bean
	public LineMapper<Conta> lineMapper() {
	   DefaultLineMapper<Conta> defaultLineMapper = new DefaultLineMapper<>();
	   DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();

	   lineTokenizer.setDelimiter(";");

	   lineTokenizer.setNames(ConstantesUtil.AGENCIA,
	                          ConstantesUtil.CONTA,
	                          ConstantesUtil.SALDO,
	                          ConstantesUtil.STATUS);
	   
	   lineTokenizer.setStrict(false);

	   ContaFieldSetMapper contaFieldSetMapper = new ContaFieldSetMapper();

	   defaultLineMapper.setLineTokenizer(lineTokenizer);
	   defaultLineMapper.setFieldSetMapper(contaFieldSetMapper);

	   return defaultLineMapper;
	}

}
