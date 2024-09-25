package com.springbatch.jdbccursorreader.step;

import com.springbatch.jdbccursorreader.dominio.Cliente;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class LeituraArquivoLarguraFixaStepConfig {

	private JobRepository jobRepository;
	private PlatformTransactionManager platformTransactionManager;

	public LeituraArquivoLarguraFixaStepConfig(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
		this.jobRepository = jobRepository;
		this.platformTransactionManager = platformTransactionManager;
	}

	@Bean
	public Step leituraArquivoLarguraFixaStep(ItemReader<Cliente> leituraArquivoLarguraFixaReader,
											  ItemWriter<Cliente> leituraArquivoLarguraFixaWriter) {
		return new StepBuilder("leituraArquivoLarguraFixaWriter", jobRepository)
				/*
				 QUanto maior o numero de chunk, mas dificil da aplicacao se recuperar quando der erro, para processar o que nao foi processado anteriormente
				 pois a transacao de um chunk que nao for completada, ela sera reexecutada.
				 */
				.<Cliente, Cliente>chunk(1, platformTransactionManager) // Chunk controla o numero de transacoes
				.reader(leituraArquivoLarguraFixaReader)
				.writer(leituraArquivoLarguraFixaWriter)
				.build();
	}

}
