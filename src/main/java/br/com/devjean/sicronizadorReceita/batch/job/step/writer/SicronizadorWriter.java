package br.com.devjean.sicronizadorReceita.batch.job.step.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import br.com.devjean.sicronizadorReceita.model.Conta;
import br.com.devjean.sicronizadorReceita.util.ConstantesUtil;

@Configuration
public class SicronizadorWriter {

	private Resource outputResource = new FileSystemResource("file/resultadoReceita.csv");

	@Bean("fileItemWriter")
	public ItemWriter<Conta> fileItemWriter() {
		return items -> items.forEach(System.out::println);
	}

	@Bean("fileItemWriterCSV")
	public FlatFileItemWriter<Conta> writer() {
		FlatFileItemWriter<Conta> writer = new FlatFileItemWriter<>();
		DelimitedLineAggregator<Conta> lineAggregator = new DelimitedLineAggregator<Conta>();
		BeanWrapperFieldExtractor<Conta> fieldExtractor = new BeanWrapperFieldExtractor<Conta>();
		writer.setResource(outputResource);
		writer.setAppendAllowed(true);
		lineAggregator.setDelimiter(",");
		fieldExtractor.setNames(new String[] { ConstantesUtil.AGENCIA, ConstantesUtil.CONTA, ConstantesUtil.SALDO, ConstantesUtil.STATUS, ConstantesUtil.RESULTADO_RECEITA });
		lineAggregator.setFieldExtractor(fieldExtractor);
		
		writer.setLineAggregator(lineAggregator);
		
		return writer;
	}

}
