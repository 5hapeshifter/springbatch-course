package com.springbatch.processadorclassifier.step;

import com.springbatch.processadorclassifier.dominio.Cliente;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ProcessadorClassifierStepConfig {

	private JobRepository jobRepository;
	private PlatformTransactionManager platformTransactionManager;

	public ProcessadorClassifierStepConfig(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
		this.jobRepository = jobRepository;
		this.platformTransactionManager = platformTransactionManager;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public Step processadorClassifierStep(
			FlatFileItemReader processadorClassifierReader,
			ItemProcessor processadorClassifierProcessor,
			ItemWriter processadorClassifierWriter) {
		return new StepBuilder("processadorClassifierStep", jobRepository)
				.<Cliente, Cliente>chunk(1, platformTransactionManager)
				.reader(processadorClassifierReader)
				.processor(processadorClassifierProcessor)
				.writer(processadorClassifierWriter)
				.build();
	}
}
