package com.springbatch.contasbancarias.step;

import com.springbatch.contasbancarias.dominio.Cliente;
import com.springbatch.contasbancarias.dominio.Conta;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class CriacaoContasStepConfig {

	private JobRepository jobRepository;
	private PlatformTransactionManager platformTransactionManager;

	public CriacaoContasStepConfig(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
		this.jobRepository = jobRepository;
		this.platformTransactionManager = platformTransactionManager;
	}
	
	@Bean
	public Step criacaoContasStep(
			ItemReader<Cliente> leituraClientesReader, 
			ItemProcessor<Cliente, Conta> geracaoContaProcessor,
//			ItemWriter<Conta> jdbcContaWriter //
			CompositeItemWriter<Conta> compositeContaWriterConfig
	) {
		return new StepBuilder("criacaoContasStep", jobRepository)
				.<Cliente, Conta>chunk(100, platformTransactionManager) // A quantidade de insert no banco de dados é com base no chunk, portanto se chunk possuir '1' de valor, sera criada uma transação para cada insert, como estamos usando '100' de chunk, tudo esta sendo feito em uma transação
				.reader(leituraClientesReader)
				.processor(geracaoContaProcessor)
				.writer(compositeContaWriterConfig)
				.build();
	}
}
