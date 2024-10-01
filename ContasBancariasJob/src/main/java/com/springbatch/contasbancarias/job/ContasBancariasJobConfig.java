package com.springbatch.contasbancarias.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContasBancariasJobConfig {
	
	@Bean
	public Job contasBancariasJob(Step criacaoContasStep, JobRepository jobRepository) {
		return new JobBuilder("contasBancariasJob", jobRepository)
				.start(criacaoContasStep)
				.incrementer(new RunIdIncrementer())
				.build();
	}
}
