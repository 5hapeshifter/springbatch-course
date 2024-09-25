package com.springbatch.jdbccursorreader.reader;

import com.springbatch.jdbccursorreader.dominio.Cliente;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class LeituraArquivoMultiplosFormatosReaderConfig {

	// Como não sabemos o que estamos lendo, estamos ignorando o warning
	@SuppressWarnings({"rawtypes", "unchecked"})
	@StepScope
	@Bean
	public FlatFileItemReader<Cliente> leituraArquivoMultiplosFormatosReader(
			// Program arguments setado nas configuracoes de execucao - valor: arquivoClientes=file:ArquivoDelimitadoJob/files/clientes.txt
			@Value("#{jobParameters['arquivoClientes']}") Resource arquivoClientes, LineMapper lineMapper) {
		// Como nao sabemos o que vamos receber ao certo, nao podemos definir um tipo especifico
		return new FlatFileItemReaderBuilder()
				.name("leituraArquivoMultiplosFormatosReader")
				.resource(arquivoClientes)
				.lineMapper(lineMapper) // tipo do arquivo, delimitado, indefinido e etc
				.build();
	}
}
