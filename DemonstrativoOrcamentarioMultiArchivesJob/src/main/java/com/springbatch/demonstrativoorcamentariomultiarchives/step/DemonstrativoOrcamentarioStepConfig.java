package com.springbatch.demonstrativoorcamentariomultiarchives.step;

import com.springbatch.demonstrativoorcamentariomultiarchives.dominio.GrupoLancamento;
import com.springbatch.demonstrativoorcamentariomultiarchives.reader.GrupoLancamentoReader;
import com.springbatch.demonstrativoorcamentariomultiarchives.writer.DemonstrativoOrcamentarioRodape;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.MultiResourceItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class DemonstrativoOrcamentarioStepConfig {

	private PlatformTransactionManager platformTransactionManager;
	private JobRepository jobRepository;

	public DemonstrativoOrcamentarioStepConfig(PlatformTransactionManager platformTransactionManager, JobRepository jobRepository) {
		this.platformTransactionManager = platformTransactionManager;
		this.jobRepository = jobRepository;
	}

	@Bean
	public Step demonstrativoOrcamentarioStep(
			// Esse aqui lê dos arquivos
			//MultiResourceItemReader<GrupoLancamento> demonstrativoOrcamentarioReader,
			// Esse aqui lê do banco de dados
			GrupoLancamentoReader demonstrativoOrcamentarioReader,
			MultiResourceItemWriter<GrupoLancamento> demonstrativoOrcamentarioWriter,
			DemonstrativoOrcamentarioRodape rodapeCallback) {
		return new StepBuilder("demonstrativoOrcamentarioStep", jobRepository)
				.<GrupoLancamento,GrupoLancamento>chunk(1, platformTransactionManager) // quando tiver escrita de multiplos arquivos esse valor deve ser equivalente ao do step '.itemCountLimitPerResource(1)'
				.reader(demonstrativoOrcamentarioReader)
				.writer(demonstrativoOrcamentarioWriter)
				.listener(rodapeCallback)
				.build();
	}
}
