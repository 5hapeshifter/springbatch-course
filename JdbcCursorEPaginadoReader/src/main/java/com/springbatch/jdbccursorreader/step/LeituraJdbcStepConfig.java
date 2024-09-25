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

import java.sql.SQLException;

@Configuration
public class LeituraJdbcStepConfig {

	private JobRepository jobRepository;
	private PlatformTransactionManager platformTransactionManager;

	public LeituraJdbcStepConfig(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
		this.jobRepository = jobRepository;
		this.platformTransactionManager = platformTransactionManager;
	}

	@Bean
	public Step leituraJdbcStep(ItemReader<Cliente> leituraArquivoDelimitadoReader,
											 ItemWriter<Cliente> leituraArquivoDelimitadoWriter) {
		return new StepBuilder("leituraJdbcStep", jobRepository)
				/*
				 QUanto maior o numero de chunk, mas dificil da aplicacao se recuperar quando der erro, para processar o que nao foi processado anteriormente
				 pois a transacao de um chunk que nao for completada, ela sera reexecutada.
				 */
				.<Cliente, Cliente>chunk(1, platformTransactionManager) // Chunk controla o numero de transacoes
				.reader(leituraArquivoDelimitadoReader)
				.writer(leituraArquivoDelimitadoWriter)
				.faultTolerant()
				.skip(Exception.class) // Com isso tornamos o job tolerante a falhas
				.skipLimit(2) // Maximo de falhas que pode ocorrer
				.build();
	}

}
