package com.springbatch.jdbccursorreader.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.ExecutionContextSerializer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.dao.Jackson2ExecutionContextStringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArquivoLarguraFixaJobConfig {

	@Bean
	public Job arquivoLarguraFixaJob(JobRepository jobRepository, Step leituraArquivoLarguraFixaStep) {
		return new JobBuilder("arquivoLarguraFixaJob",jobRepository)
				.start(leituraArquivoLarguraFixaStep)
				//.incrementer(new RunIdIncrementer()) // tiramos o incrementer para ocorrer o erro quando o nome for Maria, a gente conseguir restartar o job e não reprocessar o que já tinha sido feito
				.build();
	}

	@Bean
	public ExecutionContextSerializer jacksonSerializer() {
		return new Jackson2ExecutionContextStringSerializer();
	}
}
