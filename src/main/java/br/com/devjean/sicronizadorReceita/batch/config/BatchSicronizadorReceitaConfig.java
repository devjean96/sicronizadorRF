package br.com.devjean.sicronizadorReceita.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.devjean.sicronizadorReceita.batch.job.step.processor.SicronizadorProcessor;
import br.com.devjean.sicronizadorReceita.batch.job.step.processor.listener.SicronizadorProcessListener;
import br.com.devjean.sicronizadorReceita.batch.job.step.reader.listener.SicronizadorReadListener;
import br.com.devjean.sicronizadorReceita.model.Conta;
import br.com.devjean.sicronizadorReceita.service.ReceitaService;


@Configuration
@EnableBatchProcessing
public class BatchSicronizadorReceitaConfig {
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private SicronizadorReadListener readListener;
	
	@Autowired
	private SicronizadorProcessListener processListener;
	
	@Autowired
    @Qualifier(value = "fileItemReader")
    ItemReader<Conta> reader;
	
	@Autowired
    @Qualifier(value = "fileItemWriterCSV")
    ItemWriter<Conta> writer;
	
	@Bean
	public SicronizadorProcessor processor() {
		return new SicronizadorProcessor(new ReceitaService());
	}
	
	@Bean
	public Job job() {
	  return this.jobBuilderFactory
	    .get("JobSicronizadorReceitaFederal")
	    .start(step())
	    .build();
	}
	
	@Bean
    public Step step() {
      return stepBuilderFactory.get("StepSicronizador")
    		  .<Conta, Conta> chunk(1)
    	       .reader(reader).listener(readListener)
    	       .processor(processor()).listener(processListener).faultTolerant()
    	       .retry(Exception.class).retryLimit(1)
    	       .skip(Exception.class).skipLimit(10)
    	       .writer(writer)
    	       .allowStartIfComplete(true)
    		   .build();
    		  
	}

}
