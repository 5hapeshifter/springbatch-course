package com.springbatch.processadorclassifier.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProcessadorClassifierJobConfig {
	
	@Bean
	public Job processadorClassifierJob(JobRepository jobRepository, Step processadorValidacaoStep) {


		return new JobBuilder("processadorClassifierJob", jobRepository)
				.start(processadorValidacaoStep)
				.incrementer(new RunIdIncrementer())
				.build();
	}
}
